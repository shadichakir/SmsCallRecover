package dev.justforyou.smscallrecover;


import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class DateActivity extends AppCompatActivity {

    InterstitialAd mInterstitialAd;
    private InterstitialAd interstitial;
    Button btn1, btn2,btn4,btn3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_date);
            //Admob
            AdRequest adRequest = new AdRequest.Builder().build();
            AdView mAdView = findViewById(R.id.ad_view);
            mAdView.loadAd(adRequest);

            // Prepare the Interstitial Ad
            interstitial = new InterstitialAd(DateActivity.this);
            interstitial.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
            interstitial.loadAd(adRequest);
            interstitial.setAdListener(new AdListener() {
                public void onAdLoaded() {
                    displayInterstitial();
                }
            });
            final TextView t = findViewById(R.id.textview1);
            btn2 = findViewById(R.id.btn2);
            btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(t.getText().toString().matches(""))
                    {
                      Toast.makeText(getApplicationContext(),"المرجو إختيار التاريخ",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {

                        Intent i = new Intent(DateActivity.this,LoadingActivity.class);
                        startActivity(i);
                    }

                }
            });
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }





    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new MyDatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }
    public void displayInterstitial() {
        if (interstitial.isLoaded()) {
            interstitial.show();
        }
    }
}
