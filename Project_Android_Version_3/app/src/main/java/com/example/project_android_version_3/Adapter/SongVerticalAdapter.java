package com.example.project_android_version_3.Adapter;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v7.widget.ActivityChooserView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.project_android_version_3.Data.Song;
import com.example.project_android_version_3.Data.SongItem;
import com.example.project_android_version_3.PlayActivity;
import com.example.project_android_version_3.R;

import java.util.ArrayList;

public class SongVerticalAdapter extends  RecyclerView.Adapter<SongVerticalAdapter.ViewHolder>{



    ArrayList<Song> dataSongs;
    Context context;
    MediaPlayer mediaPlayer;
    public SongVerticalAdapter(ArrayList<Song> dataSongs, Context context) {
        this.dataSongs = dataSongs;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i){
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_row,viewGroup,false);
//        mediaPlayer = MediaPlayer.create(context,dataSongs.indexOf(i));
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        viewHolder.txtNumber.setText("" + (i+1));
        viewHolder.txtName.setText(dataSongs.get(i).getSzName());
        viewHolder.imgHinhAnh.setImageResource(dataSongs.get(i).getnImage());
        viewHolder.lnlitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                if (mediaPlayer.isPlaying())
//                {
//                    mediaPlayer.stop();
//                    mediaPlayer = MediaPlayer.create(context,dataSongs.get(i).getnFile());
//
//                }
//                else
//                {
//                    mediaPlayer = MediaPlayer.create(context,dataSongs.get(i).getnFile());
//                }
//
//                mediaPlayer.start();
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataSongs.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView txtName;
        ImageView imgHinhAnh;
        TextView txtNumber;
        LinearLayout lnlitem;

        public ViewHolder( View itemView) {
            super(itemView);
            txtNumber = (TextView)itemView.findViewById(R.id.sTT);
            txtName = (TextView)itemView.findViewById(R.id.txtName);
            imgHinhAnh = (ImageView)itemView.findViewById(R.id.imgAvatar);
            lnlitem = itemView.findViewById(R.id.lnlitem);
        }


    }



}
