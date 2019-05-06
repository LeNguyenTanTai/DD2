package com.example.project_android_version_3.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
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

public class SongAdapter extends  RecyclerView.Adapter<SongAdapter.ViewHolder>{



    ArrayList<Song> dataSongs;
    Context context;


    public SongAdapter(ArrayList<Song> dataSongs, Context context) {
        this.dataSongs = dataSongs;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_song,viewGroup,false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.txtName.setText(dataSongs.get(i).getSzName());
        viewHolder.imgHinhAnh.setImageResource(dataSongs.get(i).getnImage());

        viewHolder.carditem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PlayActivity.class);
                context.startActivity(intent);
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
        CardView carditem;


        public ViewHolder( View itemView) {
            super(itemView);
            txtName = (TextView)itemView.findViewById(R.id.txtName);
            imgHinhAnh = (ImageView)itemView.findViewById(R.id.imgAvatar);
            carditem = (CardView) itemView.findViewById(R.id.carditem);

        }


    }



}
