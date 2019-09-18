package com.veldro.encounter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class InstagramActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instagram);

        loadSavedPreferences();

        final Button InstagramSaveButton = (Button) findViewById(R.id.instagram_save_button);
        final Button InstagramShowButton = (Button) findViewById(R.id.showInstagramButton);
        final Button InstagramGenerateQrButton = (Button) findViewById(R.id.instagram_generateQR_button);
        final TextView InstagramNameTextView = (TextView) findViewById(R.id.InstagramNameTextView);
        final TextView InstagramTapTextView = (TextView) findViewById(R.id.instagram_tap_text);

        final ImageView InstagramQrImg = (ImageView) findViewById(R.id.instagram_QR_img);

        final EditText InstagramNameEditText = (EditText)findViewById(R.id.instagram_name_editText);


        InstagramSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savePreferences("InstagramName", InstagramNameEditText.getText().toString());

            }
        });

        InstagramShowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(InstagramActivity.this,InstagramFullscreenActivity.class);
                i.putExtra("InstagramName",InstagramNameEditText.getText().toString());
                startActivity(i);
            }
        });

        InstagramQrImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (InstagramNameEditText.getVisibility() == View.VISIBLE) {

                    InstagramShowButton.setVisibility(View.GONE);
                    InstagramSaveButton.setVisibility(View.GONE);
                    InstagramGenerateQrButton.setVisibility(View.GONE);
                    InstagramNameEditText.setVisibility(View.GONE);
                    InstagramNameTextView.setVisibility(View.GONE);
                    InstagramTapTextView.setVisibility(View.GONE);
                    InstagramQrImg.setMinimumHeight(1024);
                    InstagramQrImg.setMinimumWidth(1024);
                    InstagramQrImg.setMaxHeight(1024);
                    InstagramQrImg.setMaxWidth(1024);
                } else if (InstagramNameEditText.getVisibility() == View.GONE) {
                    InstagramShowButton.setVisibility(View.VISIBLE);
                    InstagramSaveButton.setVisibility(View.VISIBLE);
                    InstagramGenerateQrButton.setVisibility(View.VISIBLE);
                    InstagramNameEditText.setVisibility(View.VISIBLE);
                    InstagramNameTextView.setVisibility(View.VISIBLE);
                    InstagramTapTextView.setVisibility(View.VISIBLE);
                    InstagramQrImg.setMinimumHeight(512);
                    InstagramQrImg.setMinimumWidth(512);
                    InstagramQrImg.setMaxHeight(512);
                    InstagramQrImg.setMaxWidth(512);
                }
            }
        });

        InstagramGenerateQrButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QRCodeWriter writer = new QRCodeWriter();

                String InstagramUrl = "https://www.Instagram.com/"+InstagramNameEditText.getText().toString();



                try {
                    BitMatrix bitMatrix = writer.encode(InstagramUrl, BarcodeFormat.QR_CODE, 512, 512);
                    int width = bitMatrix.getWidth();
                    int height = bitMatrix.getHeight();
                    Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
                    for (int x = 0; x < width; x++) {
                        for (int y = 0; y < height; y++) {
                            bmp.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                        }
                    }
                    ((ImageView) findViewById(R.id.instagram_QR_img)).setImageBitmap(bmp);

                } catch (WriterException e) {
                    e.printStackTrace();
                }
                InstagramTapTextView.setVisibility(View.VISIBLE);
            }
        });


    }

    private void savePreferences(String key, String value) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();

        Toast.makeText(this, "Saved!",
                Toast.LENGTH_SHORT).show();

    }

    private void loadSavedPreferences() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);


        String Name = sharedPreferences.getString("InstagramName", "");

        EditText InstagramName = (EditText) findViewById(R.id.instagram_name_editText);


        InstagramName.setText(Name);



    }
}
