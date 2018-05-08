package com.example.marco.myfeeder;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.marco.myfeeder.ble.BluetoothChatService;
import com.example.marco.myfeeder.bluetooth_ui.BluetoothConnect;
import com.example.marco.myfeeder.settings.FormatSetting;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showBLE(View view) {
        Intent intent = new Intent(this, BluetoothConnect.class);
        startActivity(intent);
    }

    public void showFormatSetting(View view) {
        Intent intent = new Intent(this, FormatSetting.class);
        startActivity(intent);
    }


    public void showFormatSelection(View view) {
        Intent intent = new Intent(this, FormatSelection.class);
        startActivity(intent);
    }


    public void sendTest(View view) {
        byte[] temp = {0x42, 0x43, 0x44, 0x45};
        BluetoothChatService.getInstance().write(temp);

    }



}
