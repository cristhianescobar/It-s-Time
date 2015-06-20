package com.cristhianescobar.itstime.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cristhianescobar.itstime.data.DataUnit;
import com.cristhianescobar.itstime.adapters.DataUnitAdapter;
import com.cristhianescobar.itstime.R;

import java.util.ArrayList;
import java.util.List;


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

        adapter = new DataUnitAdapter(getActivity(), getDataToDisplay());
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return layout;
    }


    public static List<DataUnit> getDataToDisplay(){
        List<DataUnit> list = new ArrayList<>();
        int [] icons = {R.mipmap.ic_launcher, R.mipmap.ic_launcher};
        String [] names = {"One", "Two", "Three", "Four"};

        for(int i = 0; i < 1; i++){
            DataUnit d = new DataUnit();
            d.iconId = icons[i%icons.length];
            d.title = names[i%names.length];
            list.add(d);
        }

        return list;
    }

    public void addReminder() {
        adapter.addItem();
    }
}
