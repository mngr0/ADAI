package com.example.marco.myfeeder;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.marco.myfeeder.format_edit.FormatEdit;

public class FormatSelection extends AppCompatActivity {

    private static View[] viewArray = new View[Configuration.size];


    public static void setRadio(int index, boolean check) {
        if ((index == Configuration.getActive()) && (!check)) {
            return;
        }
        for (int i = 0; i < Configuration.size; i++) {
            if (i != index) {
                ((RadioButton) viewArray[i].findViewById(R.id.radioButton3)).setChecked(false);
            } else {
                ((RadioButton) viewArray[i].findViewById(R.id.radioButton3)).setChecked(true);
            }
        }
        Configuration.setActive(index);
    }

    public class ButtonListener implements View.OnClickListener {

        int mIndex;
        Context mContext;
        FormatSelection mParent;

        public ButtonListener(int index, Context context, FormatSelection parent) {
            mIndex = index;
            mContext = context;
            mParent = parent;
        }

        @Override
        public void onClick(View v) {
            Toast toast = Toast.makeText(mContext, "Editing " + Configuration.getName(mIndex), Toast.LENGTH_SHORT);
            toast.show();
            mParent.showFormatEdit(mIndex);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Bundle bundle = data.getExtras();
        int val= Integer.parseInt(bundle.get("index").toString());
        Toast toast = Toast.makeText(getApplicationContext(), "DONE!" + val, Toast.LENGTH_SHORT);
        toast.show();
        ((TextView)viewArray[val].findViewById(R.id.nameText)).setText(Configuration.getName(val));
    }

    public static class RadioListener implements View.OnClickListener {

        int mIndex;
        Context mContext;

        public RadioListener(int index, Context context) {
            mIndex = index;
            mContext = context;
        }

        @Override
        public void onClick(View v) {
            Toast toast = Toast.makeText(mContext, "Element " + mIndex + " clicked.", Toast.LENGTH_SHORT);
            toast.show();
            FormatSelection.setRadio(mIndex, false);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Context t = getApplicationContext();
        setContentView(R.layout.activity_format_selection);
        LinearLayout list = (LinearLayout) findViewById(R.id.layoutConfList);
        for (int i = 0; i < Configuration.size; i++) {
            viewArray[i] = getLayoutInflater().inflate(R.layout.item_selection, null, false);
            list.addView(viewArray[i]);
            viewArray[i].findViewById(R.id.button19).setOnClickListener(new ButtonListener(i, t, this));
            viewArray[i].findViewById(R.id.radioButton3).setOnClickListener(new RadioListener(i, t));
            ((TextView) viewArray[i].findViewById(R.id.nameText)).setText(Configuration.getName(i));
        }
        // BluetoothChatService mBTC = BluetoothChatService.getInstance();
        // mConfiguration = BluetoothChatService.getInstance().getmConfiguration();
        // if(!mBTC.isConnected()){
        //     //showtoast
        // }
        // else{
        //    setRadio(mConfiguration.active,true);
        // }
    }

    @Override
    protected void onStart() {
        super.onStart();
        setRadio(Configuration.getActive(), true);
    }

    public void showFormatEdit(int index) {

        Toast toast = Toast.makeText(getApplicationContext(), "go format_setting!", Toast.LENGTH_SHORT);
        toast.show();
        Intent intent = new Intent(this, FormatEdit.class);
        intent.setData(Uri.parse(index + ""));
        startActivityForResult(intent, 42);

    }
}
