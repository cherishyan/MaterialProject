/*
 * Copyright (c) 2015 Zhang Hai <Dreaming.in.Code.ZH@Gmail.com>
 * All Rights Reserved.
 */

package me.jinqiang.android.material.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;

import me.jinqiang.android.material.network.api.info.Image;
import me.jinqiang.android.material.ui.RatioImageView;
import me.jinqiang.android.material.R;

public class ImageUtils {

    public static void loadAvatar(ImageView view, String url, Context context) {
        Glide.with(context)
                .load(url)
                .placeholder(R.drawable.avatar_icon_grey600_40dp)
                .dontAnimate()
                .dontTransform()
                .into(view);
    }

    public static void loadImage(RatioImageView view, Image image, Context context) {
        view.setRatio(image.width, image.height);
        Glide.with(context)
                .load(image.medium)
                // dontTransform() is required for our RatioImageView to work correctly.
                .dontTransform()
                .placeholder(android.R.color.transparent)
                .into(view);
    }

    public static void loadImage(ImageView view, String url,
                                 RequestListener<String, GlideDrawable> listener, Context context) {
        Glide.with(context)
                .load(url)
                .dontTransform()
                .placeholder(android.R.color.transparent)
                .listener(listener)
                .into(view);
    }

    public static void loadImage(ImageView view, String url, Context context) {
        loadImage(view, url, null, context);
    }
}
