package com.veldro.encounter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

public class InfoFullscreenActivity extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.activity_info_fullscreen);

        FontFitTextView nameTextview = (FontFitTextView) findViewById(R.id.name_fullscreen_FontFitTextView);
        FontFitTextView numberTextview = (FontFitTextView) findViewById(R.id.number_fullscreen_FontFitTextView);

        Intent intent = getIntent();

        String Name = intent.getExtras().getString("name");
        String Number = intent.getExtras().getString("number");

        nameTextview.setText(Name);
        numberTextview.setText(Number);



    }
}
