package com.example.marco.myfeeder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.marco.myfeeder.format_edit.FormatEdit;
import com.example.marco.myfeeder.settings.FormatSetting;

public class FormatSelection extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.format_selection);

        //RadioGroup rg = new RadioGroup(getApplicationContext());
        //rg.addView(findViewById(R.id.radioButton));
        //rg.addView(findViewById(R.id.radioButton2));

    }
    public void showFormatEdit(View view) {

        Toast toast = Toast.makeText(getApplicationContext(), "go format_setting!", Toast.LENGTH_SHORT);
        toast.show();

        Intent intent = new Intent(this, FormatEdit.class);
        startActivity(intent);
    }
}
