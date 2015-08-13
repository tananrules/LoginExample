package com.example.tanan.loginexample.session;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by tanan on 1/8/15.
 */
public class SessionManager {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor sharedPreferencesEditor;
    Context context;

    public static final String PREF_NAME = "MyPrefsFile";
    private static final String IS_LOGGED_IN = "isLoggedIn";
    private static final String IS_ADMIN = "isAdmin";
    public static final String KEY_PASSWORD = "user_password";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_AUTHENTICATION_TOKEN = "authenticationToken";

    public SessionManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        sharedPreferencesEditor = sharedPreferences.edit();
    }

    public void createLoginSession(SignInResponse signInResponse) {
        sharedPreferencesEditor.putBoolean(IS_LOGGED_IN, true);
        sharedPreferencesEditor.putString(KEY_EMAIL, signInResponse.email);
        sharedPreferencesEditor.putString(KEY_PASSWORD, signInResponse.password);
        sharedPreferencesEditor.putBoolean(IS_ADMIN, signInResponse.role != null && signInResponse.role.equals("admin"));
        sharedPreferencesEditor.putString(KEY_AUTHENTICATION_TOKEN, signInResponse.auth_token);
        sharedPreferencesEditor.commit();
    }
}
