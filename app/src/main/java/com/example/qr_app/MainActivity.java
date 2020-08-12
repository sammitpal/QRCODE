package com.example.qr_app;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class MainActivity extends AppCompatActivity {

    EditText qrvalue;
    Button generate;
    ImageView qrcode;

    InterstitialAd interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        qrvalue = (EditText) findViewById(R.id.value);
        generate = (Button) findViewById(R.id.button);
        qrcode = (ImageView) findViewById(R.id.qrcode);
        MobileAds.initialize(this, "ca-app-pub-8078038656648358~1772890360");
        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId("ca-app-pub-8078038656648358/1879458306");

        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interstitialAd.loadAd(new AdRequest.Builder().build());
                interstitialAd.show();
                String data = qrvalue.getText().toString();
                QRGEncoder qrgEncoder = new QRGEncoder(data,null, QRGContents.Type.TEXT,500);
                Bitmap qrbits = qrgEncoder.getBitmap();
                qrcode.setImageBitmap(qrbits);

            }
        });
    }
}