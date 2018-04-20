package com.example.marco.myfeeder;


import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

public class FormatSetting extends FragmentActivity implements FragmentEncoderFormat.OnFragmentInteractionListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.format_setting);
        FrameLayout fl = (FrameLayout) findViewById(R.id.host_frame);
        //fl.

        FragmentEncoderFormat fef = new FragmentEncoderFormat();


        android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed
        transaction.replace(R.id.host_frame, fef);
        transaction.addToBackStack(null);

// Commit the transaction
        transaction.commit();

    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
