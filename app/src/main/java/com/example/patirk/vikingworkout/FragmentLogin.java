package com.example.patirk.vikingworkout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.*;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;

/**
 * Created by Patirk on 03/04/2016.
 */
public class FragmentLogin extends android.support.v4.app.Fragment {
    public static FragmentLogin fragment;
    //Facebook login
    //CallbackManager callbackManager;

    public static FragmentLogin newInstance() {
        fragment = new FragmentLogin();
        Bundle args = new Bundle();
        //   args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);

        return fragment;
    }

    public FragmentLogin() {
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        final LoginButton loginButton = (LoginButton) view.findViewById(R.id.login_button);
        loginButton.setReadPermissions("public_profile, user_friends");
        // If using in a fragment
        loginButton.setFragment(this);
        // Other app specific specialization

        // Callback registration
        loginButton.registerCallback(MainActivity.callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                Toast.makeText(MainActivity.mainActivity, "logged in", Toast.LENGTH_SHORT).show();
                com.facebook.Profile.fetchProfileForCurrentAccessToken();
                String name = com.facebook.Profile.getCurrentProfile().getName();
                ;
                MainActivity.loadProfile(name, Profile.getCurrentProfile().getProfilePictureUri(500,500).toString());
               // LoginManager.getInstance().logInWithReadPermissions(MainActivity.mainActivity, Arrays.asList("public_profile", "user_friends"));
                MainActivity.removeFragment("login");
            }

            @Override
            public void onCancel() {
                // App code
                Toast.makeText(MainActivity.mainActivity, "FB: Cancel", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                Toast.makeText(MainActivity.mainActivity, "FB: Error", Toast.LENGTH_SHORT).show();
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logInWithReadPermissions(MainActivity.mainActivity, Arrays.asList("public_profile", "user_friends"));
            }
        });
        return view;
    }
}

