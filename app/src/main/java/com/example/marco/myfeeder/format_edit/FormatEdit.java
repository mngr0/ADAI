package com.example.marco.myfeeder.format_edit;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.marco.myfeeder.R;

public class FormatEdit extends AppCompatActivity {
    RecyclerEditFragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_format_edit);
        Intent intent = getIntent();
        Uri data = intent.getData();
        Log.d("test",data.toString());


        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            mFragment = new RecyclerEditFragment();
            transaction.replace(R.id.edit_content_fragment, mFragment);
            transaction.commit();
        }

    }


    public void returnResult(View view){
        Intent result = new Intent("com.example.RESULT_ACTION", Uri.parse("content://result_uri"));
        setResult(Activity.RESULT_OK, result);
        finish();

    }

    public void addElement(View view){
        mFragment.addElementIn(view);

    }


}
