package com.veldro.encounter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SnapchatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snapchat);

        Button SaveSnapchatButton = (Button)findViewById(R.id.saveSnapchatButton);
        Button ShowSnapchatButton = (Button) findViewById(R.id.showSnapchatButton);


        final EditText snapchatNameEditText= (EditText) findViewById(R.id.Snapchat_Name_EditText);


        loadSavedPreferences();

        ShowSnapchatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String SnapchatName = snapchatNameEditText.getText().toString();
                Intent i = new Intent(SnapchatActivity.this, SnapchatFullscreenActivity.class);
                i.putExtra("SnapchatName", SnapchatName);
                startActivity(i);

            }
        });

        SaveSnapchatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savePreferences("SnapchatName",snapchatNameEditText.getText().toString());

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



        String tag = sharedPreferences.getString("SnapchatName", "");

        EditText SnapchatName = (EditText) findViewById(R.id.Snapchat_Name_EditText);


        SnapchatName.setText(tag);




    }
}
