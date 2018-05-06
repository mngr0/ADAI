/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.marco.myfeeder.format_edit;

import android.content.Context;
import android.net.wifi.p2p.WifiP2pManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.marco.myfeeder.Configuration;
import com.example.marco.myfeeder.R;
import com.example.marco.myfeeder.ble.BluetoothChatService;

import java.util.ArrayList;


public class FormatAdapter extends RecyclerView.Adapter<FormatAdapter.ViewHolder> {
    private static final String TAG = "FormatAdapter";

    private ArrayList<sd> mItems;
    private int mIndex;

    class RemoveListener implements View.OnClickListener {

        private ArrayList<sd> mItems;
        private int mIndex;
        private FormatAdapter mAdapter;

        public RemoveListener(FormatAdapter adapter, ArrayList<sd> items, int index) {
            mItems = items;
            mIndex = index;
            mAdapter = adapter;
        }

        @Override
        public void onClick(View v) {
            mItems.remove(mIndex);
            mAdapter.notifyDataSetChanged();

        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView textView;
        private final Button button;
        private final EditText spaceText;
        private final EditText lineText;

        public ViewHolder(View v) {
            super(v);
            final Context t = v.getContext();
            textView = v.findViewById(R.id.textView6);
            button = v.findViewById(R.id.button13);
            spaceText = v.findViewById(R.id.spaceText);
            lineText = v.findViewById(R.id.lineText);
        }

        public TextView getTextView() {
            return textView;
        }

        public Button getButton() {
            return button;
        }

        public void setSpaceText(String text){
            spaceText.setText(text);
        }

        public void setLineText(String text){
            lineText.setText(text);
        }
    }

    public FormatAdapter(ArrayList<sd> items,int index) {
        mItems = items;
        mIndex=index;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_schedule_element, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Log.d(TAG, "Element " + position + " set.");
        //viewHolder.getTextView().setText(mItems.get(position).a);
        viewHolder.getButton().setOnClickListener(new RemoveListener(this, mItems, position));
        viewHolder.setSpaceText(mItems.get(position).a);
        viewHolder.setLineText(mItems.get(position).b);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}
