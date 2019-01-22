package com.memberapps2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

//import adapter.UserAdapter;

import connection.Endpoint;
//import connection.ServerConnection;
//import connection.Service;
import helper.RetroClient;
//import model.User;
//import model.UserList;
import model.UserList;
import model.UserLogin;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private Boolean status;
    private ProgressDialog pDialog;
    private RecyclerView recyclerView;
    //    private UserAdapter userAdapter;
    EditText txtusername, txtpassword;
    Endpoint api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        txtusername = (EditText) findViewById(R.id.txtusername);
        txtpassword = (EditText) findViewById(R.id.txtpassword);

        Button btnLogin = (Button) findViewById(R.id.btnlogin);
        btnLogin.setOnClickListener(this);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        System.exit(0);
        finish();
    }

    @Override
    public void onClick(View view) {
//        new Service().execute();
        pDialog = new ProgressDialog(LoginActivity.this);
        pDialog.setMessage("Loading Data.. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

        String email = txtusername.getText().toString();
        String password = txtpassword.getText().toString();

        login("wkkssks0g88sss004wko08ok44kkw80osss40gkc", email, password);

    }


    private void login(String key, String email, String password) {
        //login
        final UserLogin userLogin = new UserLogin();
        userLogin.setKey(key);
        userLogin.setEmail(email);
        userLogin.setPassword(password);
        RetroClient.getClient().create(Endpoint.class).responseUser(userLogin).enqueue(new Callback<UserList>() {
            @Override
            public void onResponse(Call<UserList> call, Response<UserList> response) {
                if (response.isSuccessful()) {
                    Gson gson = new Gson();
                    String j = gson.toJson(response.body());
                    Log.i("response",j);
                    Log.i("response2", response.raw().request().url().toString());
                    pDialog.dismiss();
                    if (response.body().data.getUser_id() != null ) {
                        String name = response.body().data.getFirst_name();
                        String userId = response.body().data.getUser_id();
                        String email = response.body().data.getUsername();
                        String phone = response.body().data.getPhone();
                        SharedPreferences sharedPref = getSharedPreferences("data",MODE_PRIVATE);
                        SharedPreferences.Editor prefEditor = sharedPref.edit();
                        prefEditor.putInt("isLogged",1);
                        prefEditor.putString("name",name);
                        prefEditor.putString("id",userId);
                        prefEditor.putString("email",email);
                        prefEditor.putString("phone",phone);
                        prefEditor.commit();
                        Intent inten = new Intent(LoginActivity.this, Home.class);
                        startActivity(inten);
                    }
//                    Log.i("YISC", "post submitted to API." + userList.g());

                }
            }

            @Override
            public void onFailure(Call<UserList> call, Throwable t) {

            }
        });

    }



}

