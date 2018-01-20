package com.example.jrod730.eshowapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MainMenuFragment extends Fragment {

    private MainMenuFragmentInterface mainMenuFragmentInterfaceRef;

    public MainMenuFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //inflate view with listview adapter and onClickListener
        View v = inflater.inflate(R.layout.fragment_main_menu, container, false);


        String[] btns = { "Resaurant", "Chefs"};

        //attach string to paart of listview
        final ListView listView = (ListView) v.findViewById(R.id.mainmenu);

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                btns

        );

        listView.setAdapter(listViewAdapter);

    //listen for user click and send click position back to activity to prompt next fragment
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long id)
            {

                if(position == 0)
                {

                    if (mainMenuFragmentInterfaceRef != null)
                        mainMenuFragmentInterfaceRef.callList(position);
                }
                if(position == 1)
                {
                    if (mainMenuFragmentInterfaceRef != null)
                        mainMenuFragmentInterfaceRef.callList(position);
                }
            }
        });


        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainMenuFragmentInterface)
            mainMenuFragmentInterfaceRef = (MainMenuFragmentInterface) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mainMenuFragmentInterfaceRef = null;
    }



    public interface MainMenuFragmentInterface {

        void callList(int buttonIndex);

    }

}
