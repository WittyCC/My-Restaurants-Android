package com.epicodus.myrestaurants.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.epicodus.myrestaurants.R;
import com.epicodus.myrestaurants.adapters.RestaurantListAdapter;
import com.epicodus.myrestaurants.models.Restaurant;
import com.epicodus.myrestaurants.services.YelpService;

import butterknife.Bind;
import butterknife.ButterKnife;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import java.io.IOException;
import java.util.ArrayList;

public class RestaurantListActivity extends AppCompatActivity {
    public static final String TAG = RestaurantListActivity.class.getSimpleName();

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    private RestaurantListAdapter mAdapter;

    public ArrayList<Restaurant> mRestaurants = new ArrayList<>();

//    private String[] restaurants = new String[] {"Sweet Hereafter", "Cricket", "Hawthorne Fish House", "Viking Soul Food", "Red Square", "Horse Brass", "Dick's Kitchen", "Taco Bell", "Me Kha Noodle Bar", "La Bonita Taqueria", "Smokehouse Tavern", "Pembiche", "Kay's Bar", "Gnarly Grey", "Slappy Cakes", "Mi Mero Mole"};

//    private String[] cuisines = new String[] {"Vegan Food", "Breakfast", "Fishs Dishs", "Scandinavian", "Coffee", "English Food", "Burgers", "Fast Food", "Noodle Soups", "Mexican", "BBQ", "Cuban", "Bar Food", "Sports Bar", "Breakfast", "Mexican" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");

//        mLocationTextView.setText("Here are all the restaurants near: " + location);

        getRestaurants(location);
        }

//        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, restaurants);
//        mListView.setAdapter(adapter);

//        mListView = (ListView) findViewById(R.id.listView);
//        mLocationTextView = (TextView) findViewById(R.id.locationTextView);
//
//        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

//        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                String restaurant = ((TextView)view).getText().toString();
//                Toast.makeText(RestaurantListActivity.this, restaurant, Toast.LENGTH_LONG).show();
////                Log.v(TAG, "In the onItemClickListener!");
//            }
//        });


//        Log.d(TAG, "In the onCreate method!");

//        MyRestaurantsArrayAdapter adapter = new MyRestaurantsArrayAdapter(this, android.R.layout.simple_list_item_1, restaurants, cuisines);
//        mListView.setAdapter(adapter);


    private void getRestaurants(String location) {
        final YelpService yelpService = new YelpService();
        yelpService.findRestaurants(location, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                mRestaurants = yelpService.processResults(response);

                RestaurantListActivity.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        mAdapter = new RestaurantListAdapter(getApplicationContext(), mRestaurants);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(RestaurantListActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);
//                        String[] restaurantNames = new String[mRestaurants.size()];
//                        for (int i = 0; i < restaurantNames.length; i++) {
//                            restaurantNames[i] = mRestaurants.get(i).getName();
//                        }
//
//                        ArrayAdapter adapter = new ArrayAdapter(RestaurantListActivity.this,
//                                android.R.layout.simple_list_item_1, restaurantNames);
//                        mListView.setAdapter(adapter);
//
//                        for (Restaurant restaurant : mRestaurants) {
//                            Log.d(TAG, "Name: " + restaurant.getName());
//                            Log.d(TAG, "Phone: " + restaurant.getPhone());
//                            Log.d(TAG, "Website: " + restaurant.getWebsite());
//                            Log.d(TAG, "Image url: " + restaurant.getImageUrl());
//                            Log.d(TAG, "Rating: " + Double.toString(restaurant.getRating()));
//                            Log.d(TAG, "Address: " + android.text.TextUtils.join(", ", restaurant.getAddress()));
//                            Log.d(TAG, "Categories: " + restaurant.getCategories().toString());
//                        }
                    }

                });

//                try {
//                    String jsonData = response.body().string();
//                    if (response.isSuccessful()) {
//                    Log.v(TAG, jsonData);
//                        mRestaurants = yelpService.processResults(response);
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
            }
        });
    }

}
