package com.example.marco.myfeeder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.support.v4.app.Fragment;

import android.view.View;
import android.view.ViewGroup;

public class FragmentGenericFormat extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_timer_format, container, false);
    }
}