package com.example.marco.myfeeder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.marco.myfeeder.bluetooth.BluetoothConnect;
import com.example.marco.myfeeder.settings.FormatSetting;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showBLE(View view) {

        Toast toast = Toast.makeText(getApplicationContext(), "go ble!", Toast.LENGTH_SHORT);
        toast.show();

        Intent intent = new Intent(this, BluetoothConnect.class);
        startActivity(intent);
    }

    public void showFormatSetting(View view) {

        Toast toast = Toast.makeText(getApplicationContext(), "go format_setting!", Toast.LENGTH_SHORT);
        toast.show();

        Intent intent = new Intent(this, FormatSetting.class);
        startActivity(intent);
    }



    public void showFormatSelection(View view) {

        Toast toast = Toast.makeText(getApplicationContext(), "go format_selection!", Toast.LENGTH_SHORT);
        toast.show();

        Intent intent = new Intent(this, FormatSelection.class);
        startActivity(intent);
    }
}
