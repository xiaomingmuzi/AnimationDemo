package com.lixm.animationdemo.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.lixm.animationdemo.R;

import org.xutils.common.util.LogUtil;

import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;

/**
 * @author Lixm
 * @date 2018/4/26
 * @detail
 */

public class ImageUtils {
    public static String getImageSize(Bitmap bmp) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] bytes = baos.toByteArray();


        int fileS = bytes.length;
////        if (Build.VERSION.SDK_INT >= 12)
////            fileS = bmp.getByteCount();
////        else
//        fileS = bmp.getRowBytes() * bmp.getHeight();
        LogUtil.i("图片字节数：" + fileS);
        String fileSizeString = "";
        DecimalFormat df = new DecimalFormat("#.00");
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "KB";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "MB";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "GB";
        }
        return fileSizeString;
    }

    public static Bitmap getImage(Context context) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        //为了获取图片的实际宽高，需设置BitmapFactory.Options的inJustDecodeBounds值为true，设为true表示只是解码图片的边距，
        // 而没有实际返回一个Bitmap对象，从而不会因为解码图片过程中占用太多内存而频繁发生OOM，
        // 操作完成后需将该值重新设为false。
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(context.getResources(), R.mipmap.user_1, options);
        options.inJustDecodeBounds = false;

        int w = options.outWidth;// 800
        int h = options.outHeight;// 1280

        int desW = 480;
        int desH = 800;

        int scale = 0;//缩放比例
        if (w > desW || h > desH) {
            int wScale = Math.round(w / desW);
            int hScale = Math.round(h / desH);
            scale = wScale > hScale ? wScale : hScale;
        }
        LogUtil.w("缩放比例：" + scale);
        options.inSampleSize = scale;
        Bitmap result = BitmapFactory.decodeResource(context.getResources(), R.mipmap.user_1, options);
        return result;
    }

    public static Bitmap getImageM(Context context) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        //为了获取图片的实际宽高，需设置BitmapFactory.Options的inJustDecodeBounds值为true，设为true表示只是解码图片的边距，
        // 而没有实际返回一个Bitmap对象，从而不会因为解码图片过程中占用太多内存而频繁发生OOM，
        // 操作完成后需将该值重新设为false。
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(context.getResources(), R.mipmap.user_1, options);
        options.inJustDecodeBounds = false;

        int w = options.outWidth;// 800
        int h = options.outHeight;// 1280

        int desW = 480;
        int desH = 800;

        int wScale = 0;
        int hScale = 0;
        int scale = 0;
        if (w > desW || h > desH) {
            wScale = Math.round(w / desW);
            hScale = Math.round(h / desH);
            scale = wScale > hScale ? hScale : wScale;
        }
//        Matrix matrix = new Matrix();
//        matrix.postScale(wScale, hScale);
//        Bitmap result = Bitmap.createBitmap(BitmapFactory.decodeResource(context.getResources(), R.mipmap.user_1), 0, 0, w, h, matrix, false);
        Bitmap image = BitmapFactory.decodeResource(context.getResources(), R.mipmap.user_1);
        Bitmap result = Bitmap.createScaledBitmap(image, (int) (w * 0.8), (int) (h * 0.8), false);//scale the bitmap
        return result;
    }

    public static Bitmap getImage3(Context context) {
        Bitmap image = BitmapFactory.decodeResource(context.getResources(), R.mipmap.user_1);//loading the large bitmap is fine.
        int w = image.getWidth();//get width
        int h = image.getHeight();//get height
        int aspRat = w / h;//get aspect ratio
        int W = 480;//do whatever you want with width. Fixed, screen size, anything
        int H = w * aspRat;//set the height based on width and aspect ratio
        Bitmap b = Bitmap.createScaledBitmap(image, W, H, false);//scale the bitmap
        return b;
    }
}
