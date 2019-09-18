package com.veldro.encounter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

public class TwitterFullscreenActivity extends Activity {

    FontFitTextView TwitterFullScreenText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_twitter_fullscreen);

        TwitterFullScreenText = (FontFitTextView) findViewById(R.id.twitter_fullscreen_FontFitTextView);



        Intent intent = getIntent();
        String Text = intent.getExtras().getString("tag");

        TwitterFullScreenText.setText(Text);

    }
}
