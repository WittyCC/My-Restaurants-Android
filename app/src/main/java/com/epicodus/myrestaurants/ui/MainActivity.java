package com.epicodus.myrestaurants.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.graphics.Typeface;

import com.epicodus.myrestaurants.Constants;
import com.epicodus.myrestaurants.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

//    public static final String TAG = MainActivity.class.getSimpleName();
    @Bind(R.id.textView) TextView mAppNameTextView;
    @Bind(R.id.locationEditText) EditText mLocationEditText;
    @Bind(R.id.findRestaurantsButton) Button mFindRestaurantsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

//        mAppNameTextView = (TextView) findViewById(R.id.textView);

        Typeface champagneFont = Typeface.createFromAsset(getAssets(), "fonts/cac_champagne.ttf");
        mAppNameTextView.setTypeface(champagneFont);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

//        mLocationEditText = (EditText) findViewById(R.id.locationEditText);

        mFindRestaurantsButton.setOnClickListener(this);
    }

//        mFindRestaurantsButton = (Button) findViewById(R.id.findRestaurantsButton);

    @Override
    public void onClick(View v) {
        if (v == mFindRestaurantsButton) {

//        mFindRestaurantsButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
            String location = mLocationEditText.getText().toString();
            if(!(location).equals("")) {
                addToSharedPreferences(location);
            }
            Intent intent = new Intent(MainActivity.this, RestaurantListActivity.class);
            intent.putExtra("location", location);
            startActivity(intent);
        }
    }

    private void addToSharedPreferences(String location) {
        mEditor.putString(Constants.PREFERENCES_LOCATION_KEY, location).apply();
    }
}
