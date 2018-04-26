package com.example.marco.myfeeder.settings;


import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.example.marco.myfeeder.R;

public class FormatSetting extends FragmentActivity implements FragmentEncoderFormat.OnFragmentInteractionListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.format_setting);
        FragmentEncoderFormat fef = new FragmentEncoderFormat();
        android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.host_frame, fef);

        transaction.commit();

    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
