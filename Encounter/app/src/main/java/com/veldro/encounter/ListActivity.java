package com.veldro.encounter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ListActivity extends AppCompatActivity {

    int MAX_LIST_SIZE = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_list);

        Button AddToListButton = (Button) findViewById(R.id.addListButton);
        Button ShowListButton = (Button) findViewById(R.id.showListButton);


        final EditText AddToListString = (EditText) findViewById(R.id.AddToListStringEditText);

        final ArrayList<String> List = new ArrayList<>();

        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, List);


        ListView Lv = (ListView) findViewById(R.id.ListView);
        Lv.setAdapter(adapter);


        Lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                List.remove(i);
                adapter.notifyDataSetChanged();
            }
        });

        AddToListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (List.size() < 3) {
                    if (AddToListString.getText().toString().length() > 1) {
                        String input = AddToListString.getText().toString();
                        List.add(input);

                        AddToListString.setText("");

                        adapter.notifyDataSetChanged();
                    }
                } else {
                    Toast.makeText(ListActivity.this, "Max 3!",
                            Toast.LENGTH_SHORT).show();

                }
            }
        });

        ShowListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ListActivity.this, ListFullscreenActivity.class);
                int ArraySize = List.size();

                i.putExtra("size", ArraySize);
                for(int k = 0; k< ArraySize;k++){
                    i.putExtra(""+k,List.get(k));
                }

                startActivity(i);
            }
        });


    }


}
