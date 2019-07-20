package com.horizonlabs.rebelfoods.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.horizonlabs.rebelfoods.R;

public class SplashScreenActivity extends AppCompatActivity {

    ImageView ivLogo;
    public static final int SPLASH_TIME = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.content_splash_screen);
        ivLogo = findViewById(R.id.ivLogo);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(SplashScreenActivity.this, (View) ivLogo, "logo");
                startActivity(new Intent(SplashScreenActivity.this, MainActivity.class), options.toBundle());
                //startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
            }
        }, SPLASH_TIME);
    }
}
