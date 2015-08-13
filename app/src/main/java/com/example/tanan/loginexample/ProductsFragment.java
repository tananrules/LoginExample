package com.example.tanan.loginexample;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.tanan.loginexample.session.SessionManager;

/**
 * Created by tanan on 3/8/15.
 */
public class ProductsFragment extends Fragment{
    public static final String PREF_NAME = "MyPrefsFile";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        Boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", Boolean.parseBoolean(null));

        if(isLoggedIn == true)
            Toast.makeText(getActivity(), "User Already Logged in", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(getActivity(), "Please Login", Toast.LENGTH_LONG).show();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.products_fragment, container, false);
        return view;
    }
}
