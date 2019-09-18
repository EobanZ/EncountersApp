package com.veldro.encounter;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class MainActivity extends AppCompatActivity  {

    private ZXingScannerView mScannerView;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final EditText inputText = (EditText) findViewById(R.id.input_edit_text);

        Button showButton = (Button) findViewById(R.id.show_Button);

        FontFitTextView infoText = (FontFitTextView) findViewById(R.id.info_textView);
        FontFitTextView twitterText = (FontFitTextView) findViewById(R.id.twitter_textView);
        FontFitTextView instagramText = (FontFitTextView) findViewById(R.id.instagram_textView);
        FontFitTextView listText = (FontFitTextView) findViewById(R.id.list_textView);
        FontFitTextView snapchatText = (FontFitTextView) findViewById(R.id.snapchat_textView);
        FontFitTextView facebookText = (FontFitTextView) findViewById(R.id.facebook_textView);

        Button scanButton = (Button) findViewById(R.id.scan_button);


        infoText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent j = new Intent(MainActivity.this, InfoActivity.class);
                startActivity(j);

            }
        });

        twitterText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, TwitterActivity.class);
                startActivity(i);

            }
        });

        instagramText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, InstagramActivity.class);
                startActivity(i);

            }
        });

        listText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ListActivity.class);
                startActivity(i);

            }
        });

        snapchatText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, SnapchatActivity.class);
                startActivity(i);

            }
        });

        facebookText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, FacebookActivity.class);
                startActivity(i);

            }
        });


        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String ShowInput = inputText.getText().toString();
                Log.v("INPUT", ShowInput);

                Intent i = new Intent(MainActivity.this, ShowFullscreenActivity.class);
                i.putExtra("value", ShowInput);

                if (ShowInput.length() >= 1) {
                    startActivity(i);
                }


            }
        });

        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, QrScanActivity.class);
                startActivity(i);

            }
        });


    }



}
