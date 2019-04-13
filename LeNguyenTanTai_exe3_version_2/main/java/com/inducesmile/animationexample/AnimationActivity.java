package com.inducesmile.animationexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import pl.droidsonroids.gif.GifTextView;

public class AnimationActivity extends AppCompatActivity {

    private static final String TAG = AnimationActivity.class.getSimpleName();
    /*private ImageView mImageView;*/
    private GifTextView mImageView,mImageView2;
    private Button buttonZoom;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        mImageView = (GifTextView) findViewById(R.id.imageView);
        mImageView.setBackgroundResource(R.drawable.dong);

        mImageView2 = (GifTextView) findViewById(R.id.imageView2);
        mImageView2.setBackgroundResource(R.drawable.hinhdong);

        buttonZoom = (Button) findViewById(R.id.buttonZoom);
       /* buttonZoom.setBackgroundResource(R.drawable.buttonZoom);*/
       /* mImageView.setImageResource(R.drawable.hinhtron);*/
    }

    public void zoom(View view) {
        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_in);
        mImageView.startAnimation(animation1);
        Animation animation12 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotateee);
        mImageView2.startAnimation(animation12);

    }

    public void move(View view) {
        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anmove);
        mImageView2.startAnimation(animation1);
        Animation animation12 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move);
        mImageView.startAnimation(animation12);

    }

    public void blink(View view) {
        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink);
        mImageView.startAnimation(animation1);
        Animation animation12 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rote);
        mImageView2.startAnimation(animation12);
    }

    public void slideUp(View view) {
        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
        mImageView2.startAnimation(animation1);
        Animation animation12 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
        mImageView.startAnimation(animation12);
    }

    public void fade(View view) {
        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
        mImageView.startAnimation(animation1);
        Animation animation12 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rote);
        mImageView2.startAnimation(animation12);
    }



    public void rotate(View view) {
        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
        mImageView.startAnimation(animation1);
        Animation animation12 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rote);
        mImageView2.startAnimation(animation1);
    }

    public void anrotate(View view) {
        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anrotate);
        mImageView.startAnimation(animation1);
        Animation animation12 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotateee);
        mImageView2.startAnimation(animation12);
    }
    public void slide(View view) {
        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide);
        Animation animation12 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
        mImageView.startAnimation(animation1);
        mImageView2.startAnimation(animation12);

    }
}
