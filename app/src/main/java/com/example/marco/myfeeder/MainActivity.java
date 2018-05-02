package com.example.marco.myfeeder;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.marco.myfeeder.ble.BluetoothChatService;
import com.example.marco.myfeeder.bluetooth_ui.BluetoothConnect;
import com.example.marco.myfeeder.settings.FormatSetting;


public class MainActivity extends AppCompatActivity {

    public MainActivity() {
        BluetoothChatService.createInstance(mHandler);
    }


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


    public void sendTest(View view) {
        byte[] temp = {0x42, 0x43, 0x44, 0x45};
        BluetoothChatService.getInstance().write(temp);

    }


    @SuppressLint("HandlerLeak")
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Log.d("handler", msg.toString());
            byte[] readBuf = (byte[]) msg.obj;
            // construct a string from the valid bytes in the buffer
            String readMessage = "null";
            if((readBuf!= null)&&(msg.arg1>0))
                readMessage = new String(readBuf, 0, msg.arg1);
            Log.d("handler", readMessage);
        }
    };
}
