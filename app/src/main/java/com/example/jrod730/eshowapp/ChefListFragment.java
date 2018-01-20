package com.example.jrod730.eshowapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;



public class ChefListFragment extends Fragment {

    DatabaseHelper db;


    public ChefListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //get all chef names and append to array apdater then inflate view

        List<Chef> chefs = getAllChefsQuery();
        View v = inflater.inflate(R.layout.fragment_chef_list, container, false);


        String[] chefNames = new String[chefs.size()];
        for(int i = 0; i < chefs.size(); i++)
        {
            chefNames[i] =chefs.get(i).getmFirstName() + " " + chefs.get(i).getmLastName();
        }


        final ListView listView = (ListView) v.findViewById(R.id.cheflist);

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                chefNames


        );


        listView.setAdapter(listViewAdapter);

        return v;
    }

    public List<Chef> getAllChefsQuery()
    {
        //query db for all chefs
        db = new DatabaseHelper(getActivity());

        List<Chef> chefList = db.getAllChefs();

        db.closeDB();

        return chefList;
    }



}
