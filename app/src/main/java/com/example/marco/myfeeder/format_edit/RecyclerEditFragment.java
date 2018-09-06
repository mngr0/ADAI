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

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.marco.myfeeder.Configuration;
import com.example.marco.myfeeder.R;

import java.util.ArrayList;

/**
 * Demonstrates the use of {@link RecyclerView} with space {@link LinearLayoutManager} and space
 * {@link GridLayoutManager}.
 */
public class RecyclerEditFragment extends Fragment {

    private static final String TAG = "RecyclerViewFragment";

    protected RecyclerView mRecyclerView;
    protected FormatAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    private int mIndex;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recycler_edit, container, false);
        rootView.setTag(TAG);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView3);
        mLayoutManager = new LinearLayoutManager(getActivity());
        setRecyclerViewLayoutManager();
        mAdapter = new FormatAdapter(mIndex);
        mRecyclerView.setAdapter(mAdapter);
        return rootView;
    }

    public void setRecyclerViewLayoutManager() {
        int scrollPosition = 0;

        // If space layout manager has already been set, get current scroll position.
        if (mRecyclerView.getLayoutManager() != null) {
            scrollPosition = ((LinearLayoutManager) mRecyclerView.getLayoutManager())
                    .findFirstCompletelyVisibleItemPosition();
        }

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.scrollToPosition(scrollPosition);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save currently selected layout manager.
        super.onSaveInstanceState(savedInstanceState);
    }


    public int[] getTimes() {

        int[] val= new int[mLayoutManager.getItemCount()*2];
        for (int i=0;i<mLayoutManager.getItemCount();i++){
            val[i*2]=Integer.parseInt(((EditText)mLayoutManager.getChildAt(i).findViewById(R.id.spaceText)).getText().toString());
            val[i*2+1]=Integer.parseInt(((EditText)mLayoutManager.getChildAt(i).findViewById(R.id.lineText)).getText().toString());
        }
        return val;
    }

    public void addElementIn(){
        mAdapter.add(50,100);
        mAdapter.notifyItemInserted(mAdapter.numElem() - 1);
    }

    public void setIndex(int index) {
        mIndex=index;
    }
}
