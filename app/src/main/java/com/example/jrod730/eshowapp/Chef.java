package com.example.jrod730.eshowapp;

import android.os.Parcelable;

/**
 * Created by jrod730 on 1/12/18.
 */

public class Chef {

    private int id;
    private String mFirstName;
    private String mLastName;
    private String mAddress1;
    private String mAddress2;
    private String mCity;
    private String mState;
    private String mCountry;
    private String mEmail;

    public Chef() {
    }

    public Chef(String firstName, String lastName, String address1, String address2, String city, String state, String country, String email) {

        this.mFirstName = firstName;
        this.mLastName = lastName;
        this.mAddress1 = address1;
        this.mAddress2 = address2;
        this.mCity = city;
        this.mState = state;
        this.mCountry = country;
        this.mEmail = email;
    }

    //Getters
    public int getId() {
        return id;
    }

    public String getmFirstName() {
        return mFirstName;
    }

    public String getmLastName() {
        return mLastName;
    }

    public String getmAddress1() {
        return mAddress1;
    }

    public String getmAddress2() {
        return mAddress2;
    }

    public String getmCity() {
        return mCity;
    }

    public String getmState() {
        return mState;
    }

    public String getmCountry() {
        return mCountry;
    }

    public String getmEmail() {
        return mEmail;
    }

    //Setters

    public void setId(int id) {
        this.id = id;
    }

    public void setmFirstName(String mFirstName) {
        this.mFirstName = mFirstName;
    }

    public void setmLastName(String mLastName) {
        this.mLastName = mLastName;
    }

    public void setmAddress1(String mAddress1) {
        this.mAddress1 = mAddress1;
    }

    public void setmAddress2(String mAddress2) {
        this.mAddress2 = mAddress2;
    }

    public void setmCity(String mCity) {
        this.mCity = mCity;
    }

    public void setmState(String mState) {
        this.mState = mState;
    }

    public void setmCountry(String mCountry) {
        this.mCountry = mCountry;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }
}
