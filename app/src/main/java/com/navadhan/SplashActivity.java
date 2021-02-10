package com.navadhan;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    Animation animZoomIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_splash);
        /*animZoomIn = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.anim_zoomin);*/
        /*RotateAnimation anim = new RotateAnimation(0f, 350f, 15f, 15f);
        anim.setInterpolator(new LinearInterpolator());
        anim.setRepeatCount(Animation.INFINITE);
        anim.setDuration(700);*/

// Start animating the image
        final ImageView splash = (ImageView) findViewById(R.id.splash_image);
        //splash.startAnimation(anim);
        //splash.startAnimation(animZoomIn);

// Later.. stop the animation

        Thread background = new Thread() {
            public void run() {
                try {
                    Animation animZoomOut = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.anim_zoomin);
                    splash.startAnimation(animZoomOut);
                    // Thread will sleep for 5 seconds
                    sleep(2*1000);

                    // After 5 seconds redirect to another intent
                    //splash.setAnimation(null);
                    Intent i=new Intent(getBaseContext(), com.navadhan.MainActivity.class);
                    startActivity(i);

                    //Remove activity
                    finish();
                } catch (Exception e) {
                }
            }
        };
        // start thread
        background.start();
    }
}