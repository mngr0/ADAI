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

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.marco.myfeeder.Configuration;
import com.example.marco.myfeeder.R;

import java.util.ArrayList;


public class FormatAdapter extends RecyclerView.Adapter<FormatAdapter.ViewHolder> {

    private class space_time{
        String space;
        String time;
        space_time(String mspace, String mtime){
            space =mspace;
            time =mtime;
        }
    }

    private ArrayList<space_time> mItems;
    private ArrayList<EditText> mEditTexts;
    //private int mIndex;

    class RemoveListener implements View.OnClickListener {

        private ArrayList<space_time> mItems;
        private int mIndex;
        private FormatAdapter mAdapter;

        private RemoveListener(FormatAdapter adapter, ArrayList<space_time> items, int index) {
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

        private ViewHolder(View v) {
            super(v);
            textView = v.findViewById(R.id.textView6);
            button = v.findViewById(R.id.button13);
            spaceText = v.findViewById(R.id.spaceText);
            lineText = v.findViewById(R.id.lineText);
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

        public EditText getLineText() {
            return lineText;
        }

        public EditText getSpaceText() {
            return spaceText;
        }
    }

    public FormatAdapter( int index) {
        //mIndex=index;
        mItems=new ArrayList<>();
        int[] u= Configuration.getTimes(index);
        int len= u.length/2;
        for (int i=0; i<len;i++){
            add(u[i*2],u[i*2+1]);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_schedule_element, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        //viewHolder.getTextView().setText(mItems.get(position).space);
        viewHolder.getButton().setOnClickListener(new RemoveListener(this, mItems, position));
        viewHolder.setSpaceText(mItems.get(position).space);
        viewHolder.setLineText(mItems.get(position).time);

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void add(int a,int b){
        mItems.add(new space_time(a+"",b+""));
    }

    public int numElem(){
        return mItems.size();
    }

}
