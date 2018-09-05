package com.example.marco.myfeeder.format_edit;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.app.AlertDialog;
import android.widget.Toast;

import com.example.marco.myfeeder.Configuration;
import com.example.marco.myfeeder.R;
import com.example.marco.myfeeder.ble.BluetoothChatService;

public class FormatEdit extends AppCompatActivity {
    RecyclerEditFragment mFragment;
    private Configuration mConfiguration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_format_edit);
        Intent intent = getIntent();
        Uri data = intent.getData();
        int index=Integer.parseInt(data.toString());
        Log.d("test",data.toString());
        mConfiguration = BluetoothChatService.getInstance().getmConfiguration();

        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            mFragment = new RecyclerEditFragment();
            mFragment.setIndex(index);
            transaction.replace(R.id.edit_content_fragment, mFragment);
            transaction.commit();
        }

    }


    @Override
    protected void onStart() {
        super.onStart();
    }


    public void addElement(View view){
        mFragment.addElementIn();
    }


    public void returnResult(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Title");
// I'm using fragment here so I'm using getView() to provide ViewGroup
// but you can provide here any other instance of ViewGroup from your Fragment / Activity
        View viewInflated = LayoutInflater.from(this).inflate(R.layout.text_name_input, (ViewGroup)this.mFragment.getView(), false);
// Set up the input
        final EditText input = (EditText) viewInflated.findViewById(R.id.input);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        builder.setView(viewInflated);

// Set up the buttons
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Log.d("DIALOG", input.getText().toString());
                Intent result = new Intent("com.example.RESULT_ACTION", Uri.parse("content://result_uri"));
                setResult(Activity.RESULT_OK, result);
                finish();
            }
        });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();

    }

}
