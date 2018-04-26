package com.example.marco.myfeeder.format_edit;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.marco.myfeeder.R;
import com.example.marco.myfeeder.bluetooth.RecyclerViewFragment;
import com.example.marco.myfeeder.settings.FragmentEncoderFormat;

public class FormatEdit extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_format_edit);
        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            RecyclerEditFragment fragment = new RecyclerEditFragment();
            transaction.replace(R.id.edit_content_fragment, fragment);
            transaction.commit();
        }
    }


}
