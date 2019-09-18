package com.veldro.encounter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ListFullscreenActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_fullscreen);

        FontFitTextView TopTextView = (FontFitTextView)findViewById(R.id.fullscreenTop_FontFitTextView);
        FontFitTextView MiddleTextView = (FontFitTextView)findViewById(R.id.fullscreenMiddle_FontFitTextView);
        FontFitTextView BottomTextView = (FontFitTextView)findViewById(R.id.fullscreenBottom_FontFitTextView);

        Intent i = getIntent();
        int aSize = i.getExtras().getInt("size");

        for(int k = 0; k<aSize;k++){
            i.getExtras().getString(""+k);


        }

        if(aSize == 1){
            String Top = i.getExtras().getString("0");
            TopTextView.setText(Top);
            MiddleTextView.setVisibility(View.GONE);
            BottomTextView.setVisibility(View.GONE);
        }
        else if(aSize == 2){
            String Top = i.getExtras().getString("0");
            TopTextView.setText(Top);
            String Middle = i.getExtras().getString("1");
            MiddleTextView.setText(Middle);
            BottomTextView.setVisibility(View.GONE);
        }
        else if(aSize == 3){
            String Top = i.getExtras().getString("0");
            TopTextView.setText(Top);
            String Middle = i.getExtras().getString("1");
            MiddleTextView.setText(Middle);
            String Bottom = i.getExtras().getString("2");
            BottomTextView.setText(Bottom);
        }







    }
}
