package com.hq.uitest.base;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 *
 * @author heqiang
 */
public class ImageUtil {

    public static void disPlayImage(ImageView view, String url) {
        if (view != null && url != null) {
            Glide.with(view.getContext()).load(url).into(view);
        }
    }

    public static void disPlayImageAllCache(ImageView view, String url) {
        if (view != null && url != null) {
            Glide.with(view.getContext()).load(url).
                    centerCrop().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(view);
        }
    }


    public static int calculateInSampleSize(BitmapFactory.Options options,
                                            int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            float heightRatio = height * 1.0f / reqHeight;
            float widthRatio = width * 1.0f / reqWidth;
            inSampleSize = Math.round(heightRatio < widthRatio ? heightRatio : widthRatio);
        }
        return inSampleSize < 1 ? 1 : inSampleSize;
    }


    public static Bitmap getSmallBitmap(String filePath, int w, int h) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 1;
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        int sampleSize = calculateInSampleSize(options, w, h);
        options.inJustDecodeBounds = false;
        options.inSampleSize = sampleSize;
        return BitmapFactory.decodeFile(filePath, options);
    }



    public static void compressAndGenImage(Bitmap bitmap, String outputPath, int maxSize) {

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        int options = 100;

        bitmap.compress(Bitmap.CompressFormat.JPEG, options, os);
        while (os.toByteArray().length / 1024 > maxSize) {
            os.reset();
            options -= 20;
            if (options < 10) {
                options = 10;
                bitmap.compress(Bitmap.CompressFormat.JPEG, options, os);
                break;
            }
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, os);
        }
        try {
            FileOutputStream fos = new FileOutputStream(outputPath);
            fos.write(os.toByteArray());
            fos.flush();
            fos.close();
            bitmap.recycle();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
