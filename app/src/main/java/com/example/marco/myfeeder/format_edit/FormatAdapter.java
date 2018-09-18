package com.example.marco.myfeeder.format_edit;

import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.marco.myfeeder.Configuration;
import com.example.marco.myfeeder.R;

import java.util.ArrayList;

/*
Format Adapter

each item_schedule_element View hosts a remove button, linked to a RemoveListener
on onBindViewHolder each remove button is linked to a RemoveListener that removes the host View

The Format Adapter manage adding and removing elements

 */


public class FormatAdapter extends RecyclerView.Adapter<FormatAdapter.ViewHolder> {

    private class SpaceLine {
        String space;
        String line;
        SpaceLine(String mspace, String mline){
            space =mspace;
            line =mline;
        }
    }

    private ArrayList<SpaceLine> mItems;

    class RemoveListener implements View.OnClickListener {

        //private ArrayList<SpaceLine> mItems;
        private SpaceLine mItem;
        private FormatAdapter mAdapter;

        private RemoveListener(FormatAdapter adapter, SpaceLine item) {
            //mItems = items;
            mItem = item;
            mAdapter = adapter;
        }

        @Override
        public void onClick(View v) {
            mItems.remove(mItem);

            mAdapter.notifyDataSetChanged();
        }
    }


    private class EditListener implements View.OnFocusChangeListener {
        private SpaceLine mSLItem;
        private String mKind;
        private EditListener(SpaceLine item,String kind) {
            mSLItem = item;
            mKind=kind;
        }


        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if(mItems.contains(mSLItem)){
                if(mKind.equals("line")){
                    mSLItem.line=((EditText) v).getText().toString();
                }else{
                    mSLItem.space=((EditText) v).getText().toString();
                }
            }
            Log.d("textwatcher "+mItems.indexOf(mSLItem),"edited" + ((EditText) v).getText().toString());
            for (int i=0;i<mItems.size();i++) {
                Log.d("STATE", i+" -- "+mItems.get(i).space);
                Log.d("STATE", i+" -- "+mItems.get(i).line);
            }

        }
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        private final Button button;
        private final EditText spaceText;
        private final EditText lineText;

        private ViewHolder(View v) {
            super(v);
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
        mItems=new ArrayList<>();
        int[] u= Configuration.getTimes(index);
        int len= u.length/2;
        for (int i=0; i<len;i++){
            addItem(u[i*2],u[i*2+1]);
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
        viewHolder.getButton().setOnClickListener(new RemoveListener(this, mItems.get(position)));
        viewHolder.getLineText().setOnFocusChangeListener(new EditListener(mItems.get(position),"line"));
        viewHolder.getSpaceText().setOnFocusChangeListener(new EditListener(mItems.get(position),"space"));

        viewHolder.setSpaceText(mItems.get(position).space);
        viewHolder.setLineText(mItems.get(position).line);
    }

    @Override
    public int getItemCount() {
        if(mItems==null){
            return 0;
        }
        else {
            return mItems.size();
        }
    }

    public void addItem(int a, int b){
        mItems.add(new SpaceLine(a+"",b+""));
    }

    public int[] getTimes() {
        int[] val= new int[mItems.size()*2];
        for (int i=0;i<mItems.size();i++){
            Log.d("getTimes",""+i);
            val[i*2]=Integer.parseInt(mItems.get(i).space);
            val[i*2+1]=Integer.parseInt(mItems.get(i).line);
        }
        return val;
    }


}
