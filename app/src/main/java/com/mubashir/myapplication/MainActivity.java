package com.mubashir.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN = 5000;

    //Variables
    ImageView image;
    TextView logo, slogan;
    Animation animation_top,animation_down;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        image = findViewById(R.id.imageView);
        logo = findViewById(R.id.textView);
        slogan = findViewById(R.id.textView2);
        animation_top = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        animation_down= AnimationUtils.loadAnimation(this, R.anim.down_animation);
        image.setAnimation(animation_top);
        logo.setAnimation(animation_down);
        slogan.setAnimation(animation_down);

        new Handler().postDelayed(new Runnable() {
            @SuppressLint("ObsoleteSdkInt")
            @Override
            public void run() {
             Intent intent=new Intent(getApplicationContext(),login.class);
             startActivity(intent);
             finish();

            }
        }, SPLASH_SCREEN);

    }
}