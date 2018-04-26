package com.example.marco.myfeeder.format_edit;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
        Intent intent = getIntent();
        Uri data = intent.getData();
        Log.d("test",data.toString());


        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            RecyclerEditFragment fragment = new RecyclerEditFragment();
            transaction.replace(R.id.edit_content_fragment, fragment);
            transaction.commit();
        }
        
    }


    public void returnResult(View view){
        Intent result = new Intent("com.example.RESULT_ACTION", Uri.parse("content://result_uri"));
        setResult(Activity.RESULT_OK, result);
        finish();

    }


}