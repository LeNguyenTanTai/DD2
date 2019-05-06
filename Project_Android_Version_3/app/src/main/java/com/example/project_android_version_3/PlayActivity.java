package com.example.project_android_version_3;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project_android_version_3.Adapter.SongAdapter;
import com.example.project_android_version_3.Adapter.SongVerticalAdapter;
import com.example.project_android_version_3.Data.Song;
import com.example.project_android_version_3.Data.SongItem;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PlayActivity extends AppCompatActivity {
    TextView tvTitleName, tvTimeStart,tvTimeEnd;
    ImageButton ibtnPrev,ibtnNext,ibtnPlay;
    SeekBar sbTimeLine;
    RecyclerView recyclerView;
    ArrayList<Song> arrayList;
    int position = 0;
    MediaPlayer mediaPlayer;
    Animation animation ;
    Intent intent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_song);
        setControl();
        initView();

        if ((intent = getIntent()) != null)
        {
            position =(intent.getIntExtra("positionBaiHat",0));
        }
        createMediaPlayer();
        setEvent();


    }
    public void setControl() {
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        tvTitleName = (TextView) findViewById(R.id.tvTitleName);
        tvTimeStart = (TextView) findViewById(R.id.tvTimeStart);
        tvTimeEnd = (TextView) findViewById(R.id.tvTimeEnd);
        ibtnNext = (ImageButton) findViewById(R.id.ibtnNext);
        ibtnPlay = (ImageButton) findViewById(R.id.ibtnPlay);
        ibtnPrev = (ImageButton) findViewById(R.id.ibtnPrev);
        sbTimeLine = (SeekBar) findViewById(R.id.sbTimeLine);

    }
    public void createMediaPlayer() {
        try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(arrayList.get(position).getnFile());
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.start();
                    ibtnPlay.setBackgroundResource(R.drawable.pause);
                    setTimeTotal();
                    updateTimeSong();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        tvTitleName.setText(arrayList.get(position).getSzName());
    }

    // định dạng lại time
    public void setTimeTotal(){
        SimpleDateFormat dinhDangGio = new SimpleDateFormat("mm:ss");
        tvTimeEnd.setText(dinhDangGio.format(mediaPlayer.getDuration()));
        sbTimeLine.setMax(mediaPlayer.getDuration());
    }

    // update seekbar theo time của bài hát
    public void updateTimeSong(){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat dinhDangGio = new SimpleDateFormat("mm:ss");
                tvTimeStart.setText(dinhDangGio.format(mediaPlayer.getCurrentPosition())); // vị trí hiện tại của thanh

                // cập nhật thanh seekbar
                sbTimeLine.setProgress(mediaPlayer.getCurrentPosition());

                // kiểm tra time chạy đến hết bài
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        position++;
                        if (position > arrayList.size()-1)
                        {
                            position = 0;
                        }
                        if (mediaPlayer.isPlaying())
                        {
                            mediaPlayer.stop();
                        }
                        createMediaPlayer();
                        ibtnPlay.setBackgroundResource(R.drawable.pause);
                    }
                });

                handler.postDelayed(this,500);
            }
        },100);
    }



    public  void setEvent(){
        ibtnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animation = AnimationUtils.loadAnimation(PlayActivity.this,R.anim.play_pause_rotate);
                if (mediaPlayer.isPlaying())
                {
                    mediaPlayer.pause();
                    ibtnPlay.setBackgroundResource(R.drawable.play);
                }
                else
                {
                    mediaPlayer.start();
                    ibtnPlay.setBackgroundResource(R.drawable.pause);

                }
                ibtnPlay.startAnimation(animation);
                setTimeTotal();
                updateTimeSong();
//                Toast.makeText(PlayActivity.this, "" + arrayList.get(position).getSzName(), Toast.LENGTH_SHORT).show();
            }
        });
        ibtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position++;
                if (position > arrayList.size()-1)
                {
                    position = 0;
                }
                if (mediaPlayer.isPlaying())
                {
                    mediaPlayer.stop();
                }
                createMediaPlayer();
                ibtnPlay.setBackgroundResource(R.drawable.pause);

            }
        });

        ibtnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position--;
                if (position < 0)
                {
                    position = arrayList.size()-1;
                }
                if (mediaPlayer.isPlaying())
                {
                    mediaPlayer.stop();
                }
                createMediaPlayer();
                ibtnPlay.setBackgroundResource(R.drawable.pause);
            }
        });
        sbTimeLine.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(sbTimeLine.getProgress());
            }
        });
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int positiona) {
                if (mediaPlayer.isPlaying())
                {
                    mediaPlayer.stop();
                }
                animation = AnimationUtils.loadAnimation(PlayActivity.this,R.anim.play_pause_rotate);
                position = positiona;
                createMediaPlayer();

                ibtnPlay.setBackgroundResource(R.drawable.pause);
                ibtnPlay.startAnimation(animation);


            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));

    }

    public void initView() {




        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        arrayList = new ArrayList<>();
        ContentResolver contentResolver = getContentResolver();
        String selection = MediaStore.Audio.Media.IS_MUSIC + "!=0";



        try{
            Cursor cursor ;
             cursor = this.getApplicationContext().getContentResolver().query(uri, null, selection, null, null);

            if (cursor != null && cursor.getCount() > 0) {
                if (cursor.moveToFirst()) {
                    do {
                        //ImageView imageView = null;
                        int image = R.drawable.emda;
                        String name = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
                        //String artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                        String url = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
                        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
                        retriever.setDataSource(url);
                        byte[] coverBytes = retriever.getEmbeddedPicture();
                        if (coverBytes != null) {
                            Bitmap songCover = BitmapFactory.decodeByteArray(coverBytes, 0, coverBytes.length);
                            //Drawable d = new BitmapDrawable(getResources(), songCover);
                            //image = d;
                        }
                        Song song = new Song(name, url, R.drawable.emda);
                        arrayList.add(song);

                    } while (cursor.moveToNext());
                }

                cursor.close();

            }
        }
        catch (Exception e)
        {
            Toast.makeText(this,"" + e, Toast.LENGTH_LONG).show();
        }

        SongVerticalAdapter songVerticalAdapter = new SongVerticalAdapter(arrayList,getApplicationContext());
        recyclerView.setAdapter(songVerticalAdapter);
    }


}
