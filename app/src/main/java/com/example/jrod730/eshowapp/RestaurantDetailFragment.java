package com.example.jrod730.eshowapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;


public class RestaurantDetailFragment extends Fragment {

    private long mRestId;
    private DatabaseHelper db;

    //Each instance should have its own Restaurant id #
    public RestaurantDetailFragment(long restId) {

        this.mRestId = restId;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //create view, open DB, find restuarant by id
        View v = inflater.inflate(R.layout.fragment_resaurant_detail, container, false);
        db = new DatabaseHelper(getActivity());
        Restaurant displayRestaurant = db.getRestaurant(this.mRestId);

        //assigning variables to textview for manipulation
        TextView restaurantInfo = (TextView) v.findViewById(R.id.restdetails);
        TextView chef1 = (TextView) v.findViewById(R.id.chefdetails1);
        TextView chef2 = (TextView) v.findViewById(R.id.chefdetails2);
        TextView chef3 = (TextView) v.findViewById(R.id.chefdetails3);


        //getting list of chefs by restaurant and then setting and append respectively
        List<Chef> chefsByRestaurants = db.getAllChefsByRestaurant(displayRestaurant.getmName());
        restaurantInfo.setText(displayRestaurant.getmName() + "\n");
        restaurantInfo.append(displayRestaurant.getmAddress1() + "\n");
        restaurantInfo.append(displayRestaurant.getmCity() + ", " + displayRestaurant.getmState()  + "\n");
        restaurantInfo.append(displayRestaurant.getmPhone());


        for(int i = 0; i < chefsByRestaurants.size(); i++)
        {
            if (i == 0) {
                chef1.setText(chefsByRestaurants.get(i).getmFirstName() + " " + chefsByRestaurants.get(i).getmLastName()+ "\n");
                chef1.append(chefsByRestaurants.get(i).getmAddress1() + "\n");
                chef1.append(chefsByRestaurants.get(i).getmCity() + ", " + chefsByRestaurants.get(i).getmState());
            }
            if (i == 1) {
                chef2.setText(chefsByRestaurants.get(i).getmFirstName() + " " + chefsByRestaurants.get(i).getmLastName() + "\n");
                chef2.append(chefsByRestaurants.get(i).getmAddress1() + "\n");
                chef2.append(chefsByRestaurants.get(i).getmCity() + ", " + chefsByRestaurants.get(i).getmState());
            }
            if (i == 2) {
                chef3.setText(chefsByRestaurants.get(i).getmFirstName() + " " + chefsByRestaurants.get(i).getmLastName()+ "\n");
                chef3.append(chefsByRestaurants.get(i).getmAddress1() + "\n");
                chef3.append(chefsByRestaurants.get(i).getmCity() + ", " + chefsByRestaurants.get(i).getmState());
            }
        }

        db.closeDB();

        return v;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
