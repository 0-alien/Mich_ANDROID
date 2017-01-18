package com.mich.android.mich;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;


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
}
