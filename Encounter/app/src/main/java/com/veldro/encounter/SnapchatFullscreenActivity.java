package com.veldro.encounter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

public class SnapchatFullscreenActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_snapchat_fullscreen);

        FontFitTextView SnapchatFontFitTextView = (FontFitTextView)findViewById(R.id.snapchat_fullscreen_FontFitTextView);

        Intent i = getIntent();
        String SnapchatName = i.getExtras().getString("SnapchatName");

        SnapchatFontFitTextView.setText(SnapchatName);
    }
}
