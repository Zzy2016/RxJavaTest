package com.example.filetest;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.File;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import static androidx.core.content.PermissionChecker.PERMISSION_GRANTED;

public class MainActivity extends AppCompatActivity {


    TextView textView;
    Uri uri;
    File apkFile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textview);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

                    uri=FileProvider.getUriForFile(MainActivity.this,getPackageName()+".my_provider",apkFile);

                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    intent.setDataAndType(uri, "application/vnd.android.package-archive");
                } else {

                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    intent.setDataAndType(uri, "application/vnd.android.package-archive");
                }
                startActivity(intent);
            }
        });


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
        } else {
            try {
//                File file = new File(getFilesDir(), "file");
//                Log.e("文件目录", file.getAbsolutePath() + "   " + file.getParentFile().getAbsolutePath());
//                if (!file.exists()) {
//                    file.createNewFile();
//                }
//
//
//                File[] fileArray = getExternalFilesDirs("");
//                for (int i = 0; i < fileArray.length; i++) {
//                    Log.e("文件", fileArray[i].getAbsolutePath());
//                }
//
//                File file112 = getExternalFilesDir("");
//                Log.e("外置文件", file112.getAbsolutePath());
//
//                File file1 = Environment.getDownloadCacheDirectory();
//                Log.e("文件下载", file1.getAbsolutePath() + "    " + file1.isDirectory());
//
//
//                File file2 = new File(Environment.DIRECTORY_DOWNLOADS);
//                Log.e("文件下载1", file2.getAbsolutePath() + "   " + file2.isDirectory());
//
//                File file3 = Environment.getRootDirectory();
//                Log.e("wenjian", file3.getAbsolutePath());
//
//                File file4 = getFilesDir();//获取外置文件
//                Log.e("file4", file4.getAbsolutePath());
//                File fileDown = new File(file4, "file.apk");
//                if (!fileDown.exists()) {
//                    fileDown.createNewFile();
//                }
//                Uri uri = Uri.parse(fileDown.getAbsolutePath());
//
//                Log.e("下载文件1123",uri.toString());
//
//
//                Log.e("下载文件1", fileDown.getAbsolutePath());

                File file = getExternalFilesDir("");
                Log.e("文件", file.getAbsolutePath());
                apkFile = new File(file, "DoctorApp-debug.apk");
                Log.e("文件apk", apkFile.exists() + " ");
                uri = Uri.fromFile(apkFile);
            } catch (Exception e) {
                Log.e("文件异常", e.toString());
            }
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 0) {
            Log.e("权限申请", permissions.toString() + "   " + grantResults);
        }
    }
}
