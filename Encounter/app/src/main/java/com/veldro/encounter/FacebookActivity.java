package com.veldro.encounter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Color;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import org.w3c.dom.Text;

import java.net.URL;

import static android.R.attr.width;

public class FacebookActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook);


        loadSavedPreferences();

        final Button FacebookShowButton = (Button) findViewById(R.id.showFacebookButton);
        final Button FacebookSaveButton = (Button) findViewById(R.id.facebook_save_button);
        final Button FacebookQRButton = (Button) findViewById(R.id.facebook_QR_Generate_Button);
        final EditText FacebookNameEditText = (EditText) findViewById(R.id.facebook_name_editText);
        final EditText FacebookUrlEditText = (EditText) findViewById(R.id.facebook_url_editText);
        final ImageView QRFacebook = (ImageView) findViewById(R.id.img_qr_code_image_facebook);
        final TextView FacebookNameTextView = (TextView) findViewById(R.id.FbNameTV);
        final TextView FacebookUrlTextView = (TextView) findViewById(R.id.FbUrlTV);
        final TextView FacebookTapTextView = (TextView) findViewById(R.id.facebook_tap_text);

        FacebookSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savePreferences("FbName", FacebookNameEditText.getText().toString());
                savePreferences("FbUrl", FacebookUrlEditText.getText().toString());


            }
        });

        FacebookShowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(FacebookActivity.this, FacebookFullscreenActivity.class);
                i.putExtra("FbName", FacebookNameEditText.getText().toString());
                startActivity(i);
            }
        });

        QRFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (FacebookNameEditText.getVisibility() == View.VISIBLE) {

                    FacebookShowButton.setVisibility(View.GONE);
                    FacebookSaveButton.setVisibility(View.GONE);
                    FacebookQRButton.setVisibility(View.GONE);
                    FacebookNameEditText.setVisibility(View.GONE);
                    FacebookUrlEditText.setVisibility(View.GONE);
                    FacebookNameTextView.setVisibility(View.GONE);
                    FacebookUrlTextView.setVisibility(View.GONE);
                    FacebookTapTextView.setVisibility(View.GONE);
                    QRFacebook.setMinimumHeight(1024);
                    QRFacebook.setMinimumWidth(1024);
                    QRFacebook.setMaxHeight(1024);
                    QRFacebook.setMaxWidth(1024);
                } else if (FacebookNameEditText.getVisibility() == View.GONE) {
                    FacebookShowButton.setVisibility(View.VISIBLE);
                    FacebookSaveButton.setVisibility(View.VISIBLE);
                    FacebookQRButton.setVisibility(View.VISIBLE);
                    FacebookNameEditText.setVisibility(View.VISIBLE);
                    FacebookUrlEditText.setVisibility(View.VISIBLE);
                    FacebookNameTextView.setVisibility(View.VISIBLE);
                    FacebookUrlTextView.setVisibility(View.VISIBLE);
                    FacebookTapTextView.setVisibility(View.VISIBLE);
                    QRFacebook.setMinimumHeight(512);
                    QRFacebook.setMinimumWidth(512);
                    QRFacebook.setMaxHeight(512);
                    QRFacebook.setMaxWidth(512);
                }

            }
        });

        FacebookQRButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                QRCodeWriter writer = new QRCodeWriter();
                String fbUrl = FacebookUrlEditText.getText().toString();
                if(fbUrl.startsWith("http://")){
                    fbUrl = fbUrl.substring(7);
                }
                else if(fbUrl.startsWith("https://")){
                    fbUrl = fbUrl.substring(8);
                }
                //if(!fbUrl.startsWith("http://")||!fbUrl.startsWith("www.") || !fbUrl.startsWith("https://")){}
                    fbUrl = "https://"+fbUrl;

                try {
                    BitMatrix bitMatrix = writer.encode(fbUrl, BarcodeFormat.QR_CODE, 512, 512);
                    int width = bitMatrix.getWidth();
                    int height = bitMatrix.getHeight();
                    Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
                    for (int x = 0; x < width; x++) {
                        for (int y = 0; y < height; y++) {
                            bmp.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                        }
                    }
                    ((ImageView) findViewById(R.id.img_qr_code_image_facebook)).setImageBitmap(bmp);

                } catch (WriterException e) {
                    e.printStackTrace();
                }

                FacebookTapTextView.setVisibility(View.VISIBLE);

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


        String Name = sharedPreferences.getString("FbName", "");
        String url = sharedPreferences.getString("FbUrl", "");
        EditText FbName = (EditText) findViewById(R.id.facebook_name_editText);
        EditText FbUrl = (EditText) findViewById(R.id.facebook_url_editText);

        FbName.setText(Name);
        FbUrl.setText(url);


    }


}

