package com.example.project_android_version_3;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project_android_version_3.Adapter.SongAdapter;
import com.example.project_android_version_3.Adapter.SongVerticalAdapter;
import com.example.project_android_version_3.Data.Song;
import com.example.project_android_version_3.Data.SongItem;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ImageButton imgAbout,btnSearch,imgNext,imgPrev;
    ImageView img1,img2,img3;
    FrameLayout fmheader,fmAbout;
    TextView tvHome;
    Animation anima;
    CardView cardNhacTre,cardAuMy,cardKpop,cardEDM;
    LinearLayout lnTopThree,lnTitleBaiHat,lnDanhSachBaiHat;
    RecyclerView recyclerView;
    Song song;
    ArrayList<Song> arrayList;
    public static final int RUNTIME_PERMISSION_CODE = 7;
    ArrayList<String> ListElementsArrayList ;
    int count = 0;
    int countNext = 0 ;
    int countPrev = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setControl();
        setEvent();
        initView();
        //AndroidRuntimePermission();
        //GetAllMediaMp3Files();
    }


    // setControl
    public void setControl()
    {
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        imgAbout = (ImageButton) findViewById(R.id.imgAbout);
        imgNext = (ImageButton) findViewById(R.id.imgNext);
        imgPrev = (ImageButton) findViewById(R.id.imgPrev);
        btnSearch = (ImageButton) findViewById(R.id.btnSearch);
        fmheader = (FrameLayout) findViewById(R.id.fmheader);
        fmAbout = (FrameLayout) findViewById(R.id.fmAbout);
        tvHome = (TextView) findViewById(R.id.tvHome);
        img1 = (ImageView) findViewById(R.id.img1);
        img2 = (ImageView) findViewById(R.id.img2);
        img3 = (ImageView) findViewById(R.id.img3);
        cardNhacTre = (CardView) findViewById(R.id.cardNhacTre);
        cardKpop = (CardView) findViewById(R.id.cardKpop);
        cardAuMy = (CardView) findViewById(R.id.cardAuMy);
        cardEDM = (CardView) findViewById(R.id.cardEDM);
        lnTopThree = (LinearLayout) findViewById(R.id.lnTopThree);
        lnTitleBaiHat = (LinearLayout) findViewById(R.id.lnTitleBaiHat);
        lnDanhSachBaiHat = (LinearLayout) findViewById(R.id.lnDanhSachBaiHat);
    }

    // setEvent
    public void setEvent()
    {
        Animation slide_up = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slide_up);
        lnTopThree.startAnimation(slide_up);
        lnTitleBaiHat.startAnimation(slide_up);
        lnDanhSachBaiHat.startAnimation(slide_up);

        imgAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                count++;
                if (count == 1)
                {
                    fmAbout.setVisibility(View.VISIBLE);
                    fmAbout.animate().translationY(0).setDuration(500);
                    fmheader.animate().translationY(800).setDuration(500);
                    imgAbout.animate().translationY(200).setDuration(500);
                    tvHome.animate().alpha(0).setDuration(500);
                    btnSearch.animate().alpha(0).setDuration(500);
                }
                if (count == 2)
                {

                    fmAbout.animate().translationY(-800).setDuration(500);
                    fmheader.animate().translationY(0).setDuration(500);
                    imgAbout.animate().translationY(0).setDuration(500);
                    tvHome.animate().alpha(1).setDuration(500);
                    btnSearch.animate().alpha(1).setDuration(500);
                    count = 0;
                }
            }
        });
        imgNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countNext++;
                if(countNext == 1)
                {
                    // set màn hình 2
                    img2.animate().translationX(0).setDuration(500);
                    img2.animate().translationZ(10).setDuration(500);
                    img2.animate().scaleX((float)1.1).setDuration(500);
                    img2.animate().scaleY((float)1.1).setDuration(500);
                    img2.setElevation(2);

                    // thay doi img1
                    img1.animate().translationX(-190).setDuration(500);
                    img1.animate().translationZ(0).setDuration(500);
                    img1.animate().scaleX((float)1).setDuration(500);
                    img1.animate().scaleY((float)1).setDuration(500);
                    img1.setElevation(1);

                    // thay doi img3
                    img3.animate().translationX(190).setDuration(500);
                    img3.animate().translationZ(-5).setDuration(500);
                    img3.animate().scaleX((float)1).setDuration(500);
                    img3.animate().scaleY((float)1).setDuration(500);
                    img3.setElevation(1);
                    countPrev = 2;

                }
                else if (countNext == 2)
                {
                    // set màn hình 3
                    img3.animate().translationX(0).setDuration(500);
                    img3.animate().translationZ(10).setDuration(500);
                    img3.animate().scaleX((float)1.1).setDuration(500);
                    img3.animate().scaleY((float)1.1).setDuration(500);
                    img3.setElevation(2);

                    // thay doi img2
                    img2.animate().translationX(-190).setDuration(500);
                    img2.animate().translationZ(0).setDuration(500);
                    img2.animate().scaleX((float)1).setDuration(500);
                    img2.animate().scaleY((float)1).setDuration(500);
                    img2.setElevation(1);

                    // thay doi img1
                    img1.animate().translationX(190).setDuration(500);
                    img1.animate().translationZ(-5).setDuration(500);
                    img1.animate().scaleX((float)1).setDuration(500);
                    img1.animate().scaleY((float)1).setDuration(500);
                    img1.setElevation(1);
                    countPrev = 1;
                }
                else if (countNext == 3)
                {
                    // set màn hình 1
                    img1.animate().translationX(0).setDuration(500);
                    img1.animate().translationZ(10).setDuration(500);
                    img1.animate().scaleX((float)1.1).setDuration(500);
                    img1.animate().scaleY((float)1.1).setDuration(500);
                    img1.setElevation(2);

                    // thay doi img3
                    img3.animate().translationX(-190).setDuration(500);
                    img3.animate().translationZ(0).setDuration(500);
                    img3.animate().scaleX((float)1).setDuration(500);
                    img3.animate().scaleY((float)1).setDuration(500);
                    img3.setElevation(1);

                    // thay doi img2
                    img2.animate().translationX(190).setDuration(500);
                    img2.animate().translationZ(-5).setDuration(500);
                    img2.animate().scaleX((float)1).setDuration(500);
                    img2.animate().scaleY((float)1).setDuration(500);
                    img2.setElevation(1);
                    countNext = 0;
                    countPrev = 0;
                }

            }
        });

        imgPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countPrev++;
                if (countPrev == 1)
                {
                    //set man hinh 3
                    img3.animate().translationX(0).setDuration(500);
                    img3.animate().translationZ(10).setDuration(500);
                    img3.animate().scaleX((float)1.1).setDuration(500);
                    img3.animate().scaleY((float)1.1).setDuration(500);
                    img3.setElevation(2);


                    // thay doi img2
                    img2.animate().translationX(-190).setDuration(500);
                    img2.animate().translationZ(0).setDuration(500);
                    img2.animate().scaleX((float)1).setDuration(500);
                    img2.animate().scaleY((float)1).setDuration(500);
                    img2.setElevation(1);

                    // thay doi img1
                    img1.animate().translationX(190).setDuration(500);
                    img1.animate().translationZ(-5).setDuration(500);
                    img1.animate().scaleX((float)1).setDuration(500);
                    img1.animate().scaleY((float)1).setDuration(500);
                    img1.setElevation(1);

                    countNext = 2;
                }
                else if (countPrev == 2)
                {

                    // set màn hình 2
                    img2.animate().translationX(0).setDuration(500);
                    img2.animate().translationZ(10).setDuration(500);
                    img2.animate().scaleX((float)1.1).setDuration(500);
                    img2.animate().scaleY((float)1.1).setDuration(500);
                    img2.setElevation(2);

                    // thay doi img1
                    img1.animate().translationX(-190).setDuration(500);
                    img1.animate().translationZ(0).setDuration(500);
                    img1.animate().scaleX((float)1).setDuration(500);
                    img1.animate().scaleY((float)1).setDuration(500);
                    img1.setElevation(1);

                    // thay doi img3
                    img3.animate().translationX(190).setDuration(500);
                    img3.animate().translationZ(-5).setDuration(500);
                    img3.animate().scaleX((float)1).setDuration(500);
                    img3.animate().scaleY((float)1).setDuration(500);
                    img3.setElevation(1);


                    countNext = 1;
                }
                else if(countPrev == 3)
                {
                    // set màn hình 1
                    img1.animate().translationX(0).setDuration(500);
                    img1.animate().translationZ(10).setDuration(500);
                    img1.animate().scaleX((float)1.1).setDuration(500);
                    img1.animate().scaleY((float)1.1).setDuration(500);
                    img1.setElevation(2);

                    // thay doi img3
                    img3.animate().translationX(-190).setDuration(500);
                    img3.animate().translationZ(0).setDuration(500);
                    img3.animate().scaleX((float)1).setDuration(500);
                    img3.animate().scaleY((float)1).setDuration(500);
                    img3.setElevation(1);

                    // thay doi img2
                    img2.animate().translationX(190).setDuration(500);
                    img2.animate().translationZ(-5).setDuration(500);
                    img2.animate().scaleX((float)1).setDuration(500);
                    img2.animate().scaleY((float)1).setDuration(500);
                    img2.setElevation(1);
                    countPrev = 0;
                    countNext = 0;
                }

            }
        });

        // Top 3 ramdom click
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSong = new Intent(MainActivity.this, PlayActivity.class);
                intentSong.putExtra("positionBaiHat",0);
                startActivity(intentSong);
            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSong = new Intent(MainActivity.this, PlayActivity.class);
                intentSong.putExtra("positionBaiHat",1);
                startActivity(intentSong);
            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSong = new Intent(MainActivity.this, PlayActivity.class);
                intentSong.putExtra("positionBaiHat",2);
                startActivity(intentSong);
            }
        });


        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int positiona) {

                Intent intentSong = new Intent(MainActivity.this, PlayActivity.class);
                intentSong.putExtra("positionBaiHat",positiona);
                startActivity(intentSong);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));








        // thể loại click
        cardEDM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentChuDe = new Intent(MainActivity.this, TabsActivity.class);
                intentChuDe.putExtra("positionChuDe",3);
                startActivity(intentChuDe);
            }
        });
        cardAuMy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentChuDe = new Intent(MainActivity.this, TabsActivity.class);
                intentChuDe.putExtra("positionChuDe",2);
                startActivity(intentChuDe);
            }
        });
        cardKpop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentChuDe = new Intent(MainActivity.this, TabsActivity.class);
                intentChuDe.putExtra("positionChuDe",1);
                startActivity(intentChuDe);
            }
        });
        cardNhacTre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentChuDe = new Intent(MainActivity.this, TabsActivity.class);
                intentChuDe.putExtra("positionChuDe",0);
                startActivity(intentChuDe);
            }
        });
    }

    public void GetAllMediaMp3Files(){
        Toast.makeText(MainActivity.this,"1.", Toast.LENGTH_LONG).show();
        ContentResolver contentResolver = this.getContentResolver();

        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

        Cursor cursor = contentResolver.query(
                uri, // Uri
                null,
                null,
                null,
                null
        );



        if (cursor == null) {

            Toast.makeText(MainActivity.this,"2Something Went Wrong.", Toast.LENGTH_LONG).show();

        } else if (!cursor.moveToFirst()) {

            Toast.makeText(MainActivity.this,"3No Music Found on SD Card.", Toast.LENGTH_LONG).show();
            Toast.makeText(MainActivity.this,"3No Music Found on SD Card.", Toast.LENGTH_LONG).show();
            Toast.makeText(MainActivity.this,"3No Music Found on SD Card.", Toast.LENGTH_LONG).show();
            Toast.makeText(MainActivity.this,"3No Music Found on SD Card.", Toast.LENGTH_LONG).show();
            Toast.makeText(MainActivity.this,"3No Music Found on SD Card.", Toast.LENGTH_LONG).show();

        }
        else {
           Toast.makeText(MainActivity.this,"4No Music Found on SD Card.", Toast.LENGTH_LONG).show();

        }
    }

//    // Creating Runtime permission function.
//    public void AndroidRuntimePermission(){
//        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
//
//            if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
//
//                if(shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)){
//
//                    AlertDialog.Builder alert_builder = new AlertDialog.Builder(MainActivity.this);
//                    alert_builder.setMessage("External Storage Permission is Required.");
//                    alert_builder.setTitle("Please Grant Permission.");
//                    alert_builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//
//                            ActivityCompat.requestPermissions(
//                                    MainActivity.this,
//                                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
//                                    RUNTIME_PERMISSION_CODE
//
//                            );
//                        }
//                    });
//
//                    alert_builder.setNeutralButton("Cancel",null);
//
//                    AlertDialog dialog = alert_builder.create();
//
//                    dialog.show();
//
//                }
//                else {
//
//                    ActivityCompat.requestPermissions(
//                            MainActivity.this,
//                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
//                            RUNTIME_PERMISSION_CODE
//                    );
//                }
//            }else {
//
//            }
//        }
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
//
//        switch(requestCode){
//
//            case RUNTIME_PERMISSION_CODE:{
//
//                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
//
//                }
//                else {
//
//                }
//            }
//        }
//    }

    public void initView() {




        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        arrayList = new ArrayList<>();
        ContentResolver contentResolver = getContentResolver();
        String selection = MediaStore.Audio.Media.IS_MUSIC + "!=0";
        try{
            Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        //ImageView imageView = null;
                        int image = R.drawable.emda;
                        String name = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
                        String artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                        String url = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
                        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
                        retriever.setDataSource(url);
                        byte[] coverBytes = retriever.getEmbeddedPicture();
                        if (coverBytes != null) {
                            Bitmap songCover = BitmapFactory.decodeByteArray(coverBytes, 0, coverBytes.length);
                            //Drawable d = new BitmapDrawable(getResources(), songCover);
                            //image = d;
                        }
                        song = new Song(name, url, image);
                        arrayList.add(song);

                    } while (cursor.moveToNext());
                }

                cursor.close();
                SongAdapter songVerticalAdapter = new SongAdapter(arrayList,getApplicationContext());
                recyclerView.setAdapter(songVerticalAdapter);
            }
        }
        catch (Exception e)
        {
            Toast.makeText(this,"" + e, Toast.LENGTH_LONG).show();
        }

    }

}
