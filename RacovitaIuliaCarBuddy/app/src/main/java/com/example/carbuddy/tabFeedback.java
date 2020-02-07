package com.example.carbuddy;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Rating;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.carbuddy.database.DatabaseManager;
import com.example.carbuddy.database.dao.UserDao;
import com.example.carbuddy.database.model.User;
import com.example.carbuddy.util.SharedPreferencess;


/**
 * A simple {@link Fragment} subclass.
 */
public class tabFeedback extends Fragment {
    RatingBar ratingBar;
    Button buttonFeedback;
    Button deleteAcc;
    private final static String PREF_SHARED_NAME = "PrefSharedName";
    private final static String RATING_BAR_SHARED_PREF = "ratingBarSharedPref";
    SharedPreferences sharedPreferences;
    SharedPreferencess sh;
    UserDao userDao;
    DatabaseManager db;


    public tabFeedback() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tab_feedback, container, false);
        ratingBar = view.findViewById(R.id.ratingBar);
        buttonFeedback = view.findViewById(R.id.buttonFeedback);
        deleteAcc = view.findViewById(R.id.deleteAccount);
        sh = new SharedPreferencess(getActivity());


        db = Room.databaseBuilder(getActivity(), DatabaseManager.class, "iua_db")
                .allowMainThreadQueries()
                .build();


        userDao = db.getUserDao();

        deleteAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        User user = userDao.getUserById(sh.getUser());
                        userDao.delete(user);
                        Intent intent = new Intent(getActivity(), RegisterActivity.class);
                        getActivity().startActivity(intent);
                    }
                }, 1000);

            }
    });

        if (getActivity() != null) {
            //initializare shared preferences
            sharedPreferences = getActivity().getSharedPreferences(PREF_SHARED_NAME,
                    Context.MODE_PRIVATE);
                    //citire din fisier
            float value = sharedPreferences.getFloat(RATING_BAR_SHARED_PREF, -1);
            if (value != -1) {
                ratingBar.setRating(value);
            }
            ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar,
                                            float rating,
                                            boolean fromUser) {
                    //salvare in fisier
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putFloat(RATING_BAR_SHARED_PREF, rating);
                    editor.apply();
                }


            });
        }
        return view;
    }
}
