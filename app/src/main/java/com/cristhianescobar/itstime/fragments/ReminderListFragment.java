package com.cristhianescobar.itstime.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cristhianescobar.itstime.R;
import com.cristhianescobar.itstime.adapters.DataUnitAdapter;
import com.cristhianescobar.itstime.data.DataUnit;
import com.cristhianescobar.itstime.data.Reminder;

import java.util.ArrayList;


public class ReminderListFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private DataUnitAdapter adapter;

    public ReminderListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout =  inflater.inflate(R.layout.fragment_reminder_list, container, false);
        mRecyclerView = (RecyclerView) layout.findViewById(R.id.recyclerview_list);

        adapter = new DataUnitAdapter(getActivity(), new ArrayList<DataUnit>());
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return layout;
    }

    public void addReminder(Reminder reminder) {
        if(adapter != null){
            adapter.addItem(reminder);
        }
    }
}
