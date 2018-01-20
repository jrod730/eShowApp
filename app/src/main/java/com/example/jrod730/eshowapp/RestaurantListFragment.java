package com.example.jrod730.eshowapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;


public class RestaurantListFragment extends Fragment {

    DatabaseHelper db;
    private RestaurantListFragmentInterface restaurantListFragmentInterfaceRef;

    public RestaurantListFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_restaurant_list, container, false);
        final ListView listView = (ListView) v.findViewById(R.id.restlist);

        //retrieve all restnames in db and add to listview
        String[] restNames = getStringForAllRestaurantNames();

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                restNames


        );

        listView.setAdapter(listViewAdapter);

        //listen for user click and send respective position back to activity.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long id)
            {

                //in this case position starts at 0 but to access from database id begins at 1
                // add 1 and cast to long
                long databaseId = position + 1;

                if(position == 0)
                {

                    if (restaurantListFragmentInterfaceRef != null)
                        restaurantListFragmentInterfaceRef.callRestaurantDetails(databaseId);
                }
                if(position == 1)
                {
                    if (restaurantListFragmentInterfaceRef != null)
                        restaurantListFragmentInterfaceRef.callRestaurantDetails(databaseId);
                }
                if(position == 2)
                {

                    if (restaurantListFragmentInterfaceRef != null)
                        restaurantListFragmentInterfaceRef.callRestaurantDetails(databaseId);
                }
                if(position == 3)
                {
                    if (restaurantListFragmentInterfaceRef != null)
                        restaurantListFragmentInterfaceRef.callRestaurantDetails(databaseId);
                }
                if(position == 4)
                {

                    if (restaurantListFragmentInterfaceRef != null)
                        restaurantListFragmentInterfaceRef.callRestaurantDetails(databaseId);
                }

            }
        });


        return v;
    }

    public String[] getStringForAllRestaurantNames() {

//        retrieve all restnames in db and add to array

        db = new DatabaseHelper(getActivity());

        List<Restaurant> rests = db.getAllRestaurants();

        String[] restNames = new String[rests.size()];

        for(int i = 0; i < rests.size(); i++)
        {
            restNames[i] =rests.get(i).getmName();
        }
        db.closeDB();

        return restNames;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof RestaurantListFragmentInterface)
            restaurantListFragmentInterfaceRef = (RestaurantListFragmentInterface) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        restaurantListFragmentInterfaceRef = null;
    }

    public interface RestaurantListFragmentInterface {

        void callRestaurantDetails(long listIndex);

    }
}
