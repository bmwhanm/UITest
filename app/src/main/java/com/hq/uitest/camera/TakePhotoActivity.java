package com.hq.uitest.camera;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.hq.uitest.R;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by heqiang on 17/10/16.
 */

public class TakePhotoActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btn_take_photo,btn_select;
    private final int TAKE_PHOTO = 0x1;
    private final int CROP_PHOTO = 0x2;
    private final int SELECT_PHOTO = 0x3;
    private File  mPhotoFile ;
    private ImageView iv_photo;
    private String EXTRA_RESTORE_PHOTO = "photo File";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_photo);

        btn_take_photo = (Button) findViewById(R.id.btn_take_photo);
        btn_select = (Button) findViewById(R.id.btn_select);
        iv_photo = (ImageView) findViewById(R.id.iv_take_photo);
        btn_select.setOnClickListener(this);
        btn_take_photo.setOnClickListener(this);
        File file = getExternalCacheDir();
        mPhotoFile = new File(file.getAbsolutePath() + "take_photo.jpg");
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mPhotoFile != null) {
            outState.putSerializable(EXTRA_RESTORE_PHOTO, mPhotoFile);
        }

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mPhotoFile = (File) savedInstanceState.getSerializable(EXTRA_RESTORE_PHOTO);
        Bitmap bitmap = decodeBitmapFromFile(mPhotoFile.getAbsolutePath(),200,200);
        int degree = getBitmapDegree(mPhotoFile.getAbsolutePath());
        bitmap = rotateBitmapByDegree(bitmap,degree);
        iv_photo.setImageBitmap(bitmap);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_take_photo:
                if(hasCamera()) {
                    Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    Uri fileUri = Uri.fromFile(mPhotoFile);
                    captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
                    startActivityForResult(captureIntent, TAKE_PHOTO);
                }else{
                    Toast.makeText(this,"没有发现相机应用",Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.btn_select:
                break;
        }
    }



    /**
     * 判断系统中是否存在可以启动的相机应用
     *
     * @return 存在返回true，不存在返回false
     */
    public boolean hasCamera() {
        PackageManager packageManager = getPackageManager();
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        List<ResolveInfo> list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }



    //2、拿到照片
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == TAKE_PHOTO && resultCode == RESULT_OK) {
            Log.e("TAG","PhotoFile .... "+mPhotoFile.getAbsolutePath());
            Bitmap bitmap = BitmapFactory.decodeFile(mPhotoFile.getAbsolutePath());
            int degree = getBitmapDegree(mPhotoFile.getAbsolutePath());
            Bitmap bitmap1 = rotateBitmapByDegree(BitmapFactory.decodeFile(mPhotoFile.getAbsolutePath()),degree);
            iv_photo.setImageBitmap(bitmap1);
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }



    /**
     * 获取图片的旋转角度
     *
     * @param path 图片绝对路径
     * @return 图片的旋转角度
     */
    public static int getBitmapDegree(String path) {
        int degree = 0;
        try {
            // 从指定路径下读取图片，并获取其EXIF信息
            ExifInterface exifInterface = new ExifInterface(path);
            // 获取图片的旋转信息
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    /**
     * 将图片按照指定的角度进行旋转
     *
     * @param bitmap 需要旋转的图片
     * @param degree 指定的旋转角度
     * @return 旋转后的图片
     */
    public static Bitmap rotateBitmapByDegree(Bitmap bitmap, int degree) {
        // 根据旋转角度，生成旋转矩阵
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        // 将原始图片按照旋转矩阵进行旋转，并得到新的图片
        Bitmap newBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
//        if (bitmap != null && !bitmap.isRecycled()) {
//            bitmap.recycle();
//        }
        return newBitmap;
    }


    /**
     * 压缩Bitmap的大小
     *
     * @param imagePath     图片文件路径
     * @param requestWidth  压缩到想要的宽度
     * @param requestHeight 压缩到想要的高度
     * @return
     */
    public static Bitmap decodeBitmapFromFile(String imagePath, int requestWidth, int requestHeight) {
        if (!TextUtils.isEmpty(imagePath)) {
            Log.i("TAG", "requestWidth: " + requestWidth);
            Log.i("TAG", "requestHeight: " + requestHeight);
            if (requestWidth <= 0 || requestHeight <= 0) {
                Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
                return bitmap;
            }
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;//不加载图片到内存，仅获得图片宽高
            BitmapFactory.decodeFile(imagePath, options);
            Log.i("TAG", "original height: " + options.outHeight);
            Log.i("TAG", "original width: " + options.outWidth);
            if (options.outHeight == -1 || options.outWidth == -1) {
                try {
                    ExifInterface exifInterface = new ExifInterface(imagePath);
                    int height = exifInterface.getAttributeInt(ExifInterface.TAG_IMAGE_LENGTH, ExifInterface.ORIENTATION_NORMAL);//获取图片的高度
                    int width = exifInterface.getAttributeInt(ExifInterface.TAG_IMAGE_WIDTH, ExifInterface.ORIENTATION_NORMAL);//获取图片的宽度
                    Log.i("TAG", "exif height: " + height);
                    Log.i("TAG", "exif width: " + width);
                    options.outWidth = width;
                    options.outHeight = height;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            options.inSampleSize = calculateInSampleSize(options, requestWidth, requestHeight); //计算获取新的采样率
            Log.i("TAG", "inSampleSize: " + options.inSampleSize);
            options.inJustDecodeBounds = false;
            return BitmapFactory.decodeFile(imagePath, options);

        } else {
            return null;
        }
    }


    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        Log.i("TAG", "height: " + height);
        Log.i("TAG", "width: " + width);
        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            while ((halfHeight / inSampleSize) > reqHeight && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }

            long totalPixels = width * height / inSampleSize;

            final long totalReqPixelsCap = reqWidth * reqHeight * 2;

            while (totalPixels > totalReqPixelsCap) {
                inSampleSize *= 2;
                totalPixels /= 2;
            }
        }
        return inSampleSize;
    }


}
