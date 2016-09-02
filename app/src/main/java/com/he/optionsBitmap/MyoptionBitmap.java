package com.he.optionsBitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by Administrator on 16-8-28.
 */
public class MyoptionBitmap {
    public static Bitmap getOptionBitmap(byte [] bytes){
        //首先的得到图片的宽高
        int width=0;
        int height=0;
        //定义用于压缩的options类对象
        BitmapFactory.Options options=new BitmapFactory.Options();
        //第一次只拿到图片的边缘，得到图片大小
        options.inJustDecodeBounds=true;
        //一次采样
        BitmapFactory.decodeByteArray(bytes,0,bytes.length,options);
        width=options.outWidth;
        height=options.outHeight;
        //使用宽高设置压缩比例
      // options.inSampleSize=Math.max(width,height)/500;
        options.inSampleSize=10;
        //二次采样得到bitmap对象
        options.inJustDecodeBounds=false;

        return BitmapFactory.decodeByteArray(bytes,0,bytes.length,options);
    }
}
