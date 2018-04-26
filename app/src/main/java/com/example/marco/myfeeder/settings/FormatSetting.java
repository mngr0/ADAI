package com.example.marco.myfeeder.settings;


import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;

import com.example.marco.myfeeder.R;

public class FormatSetting extends FragmentActivity {

    String state;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        state="Encoder";
        setContentView(R.layout.format_setting);

        showEncoder();

    }
    public void showEncoder(){
        FragmentEncoderFormat fef = new FragmentEncoderFormat();
        android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.host_frame, fef);
        transaction.commit();
        findViewById(R.id.buttonEncoder).setBackgroundColor(Color.GREEN);
        findViewById(R.id.buttonTimer).setBackgroundColor(Color.RED);
        state="Encoder";
    }

    public void showTimer(){
        FragmentTimerFormat fef = new FragmentTimerFormat();
        android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.host_frame, fef);

        transaction.commit();
        findViewById(R.id.buttonEncoder).setBackgroundColor(Color.RED);
        findViewById(R.id.buttonTimer).setBackgroundColor(Color.GREEN);
        state="Timer";
    }


    public void buttonSwap(View view){
        if(state.equals("Encoder")){
            showTimer();
        }else{
            showEncoder();
        }

    }

}