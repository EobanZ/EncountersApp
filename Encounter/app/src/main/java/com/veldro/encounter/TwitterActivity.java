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

import org.w3c.dom.Text;

public class TwitterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twitter);

        final EditText twitterTagEdit= (EditText) findViewById(R.id.twitter_tag_edit);
        final Button showTwitterButton = (Button) findViewById(R.id.showTwitterButton);
        final Button saveTwitterButton = (Button) findViewById(R.id.saveTwitterButton);
        final Button generateQRtwitterButton = (Button)findViewById(R.id.twitter_QR_generate_button);
        final TextView twitterNameTextView = (TextView)findViewById(R.id.twitterNameTextView);
        final TextView twitterTapTextView = (TextView) findViewById(R.id.twitter_tap_text);
        final ImageView twitterQrImage = (ImageView)findViewById(R.id.twitter_QR_code_img);

        loadSavedPreferences();




        showTwitterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String twitterTag = twitterTagEdit.getText().toString();
                Intent i = new Intent(TwitterActivity.this, TwitterFullscreenActivity.class);
                i.putExtra("tag", "@" + twitterTag);
                startActivity(i);

            }
        });

        saveTwitterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savePreferences("tag",twitterTagEdit.getText().toString());

            }
        });

        twitterQrImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (twitterTagEdit.getVisibility() == View.VISIBLE) {

                    showTwitterButton.setVisibility(View.GONE);
                    saveTwitterButton.setVisibility(View.GONE);
                    generateQRtwitterButton.setVisibility(View.GONE);
                    twitterTagEdit.setVisibility(View.GONE);
                    twitterNameTextView.setVisibility(View.GONE);
                    twitterTapTextView.setVisibility(View.GONE);
                    twitterQrImage.setMinimumHeight(1024);
                    twitterQrImage.setMinimumWidth(1024);
                    twitterQrImage.setMaxHeight(1024);
                    twitterQrImage.setMaxWidth(1024);
                } else if (twitterTagEdit.getVisibility() == View.GONE) {
                    showTwitterButton.setVisibility(View.VISIBLE);
                    saveTwitterButton.setVisibility(View.VISIBLE);
                    generateQRtwitterButton.setVisibility(View.VISIBLE);
                    twitterTagEdit.setVisibility(View.VISIBLE);
                    twitterNameTextView.setVisibility(View.VISIBLE);
                    twitterTapTextView.setVisibility(View.VISIBLE);
                    twitterQrImage.setMinimumHeight(512);
                    twitterQrImage.setMinimumWidth(512);
                    twitterQrImage.setMaxHeight(512);
                    twitterQrImage.setMaxWidth(512);
                }
            }
        });

        generateQRtwitterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                QRCodeWriter writer = new QRCodeWriter();
                try {
                    BitMatrix bitMatrix = writer.encode("https://www.twitter.com/"+twitterTagEdit.getText().toString(), BarcodeFormat.QR_CODE, 512, 512);
                    int width = bitMatrix.getWidth();
                    int height = bitMatrix.getHeight();
                    Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
                    for (int x = 0; x < width; x++) {
                        for (int y = 0; y < height; y++) {
                            bmp.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                        }
                    }
                    ((ImageView) findViewById(R.id.twitter_QR_code_img)).setImageBitmap(bmp);

                } catch (WriterException e) {
                    e.printStackTrace();
                }
                twitterTapTextView.setVisibility(View.VISIBLE);
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



            String tag = sharedPreferences.getString("tag", "");

            EditText Tag = (EditText) findViewById(R.id.twitter_tag_edit);


            Tag.setText(tag);




    }
}
