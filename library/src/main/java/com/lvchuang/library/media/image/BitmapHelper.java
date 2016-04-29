package com.lvchuang.library.media.image;

import android.content.Context;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;

/**
 * Created by: Liu.ZhiYun on 2016/4/29.
 * Description:
 */
public class BitmapHelper {

    private Context context;

    private static BitmapHelper helper;

    private BitmapHelper(Context context) {
        this.context = context;
    }
    public static BitmapHelper build(Context context){
       if(helper==null){
           helper=new BitmapHelper(context);
       }
        return helper;
    }
    public boolean showImage(String imageUrl, ImageView imageView){
        Picasso.with(context).load(imageUrl).into(imageView);
        return  true;
    }
}
