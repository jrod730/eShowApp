package com.example.jrod730.eshowapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jrod730 on 1/14/18.
 */

public class DatabaseHelper extends SQLiteOpenHelper{

    // Logcat
    private static final String LOG = "DatabaseHelper";

    // Database Version
    public static final int DATABASE_VERSION = 5;

    // Database Name
    private static final String DATABASE_NAME = "eShowApp";

    // Table Names
    private static final String TABLE_CHEF = "chef";
    private static final String TABLE_RESTAURANT = "restaurant";
    private static final String TABLE_WORK = "restaurant_chef";

    // Common column names
    private static final String KEY_ID = "id";

    // CHEF Table - column nmaes
    private static final String KEY_FNAME = "first_name";
    private static final String KEY_LNAME = "last_name";
    private static final String KEY_CHEF_ADDRESS1 = "address1";
    private static final String KEY_CHEF_ADDRESS2 = "address2";
    private static final String KEY_CHEF_CITY = "city";
    private static final String KEY_CHEF_STATE = "state";
    private static final String KEY_CHEF_COUNTRY = "country";
    private static final String KEY_CHEF_EMAIL = "email";

    // RESTAURANT Table - column names
    private static final String KEY_REST_NAME = "name";
    private static final String KEY_REST_ADDRESS1 = "address1";
    private static final String KEY_REST_ADDRESS2 = "address2";
    private static final String KEY_REST_CITY = "city";
    private static final String KEY_REST_STATE = "state";
    private static final String KEY_REST_COUNTRY = "country";
    private static final String KEY_REST_PHONE = "phone";

    // RESTAURANT_CHEF Table - column names
    private static final String KEY_CHEF_ID = "chef_id";
    private static final String KEY_REST_ID = "restaurant_id";

    // CHEF Table Create String
    private static final String CREATE_TABLE_CHEF = "CREATE TABLE "
            + TABLE_CHEF + "(" + KEY_ID + " INTEGER PRIMARY KEY," +
            KEY_FNAME + " TEXT," + KEY_LNAME + " TEXT," + KEY_CHEF_ADDRESS1 + " TEXT,"
            + KEY_CHEF_ADDRESS2 + " TEXT," + KEY_CHEF_CITY + " TEXT," + KEY_CHEF_STATE +
            " TEXT," + KEY_CHEF_COUNTRY + " TEXT," + KEY_CHEF_EMAIL + " TEXT " + ")";

    // REST table create string
    private static final String CREATE_TABLE_RESTAURANT = "CREATE TABLE " + TABLE_RESTAURANT
            + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_REST_NAME + " TEXT," + KEY_REST_ADDRESS1 + " TEXT,"
            + KEY_REST_ADDRESS2 + " TEXT," + KEY_REST_CITY + " TEXT," + KEY_REST_STATE +
            " TEXT," + KEY_REST_COUNTRY + " TEXT,"  + KEY_REST_PHONE + " TEXT" + ")";

    // Restaurant_Chef table create string
    private static final String CREATE_TABLE_WORK = "CREATE TABLE "
            + TABLE_WORK + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_REST_ID + " INTEGER," + KEY_CHEF_ID + " INTEGER" + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION );

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.v("Database", "In on create");
        // creating required tables
        db.execSQL(CREATE_TABLE_CHEF);
        db.execSQL(CREATE_TABLE_RESTAURANT);
        db.execSQL(CREATE_TABLE_WORK);

        seedDatabase(db);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHEF);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESTAURANT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WORK);

        // create new tables
        onCreate(db);
    }

    public void seedDatabase(SQLiteDatabase db){

        //This method will seed database upon create.

        Restaurant rest1 = new Restaurant("Jaybird Steakhouse", "1234 W. Anderson ave.", "", "Chicago", "IL", "US", "312-555-5555");
        Restaurant rest2 = new Restaurant("Joes Nashville Hot", "1735 N. Quadra St.", "Suite# 3", "Salt Lake City", "UT", "US", "982-985-2576");
        Restaurant rest3 = new Restaurant("Craft Beer House", "4687 S. Hops Blvd.", "", "Chicago", "IL", "US", "773-837-8374");
        Restaurant rest4 = new Restaurant("Kirkland Chicken N Waffles", "9734 E. 19th St.", "", "Chicago", "IL", "US", "312-364-5555");
        Restaurant rest5 = new Restaurant("Huskys BBQ", "62543 E. Northwest Dr..", "", "Chicago", "IL", "US", "312-763-5555");

        // Inserting restaruants in db
        long rest1_id = this.createRestaurant(db, rest1);
        long rest2_id = this.createRestaurant(db, rest2);
        long rest3_id = this.createRestaurant(db, rest3);
        long rest4_id = this.createRestaurant(db, rest4);
        long rest5_id = this.createRestaurant(db, rest5);



        // Chef seeds
        Chef chef1 = new Chef("James", "White", "555 N. Main St.", "Suite# 123", "Chicago", "IL", "US","sweetfeet@gopats.com");
        Chef chef2 = new Chef("Jimmy", "Franks", "123 S. West Ave.", "", "Long Beach", "CA", "US", "fishguy@jellys.com");
        Chef chef3 = new Chef("Grant", "Antler", "90210 E. Bay St.", "Apt# 33", "St Loius", "MO", "US", "gantler@hotmail.com");
        Chef chef4 = new Chef("Tim", "Froglegs", "5995 W. Kirkland Blvd.", "", "San Fransico", "CA", "US", "tfroglegs@butter.com");
        Chef chef5 = new Chef("Amy", "Winery", "8235 E. 23rd Ave.", "", "Winchester", "VA", "US", "awinery@gmail.com");


        // Inserting chef in db
        // Inserting  under chefs with restaurants
        long chef1_id = this.createChef(db, chef1, new long[] { rest1_id, rest2_id, rest3_id, rest4_id });
        long chef2_id = this.createChef(db, chef2, new long[] { rest3_id, rest4_id });
        long chef3_id = this.createChef(db, chef3, new long[] { rest2_id, rest4_id });
        long chef4_id = this.createChef(db, chef4, new long[] { rest1_id, rest5_id, rest3_id });
        long chef5_id = this.createChef(db, chef5, new long[] { rest1_id, rest5_id });
    }


    //create chef rows in db
    public long createChef(SQLiteDatabase db, Chef chef, long[] rest_ids) {


        ContentValues values = new ContentValues();
        values.put(KEY_FNAME, chef.getmFirstName());
        values.put(KEY_LNAME, chef.getmLastName());
        values.put(KEY_CHEF_ADDRESS1, chef.getmAddress1());
        values.put(KEY_CHEF_ADDRESS2, chef.getmAddress2());
        values.put(KEY_CHEF_CITY, chef.getmCity());
        values.put(KEY_CHEF_STATE, chef.getmState());
        values.put(KEY_CHEF_COUNTRY, chef.getmCountry());
        values.put(KEY_CHEF_EMAIL, chef.getmEmail());



        // insert row
        long chef_id = db.insert(TABLE_CHEF, null, values);

        // assigning restsaurant to chef
        for (long rest_id : rest_ids) {
            this.createWork(db, chef_id, rest_id);
        }

        return chef_id;
    }

    //create and add restaurants to db
    public long createRestaurant(SQLiteDatabase db, Restaurant rest) {

        ContentValues values = new ContentValues();
        values.put(KEY_REST_NAME, rest.getmName());
        values.put(KEY_REST_ADDRESS1, rest.getmAddress1());
        values.put(KEY_REST_ADDRESS2, rest.getmAddress2());
        values.put(KEY_REST_CITY, rest.getmCity());
        values.put(KEY_REST_STATE, rest.getmState());
        values.put(KEY_REST_COUNTRY, rest.getmCountry());
        values.put(KEY_REST_PHONE, rest.getmPhone());


        // insert row
        long rest_id = db.insert(TABLE_RESTAURANT, null, values);

        return rest_id;
    }

    //create db with restaurant and chef relationships
    public long createWork(SQLiteDatabase db, long chef_id, long rest_id) {


        ContentValues values = new ContentValues();
        values.put(KEY_CHEF_ID, chef_id);
        values.put(KEY_REST_ID, rest_id);

        long id = db.insert(TABLE_WORK, null, values);

        return id;
    }



//get a single chef
    public Chef getChef(long chef_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_CHEF + " WHERE "
                + KEY_ID + " = " + chef_id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        //create chef object from database
        Chef chef = new Chef();
        chef.setId(c.getInt((c.getColumnIndex(KEY_ID))));
        chef.setmFirstName((c.getString(c.getColumnIndex(KEY_FNAME))));
        chef.setmLastName((c.getString(c.getColumnIndex(KEY_LNAME))));
        chef.setmAddress1((c.getString(c.getColumnIndex(KEY_CHEF_ADDRESS1))));
        chef.setmAddress2((c.getString(c.getColumnIndex(KEY_CHEF_ADDRESS2))));
        chef.setmCity((c.getString(c.getColumnIndex(KEY_CHEF_CITY))));
        chef.setmState((c.getString(c.getColumnIndex(KEY_CHEF_STATE))));
        chef.setmCountry((c.getString(c.getColumnIndex(KEY_CHEF_COUNTRY))));
        chef.setmEmail((c.getString(c.getColumnIndex(KEY_CHEF_EMAIL))));

        return chef;
    }

//get all chefs
    public List<Chef> getAllChefs() {
        List<Chef> chefs = new ArrayList<Chef>();
        String selectQuery = "SELECT  * FROM " + TABLE_CHEF;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to chef list
        if (c.moveToFirst()) {
            do {
                Chef chef = new Chef();
                chef.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                chef.setmFirstName((c.getString(c.getColumnIndex(KEY_FNAME))));
                chef.setmLastName((c.getString(c.getColumnIndex(KEY_LNAME))));
                chef.setmAddress1((c.getString(c.getColumnIndex(KEY_CHEF_ADDRESS1))));
                chef.setmAddress2((c.getString(c.getColumnIndex(KEY_CHEF_ADDRESS2))));
                chef.setmCity((c.getString(c.getColumnIndex(KEY_CHEF_CITY))));
                chef.setmState((c.getString(c.getColumnIndex(KEY_CHEF_STATE))));
                chef.setmCountry((c.getString(c.getColumnIndex(KEY_CHEF_COUNTRY))));
                chef.setmEmail((c.getString(c.getColumnIndex(KEY_CHEF_EMAIL))));


                // add chef objects to list
                chefs.add(chef);
            } while (c.moveToNext());
        }

        return chefs;
    }

//get all chefs by restaurant utilizing restaurant name
    public List<Chef> getAllChefsByRestaurant(String rest_name) {
        List<Chef> chefs = new ArrayList<Chef>();

        String selectQuery = "SELECT  * FROM " + TABLE_CHEF + " tc, "
                + TABLE_RESTAURANT + " tr, " + TABLE_WORK + " tw WHERE tr."
                + KEY_REST_NAME + " = '" + rest_name + "'" + " AND tr." + KEY_ID
                + " = " + "tw." + KEY_REST_ID + " AND tc." + KEY_ID + " = "
                + "tw." + KEY_CHEF_ID;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding chefs to list
        if (c.moveToFirst()) {
            do {
                Chef chef = new Chef();
                chef.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                chef.setmFirstName((c.getString(c.getColumnIndex(KEY_FNAME))));
                chef.setmLastName((c.getString(c.getColumnIndex(KEY_LNAME))));
                chef.setmAddress1((c.getString(c.getColumnIndex(KEY_CHEF_ADDRESS1))));
                chef.setmAddress2((c.getString(c.getColumnIndex(KEY_CHEF_ADDRESS2))));
                chef.setmCity((c.getString(c.getColumnIndex(KEY_CHEF_CITY))));
                chef.setmState((c.getString(c.getColumnIndex(KEY_CHEF_STATE))));
                chef.setmCountry((c.getString(c.getColumnIndex(KEY_CHEF_COUNTRY))));
                chef.setmEmail((c.getString(c.getColumnIndex(KEY_CHEF_EMAIL))));

                // adding chefs to list
                chefs.add(chef);
            } while (c.moveToNext());
        }

        return chefs;
    }

    //get a single restaurant
    public Restaurant getRestaurant(long restaurant_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_RESTAURANT + " WHERE "
                + KEY_ID + " = " + restaurant_id;

        Log.e(LOG, selectQuery);

        Restaurant rest = new Restaurant();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        rest.setId(c.getInt((c.getColumnIndex(KEY_ID))));
        rest.setmName((c.getString(c.getColumnIndex(KEY_REST_NAME))));
        rest.setmAddress1((c.getString(c.getColumnIndex(KEY_CHEF_ADDRESS1))));
        rest.setmAddress2((c.getString(c.getColumnIndex(KEY_CHEF_ADDRESS2))));
        rest.setmCity((c.getString(c.getColumnIndex(KEY_REST_CITY))));
        rest.setmState((c.getString(c.getColumnIndex(KEY_REST_STATE))));
        rest.setmCountry((c.getString(c.getColumnIndex(KEY_REST_COUNTRY))));
        rest.setmPhone((c.getString(c.getColumnIndex(KEY_REST_PHONE))));

        return rest;
    }

    //get a list of restaurant
    public List<Restaurant> getAllRestaurants() {

        List<Restaurant> rests = new ArrayList<Restaurant>();
        String selectQuery = "SELECT  * FROM " + TABLE_RESTAURANT;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding restaurants to list
        if (c.moveToFirst()) {
            do {
                Restaurant rest = new Restaurant();
                rest.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                rest.setmName((c.getString(c.getColumnIndex(KEY_REST_NAME))));
                rest.setmAddress1((c.getString(c.getColumnIndex(KEY_CHEF_ADDRESS1))));
                rest.setmAddress2((c.getString(c.getColumnIndex(KEY_CHEF_ADDRESS2))));
                rest.setmCity((c.getString(c.getColumnIndex(KEY_REST_CITY))));
                rest.setmState((c.getString(c.getColumnIndex(KEY_REST_STATE))));
                rest.setmCountry((c.getString(c.getColumnIndex(KEY_REST_COUNTRY))));
                rest.setmPhone((c.getString(c.getColumnIndex(KEY_REST_PHONE))));

                // adding to restaruants to  list
                rests.add(rest);
            } while (c.moveToNext());
        }
        return rests;
    }

    // closing database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }


}
