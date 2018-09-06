package com.example.marco.myfeeder.format_edit;

import android.support.v7.widget.RecyclerView;
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

each item_schedule_element View host a remove button, linked to a RemoveListener
on onBindViewHolder each remove button is linked to 

 */




public class FormatAdapter extends RecyclerView.Adapter<FormatAdapter.ViewHolder> {

    private class SpaceTime {
        String space;
        String time;
        SpaceTime(String mspace, String mtime){
            space =mspace;
            time =mtime;
        }
    }

    private ArrayList<SpaceTime> mItems;

    class RemoveListener implements View.OnClickListener {

        private ArrayList<SpaceTime> mItems;
        private int mIndex;
        private FormatAdapter mAdapter;

        private RemoveListener(FormatAdapter adapter, ArrayList<SpaceTime> items, int index) {
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
        viewHolder.getButton().setOnClickListener(new RemoveListener(this, mItems, position));
        viewHolder.setSpaceText(mItems.get(position).space);
        viewHolder.setLineText(mItems.get(position).time);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void add(int a,int b){
        mItems.add(new SpaceTime(a+"",b+""));
    }

    public int numElem(){
        return mItems.size();
    }

}
