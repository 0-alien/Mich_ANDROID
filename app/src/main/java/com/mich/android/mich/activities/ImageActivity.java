package com.mich.android.mich.activities;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.mich.android.mich.BaseActivity;
import com.mich.android.mich.R;
import com.mich.android.mich.Utils;
import com.mich.android.mich.transport.DoPostCallback;
import com.mich.android.mich.transport.MichTransport;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ImageActivity extends BaseActivity {

    ImageView image;
    EditText titleEt;
    Button postBtn;
    static final int REQUEST_TAKE_PHOTO = 1;
    static final int RESULT_LOAD_IMG = 2;
    boolean isCamera = false;


    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        image = (ImageView)findViewById(R.id.image);
        titleEt = (EditText)findViewById(R.id.et_title);
        postBtn = (Button)findViewById(R.id.btn_post);

        Bundle b = getIntent().getExtras();

        if(b != null) {
            isCamera = b.getBoolean("isCamera");
        }

        if(isCamera) {
            ContentValues values = new ContentValues();
            values.put(MediaStore.Images.Media.TITLE, "New Picture");
            values.put(MediaStore.Images.Media.DESCRIPTION, "From your Camera");
            imageUri = getContentResolver().insert(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            startActivityForResult(intent, REQUEST_TAKE_PHOTO);
        }else{
            Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
            photoPickerIntent.setType("image/*");
            startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);
        }

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_image;
    }


    public String getRealPathFromURI(Uri contentUri) {
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(contentUri, proj, null, null, null);
        int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            try {
                Bitmap thumbnail = MediaStore.Images.Media.getBitmap(
                        getContentResolver(), imageUri);
                image.setImageBitmap(thumbnail);
                String imageurl = getRealPathFromURI(imageUri);
                postBtn.setEnabled(true);
            } catch (Exception e) {
                e.printStackTrace();
                postBtn.setEnabled(false);
            }

        }


        if(requestCode == RESULT_LOAD_IMG) {
            if (resultCode == RESULT_OK) {
                try {
                    final Uri imageUri = data.getData();
                    final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                    final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                    image.setImageBitmap(selectedImage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(ImageActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
                }
                postBtn.setEnabled(true);
            } else {
                Toast.makeText(ImageActivity.this, "You haven't picked Image", Toast.LENGTH_LONG).show();
                postBtn.setEnabled(false);
            }
        }


    }

    public void onPostBtnClick(View sender){
//        String title = titleEt.getText().toString();
//        final ProgressDialog dialog = ProgressDialog.show(this, "",
//                "Loading. Please wait...", true);

        ImageToByteArrayTask task = new ImageToByteArrayTask();
        task.setTitle(titleEt.getText().toString());
        task.execute(image);





    }




    private class ImageToByteArrayTask extends AsyncTask<ImageView, Void, String > {

        String title;
        ProgressDialog dialog;

        public void setTitle(String title) {
            this.title = title;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = ProgressDialog.show(ImageActivity.this, "",
                    "Loading. Please wait...", true);

        }

        protected String  doInBackground(ImageView... args) {
            return Utils.getBytesFromImageView(args[0]);
        }



        protected void onPostExecute(String  img) {
            MichTransport.getInstance().uploadPost(ImageActivity.this, title, img, new DoPostCallback<Void>() {
                @Override
                public void onLoad(int code, String message, Void data) {
                    dialog.dismiss();
                    ImageActivity.this.finish();
                }
            });
        }
    }




}
