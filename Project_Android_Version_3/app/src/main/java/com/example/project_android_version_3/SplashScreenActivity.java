package com.example.project_android_version_3;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {
    //    private static int SPLASH_TIME = 5000;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        AndroidRuntimePermission();
        Thread mThread = new Thread(){

            @Override
            public void run() {
                try {
                    sleep(3000);
                    Intent mySuperIntent = new Intent(SplashScreenActivity.this, MainActivity.class);
                    startActivity(mySuperIntent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        mThread.start();







//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent mySuperIntent = new Intent(SplashScreenActivity.this, MainActivity.class);
//                startActivity(mySuperIntent);
//                /* This 'finish()' is for exiting the app when back button pressed
//                 *  from Home page which is ActivityHome
//                 */
//                finish();
//            }
//        }, SPLASH_TIME);
    }

    // Creating Runtime permission function.
    public void AndroidRuntimePermission(){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){

            if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){

                if(shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)){

                    AlertDialog.Builder alert_builder = new AlertDialog.Builder(this);
                    alert_builder.setMessage("External Storage Permission is Required.");
                    alert_builder.setTitle("Please Grant Permission.");
                    alert_builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            ActivityCompat.requestPermissions(
                                    SplashScreenActivity.this,
                                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                    1

                            );
                        }
                    });

                    alert_builder.setNeutralButton("Cancel",null);

                    AlertDialog dialog = alert_builder.create();

                    dialog.show();

                }
                else {

                    ActivityCompat.requestPermissions(
                            SplashScreenActivity.this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            1
                    );
                }
            }else {

            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){

        switch(requestCode){

            case 1:{

                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                }
                else {

                }
            }
        }
    }
}
