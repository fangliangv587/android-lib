package com.xz.momo.common.image;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.xz.momo.common.R;

/**
 * Created by xz on 2017/9/7.
 */

public class GlideUtil {

    private static int placeholderRes = R.drawable.ic_launcher;
    private static int errorRes = R.drawable.ic_launcher;

    public static void setPlaceholderRes(int placeholderRes , int errorRes) {
        GlideUtil.placeholderRes = placeholderRes;
        GlideUtil.errorRes = errorRes;
    }


    public static void loadImage(Context context, ImageView imageview, String url){
        loadImage(context, imageview, url,placeholderRes, errorRes,false);
    }

    public static void loadCircleImage(Context context, ImageView imageview, String url){
        loadImage(context, imageview, url, placeholderRes,errorRes,true);
    }

    /**
     * 加载图片
     * @param context
     * @param imageview
     * @param url
     * @param placeholderRes
     * @param errorRes
     */
    public static void loadImage(Context context, ImageView imageview, String url, int placeholderRes, int errorRes, boolean isCircle){
        DrawableRequestBuilder<String> builder = Glide.with(context).load(url).placeholder(placeholderRes).error(errorRes);
        builder.fitCenter().centerCrop();
        if (isCircle){
            builder.bitmapTransform(new GlideCircleTransform(context));
        }
        builder .into(imageview);
    }
}
