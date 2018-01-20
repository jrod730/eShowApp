package com.example.jrod730.eshowapp;

import android.app.FragmentTransaction;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.List;

public class MainMenuActivity extends AppCompatActivity implements MainMenuFragment.MainMenuFragmentInterface, RestaurantListFragment.RestaurantListFragmentInterface {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        getSupportActionBar().setTitle("Main Menu");


        //display main menu fragment
        MainMenuFragment mainMenu = new MainMenuFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, mainMenu).commit();


    }


    @Override
    public void callList(int buttonIndex){


        //this method calls the respective fragment for either chefs or restaurants upon click
        if(buttonIndex == 0) {

            //set title for fragment switch
            getSupportActionBar().setTitle("Restaurant List");

            RestaurantListFragment rl = new RestaurantListFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, rl)
                    .addToBackStack(null).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
        }

        if(buttonIndex == 1) {
            //set title for fragment switch
            getSupportActionBar().setTitle("Chef List");
            ChefListFragment cl = new ChefListFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, cl)
                    .addToBackStack(null).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
        }

    }

    @Override
    public void callRestaurantDetails(long listIndex){
        //set title for fragment switch
        getSupportActionBar().setTitle("Restaurant Info");

        //This method calls the fragment  for restaurant details upon click
        RestaurantDetailFragment rd = new RestaurantDetailFragment(listIndex);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, rd)
                .addToBackStack(null).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
    }
}
