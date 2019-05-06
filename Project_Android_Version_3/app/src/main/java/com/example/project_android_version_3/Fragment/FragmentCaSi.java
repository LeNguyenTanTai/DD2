package com.example.project_android_version_3.Fragment;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.example.project_android_version_3.Adapter.SongVerticalAdapter;
import com.example.project_android_version_3.Data.Song;
import com.example.project_android_version_3.Data.SongItem;
import com.example.project_android_version_3.MainActivity;
import com.example.project_android_version_3.PlayActivity;
import com.example.project_android_version_3.R;
import com.example.project_android_version_3.RecyclerItemClickListener;
import com.example.project_android_version_3.TabsActivity;

import java.util.ArrayList;

public class FragmentCaSi extends Fragment {
    View view;
    RecyclerView recyclerView;
    MediaPlayer mediaPlayer;
    Song song;
    ArrayList<Song> arrayList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.nhactre, container, false);
        setControl();
        initView();
        setEvent();
        return view;
    }

    // setControl
    public void setControl(){
        recyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);
    }
    // setEvent
    public void setEvent(){
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int positiona) {
                Intent intentSong = new Intent(getActivity(), PlayActivity.class);
                intentSong.putExtra("positionBaiHat",positiona);
                startActivity(intentSong);


            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));
    }

    // lấy dữ liệu
    public void initView() {




        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        arrayList = new ArrayList<>();
        ContentResolver contentResolver = getContext().getContentResolver();
        String selection = MediaStore.Audio.Media.IS_MUSIC + "!=0";
        try{
            Cursor cursor = getContext().getContentResolver().query(uri, null, null, null, null);
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
                SongVerticalAdapter songVerticalAdapter = new SongVerticalAdapter(arrayList,getContext());
                recyclerView.setAdapter(songVerticalAdapter);
            }
        }
        catch (Exception e)
        {
            Toast.makeText(getContext(),"" + e, Toast.LENGTH_LONG).show();
        }

    }
}
