package com.hq.uitest.permission;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.hq.uitest.R;

public class RunTimePermissionActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_camera,btn_read_contact,btn_write_contact;
    private final int REQUEST_CAMERA = 0x1;
    private final int READ_CONTACT = 0x2;
    private final int WRITE_CONTACT = 0x3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run_time_permission);
        btn_camera = (Button) findViewById(R.id.btn_permission_camera);
        btn_read_contact = (Button) findViewById(R.id.btn_read_contact);
        btn_write_contact = (Button) findViewById(R.id.btn_write_contact);
        btn_camera.setOnClickListener(this);
        btn_read_contact.setOnClickListener(this);
        btn_write_contact.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_permission_camera:
                int checkId =  ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
                if(checkId == PackageManager.PERMISSION_DENIED){
                    Log.e("TAG","Camera permission 拒绝");
                    if(Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
                        boolean shouldShow = shouldShowRequestPermissionRationale(Manifest.permission.CAMERA);
                        if(shouldShow){
                            Log.e("TAG","should is true");
                        }else{
                            Log.e("TAG","should is false");
                        }
                        requestPermissions(new String []{Manifest.permission.CAMERA},REQUEST_CAMERA);
                    }
                }
                if(checkId == PackageManager.PERMISSION_GRANTED){
                    Log.e("TAG","Camera permission 同意");
                    Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivity(captureIntent);
                }
                break;
            case R.id.btn_read_contact:
                int checkReadContact = ContextCompat.checkSelfPermission(this,Manifest.permission_group.CONTACTS);
                if(checkReadContact == PackageManager.PERMISSION_DENIED){
                    if(Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
                        boolean shouldShow = shouldShowRequestPermissionRationale(Manifest.permission.READ_CONTACTS);
                        requestPermissions(new String []{Manifest.permission.READ_CONTACTS},READ_CONTACT);
                    }
                }
                if(checkReadContact == PackageManager.PERMISSION_GRANTED){
                    Log.e("TAG","Write Contact permission 同意");
                }
                break;
            case R.id.btn_write_contact:
                int checkWriteContact = ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_CONTACTS);
                if(checkWriteContact == PackageManager.PERMISSION_DENIED){
                    if(Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
                        boolean shouldShow = shouldShowRequestPermissionRationale(Manifest.permission.WRITE_CONTACTS);
                        requestPermissions(new String []{Manifest.permission.WRITE_CONTACTS},WRITE_CONTACT);
                    }
                }
                if(checkWriteContact == PackageManager.PERMISSION_GRANTED){
                    Log.e("TAG","Write Contact permission 同意");
                }
                break;
        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case REQUEST_CAMERA:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.e("TAG","onRequestPermissionsResult ..."+"已经获取了该权限");
                    Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivity(captureIntent);
                } else {
                    Log.e("TAG","onRequestPermissionsResult ..."+"用户拒绝给权限");
                }
                break;
            case READ_CONTACT:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.e("TAG","onRequestPermissionsResult  READ CONTACT..."+"已经获取了该权限");
                } else {
                    Log.e("TAG","onRequestPermissionsResult  READ CONTACT..."+"用户拒绝给权限");
                }
                break;
            case WRITE_CONTACT:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.e("TAG","onRequestPermissionsResult WRITE CONTACT..."+"已经获取了该权限");
                } else {
                    Log.e("TAG","onRequestPermissionsResult WRITE CONTACT ..."+"用户拒绝给权限");
                }
                break;
        }

    }
}
