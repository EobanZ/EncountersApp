package com.veldro.encounter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import org.w3c.dom.Text;

public class InfoActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        loadSavedPreferences();

        final EditText Name = (EditText) findViewById(R.id.name_info_TextEdit);
        final EditText Number = (EditText) findViewById(R.id.number_info_TextEdit);
        final EditText Email = (EditText) findViewById(R.id.email_info_TextEdit);
        final TextView NameTextView = (TextView) findViewById(R.id.infoNameTextView);
        final TextView NumberTextView = (TextView) findViewById(R.id.infoNumberTextView);
        final TextView EmailTextView = (TextView) findViewById(R.id.infoEmailTextView);
        final Button saveButton = (Button) findViewById(R.id.save_info_button);
        final Button showInfoButton = (Button) findViewById(R.id.show_info_button);
        final Button generateQrButton = (Button) findViewById(R.id.infoGenerateQrButton);
        final ImageView infoQrimg = (ImageView) findViewById(R.id.infoQr_img);
        final CheckBox checkBoxTextOnly = (CheckBox) findViewById(R.id.number_only_checkbox);
        final TextView infoTapTextView = (TextView) findViewById(R.id.info_tap_text);




        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savePreferences("name", Name.getText().toString());
                savePreferences("number", Number.getText().toString());
                savePreferences("email", Email.getText().toString());

            }
        });

        showInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(checkBoxTextOnly.isChecked()){
                    Intent i = new Intent(InfoActivity.this, ShowFullscreenActivity.class);
                    i.putExtra("value", Number.getText().toString());
                    startActivity(i);
                }
                else{
                    Intent i = new Intent(InfoActivity.this, InfoFullscreenActivity.class);
                    i.putExtra("name", Name.getText().toString());
                    i.putExtra("number", Number.getText().toString());
                    startActivity(i);

                }



            }
        });

        generateQrButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {  //MECARD:N:NAME;TEL:999;EMAIL:blabla@bla.de;
                String VCardString = "MECARD:N:";
                VCardString += Name.getText().toString()+";";
                VCardString += "TEL:"+Number.getText().toString()+";";
                VCardString += "EMAIL:"+Email.getText().toString()+";";


                QRCodeWriter writer = new QRCodeWriter();
                try {
                    BitMatrix bitMatrix = writer.encode(VCardString, BarcodeFormat.QR_CODE, 512, 512);
                    int width = bitMatrix.getWidth();
                    int height = bitMatrix.getHeight();
                    Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
                    for (int x = 0; x < width; x++) {
                        for (int y = 0; y < height; y++) {
                            bmp.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                        }
                    }
                    ((ImageView) findViewById(R.id.infoQr_img)).setImageBitmap(bmp);

                } catch (WriterException e) {
                    e.printStackTrace();
                }

                infoTapTextView.setVisibility(View.VISIBLE);
            }
        });

        infoQrimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Name.getVisibility() == View.VISIBLE) {

                    showInfoButton.setVisibility(View.GONE);
                    saveButton.setVisibility(View.GONE);
                    generateQrButton.setVisibility(View.GONE);
                    Name.setVisibility(View.GONE);
                    Number.setVisibility(View.GONE);
                    Email.setVisibility(View.GONE);
                    NameTextView.setVisibility(View.GONE);
                    NumberTextView.setVisibility(View.GONE);
                    EmailTextView.setVisibility(View.GONE);
                    checkBoxTextOnly.setVisibility(View.GONE);
                    infoTapTextView.setVisibility(View.GONE);
                    infoQrimg.setMinimumHeight(1024);
                    infoQrimg.setMinimumWidth(1024);
                    infoQrimg.setMaxHeight(1024);
                    infoQrimg.setMaxWidth(1024);
                } else if (Name.getVisibility() == View.GONE) {
                    showInfoButton.setVisibility(View.VISIBLE);
                    saveButton.setVisibility(View.VISIBLE);
                    generateQrButton.setVisibility(View.VISIBLE);
                    Name.setVisibility(View.VISIBLE);
                    Number.setVisibility(View.VISIBLE);
                    Email.setVisibility(View.VISIBLE);
                    NameTextView.setVisibility(View.VISIBLE);
                    NumberTextView.setVisibility(View.VISIBLE);
                    EmailTextView.setVisibility(View.VISIBLE);
                    checkBoxTextOnly.setVisibility(View.VISIBLE);
                    infoTapTextView.setVisibility(View.VISIBLE);
                    infoQrimg.setMinimumHeight(512);
                    infoQrimg.setMinimumWidth(512);
                    infoQrimg.setMaxHeight(512);
                    infoQrimg.setMaxWidth(512);
                }
            }
        });




    }

    private void loadSavedPreferences() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);


        if (sharedPreferences.getString("name", "") != null) {
            String name = sharedPreferences.getString("name", "");
            String number = sharedPreferences.getString("number", "");
            String email = sharedPreferences.getString("email","");
            String website = sharedPreferences.getString("website","");
            final EditText Name = (EditText) findViewById(R.id.name_info_TextEdit);
            final EditText Number = (EditText) findViewById(R.id.number_info_TextEdit);
            final EditText Email = (EditText) findViewById(R.id.email_info_TextEdit);


            Name.setText(name);
            Number.setText(number);
            Email.setText(email);

        }


    }

    private void savePreferences(String key, String value) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();

        Toast.makeText(this, "Saved!",
                Toast.LENGTH_SHORT).show();

    }


}
