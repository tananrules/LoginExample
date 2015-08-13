package com.example.tanan.loginexample;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tanan.loginexample.session.SessionManager;
import com.example.tanan.loginexample.session.SignInResponse;
import com.google.gson.Gson;
import com.iangclifton.android.floatlabel.FloatLabel;


import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by tanan on 30/7/15.
 */
public class AdminLoginFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.log_in_fragment, container, false);

        final EditText flTenantEmail = ((FloatLabel) view.findViewById(R.id.flUserEmail)).getEditText();
        final EditText flTenantPassword = ((FloatLabel) view.findViewById(R.id.flUserPassword)).getEditText();
        Button tenantSignIn = (Button) view.findViewById(R.id.userSignIn);

        tenantSignIn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String tenantEmail =  flTenantEmail.getText().toString();
                        final String tenantPassword = flTenantPassword.getText().toString();

                        new AdminLogin().execute(tenantEmail, tenantPassword);
                    }
                }
        );

        return view;
    }

    public class AdminLogin extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... params) {
            String userEmail = params[0];
            String userPassword = params[1];

            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint("http://demo.baniyafy.in")
                    .build();

            AdminLoginService adminLoginService = restAdapter.create(AdminLoginService.class);

            Callback<Object> cb = new Callback<Object>() {
                @Override
                public void success(Object object, Response response) {
                    Toast.makeText(getActivity(), "Signed In Successfully", Toast.LENGTH_LONG).show();

                    Gson gson = new Gson();
                    final SignInResponse signInResponse = gson.fromJson(object.toString(), SignInResponse.class);

                    SessionManager sessionManager = new SessionManager(getActivity());
                    sessionManager.createLoginSession(signInResponse);

                    Fragment fragment = new ProductsFragment();

                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                    fragmentTransaction.replace(R.id.flLoginFragment, fragment);
                    fragmentTransaction.commit();
                }

                @Override
                public void failure(RetrofitError error) {
                    Toast.makeText(getActivity(), "Invalid Email or Password", Toast.LENGTH_LONG).show();
                }

            };


            UserObject userObject = new UserObject();
            User user = new User();
            user.email = userEmail;
            user.password = userPassword;
            user.login_for = "admin";
            userObject.user = user;

            adminLoginService.signInAdmin(userObject, cb);

            return null;
        }
    }

}


