package com.veldro.encounter;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

public class ShowFullscreenActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_show_fullscreen);


        FontFitTextView fullscreenText = (FontFitTextView) findViewById(R.id.fullscreen_FontFitTextView);


        Intent intent = getIntent();
        String Text = intent.getExtras().getString("value");



        if (Text != null) {
            fullscreenText.setText(Text);
        }


    }
}
