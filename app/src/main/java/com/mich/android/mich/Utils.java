package com.mich.android.mich;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.io.ByteArrayOutputStream;

import io.fabric.sdk.android.services.network.HttpRequest;


public class Utils {
    public static void loadUrlInImageView(Context context, final ImageView imageView, String url){
        Ion.with(context)
                .load(url)
                .asBitmap()
                .setCallback(new FutureCallback<Bitmap>() {
                    @Override
                    public void onCompleted(Exception e, Bitmap result) {
                        if (imageView != null && result != null && e == null) {
                            imageView.setImageBitmap(result);
                        }
                    }
                });

    }


    public static String  getBytesFromImageView(ImageView imageView){
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return HttpRequest.Base64.encodeBytes(baos.toByteArray());
    }

}
