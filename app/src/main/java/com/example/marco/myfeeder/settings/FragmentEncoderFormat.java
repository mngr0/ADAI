package com.example.marco.myfeeder.settings;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.marco.myfeeder.R;


public class FragmentEncoderFormat extends Fragment {

    public FragmentEncoderFormat() {   }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_encoder_format, container, false);

        return rootView;
    }

}
