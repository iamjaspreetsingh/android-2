package com.procleus.brime;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.content.SharedPreferences;
import android.transition.Fade;
import android.view.View;

public class SplashScreen extends Activity {

    private final int SPLASH_DISPLAY_LENGTH = 3000;
    SharedPreferences sharedPreferences = null;



    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        sharedPreferences = getSharedPreferences("com.procleus.brime", MODE_PRIVATE);
        setContentView(R.layout.activity_splash);


        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);


        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                if (sharedPreferences.getBoolean("firstrun", true)) {
                    Intent mainIntent = new Intent(SplashScreen.this,IntroActivity.class);
                    startActivity(mainIntent);
                    SplashScreen.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    sharedPreferences.edit().putBoolean("firstrun", false).commit();
                    finish();
                }
                else {
                    Intent mainIntent = new Intent(SplashScreen.this,SigninActivity.class);
                    startActivity(mainIntent);
                    finish();
                }
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        sharedPreferences = getSharedPreferences("com.procleus.brime", MODE_PRIVATE);
    }
}