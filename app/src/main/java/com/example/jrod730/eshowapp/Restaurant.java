package com.example.jrod730.eshowapp;

/**
 * Created by jrod730 on 1/14/18.
 */

public class Restaurant {

    private int id;
    private String mName;
    private String mAddress1;
    private String mAddress2;
    private String mCity;
    private String mState;
    private String mCountry;
    private String mPhone;

    public Restaurant() {
    }

    public Restaurant(String mName, String mAddress1, String mAddress2, String mCity, String mState, String mCountry, String mPhone) {

        this.mName = mName;
        this.mAddress1 = mAddress1;
        this.mAddress2 = mAddress2;
        this.mCity = mCity;
        this.mState = mState;
        this.mCountry = mCountry;
        this.mPhone = mPhone;
    }

    //setters

    public void setId(int id) {
        this.id = id;
    }

    public void setmName(String mName) {
        this.mName = mName;
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

    public void setmPhone(String mPhone) {
        this.mPhone = mPhone;
    }

    //getters

    public int getId() {
        return id;
    }

    public String getmName() {
        return mName;
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

    public String getmPhone() {
        return mPhone;
    }
}
