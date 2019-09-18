package com.veldro.encounter;

import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class QrScanActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_scan);

        mScannerView = new ZXingScannerView(this);
        setContentView(mScannerView);
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result result) {


        //mScannerView.resumeCameraPreview(MainActivity.this);
        String sResult = result.getText();


        String Name;
        String Number;
        String Email;
        String QrUrl;
        String[] tokens = sResult.split(";");  //MECARD:N:NAME;TEL:999;EMAIL:blabla@bla.de;
        if (sResult.startsWith("MECARD:")) {

            String[] parts = sResult.split(";"); //0 MECARD:N:NAME 1 TEL:999 2 EMAIL:blablabla@bla.de
            Name = parts[0].substring(9);
            Number = parts[1].substring(4);
            Email = parts[2].substring(6);

            Intent intent = new Intent(Intent.ACTION_INSERT);
            intent.setType(ContactsContract.Contacts.CONTENT_TYPE);
            intent.putExtra(ContactsContract.Intents.Insert.NAME, Name);
            intent.putExtra(ContactsContract.Intents.Insert.PHONE, Number);
            intent.putExtra(ContactsContract.Intents.Insert.EMAIL, Email);

            startActivity(intent);

        //sResult.startsWith("http://") || sResult.startsWith("www.") || sResult.contains(".com") || sResult.contains(".de")
        } else{
            QrUrl = sResult;
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(QrUrl));
            startActivity(i);

        }
        mScannerView.resumeCameraPreview(this);




    }

}
