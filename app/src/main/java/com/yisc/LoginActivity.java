package com.yisc;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.solver.Goal;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//import adapter.UserAdapter;
import javax.net.ssl.HttpsURLConnection;

import connection.Endpoint;
import connection.Endpoint;
//import connection.ServerConnection;
//import connection.Service;
import helper.RetroClient;
import model.MainApplication;
//import model.User;
//import model.UserList;
import model.UserList;
import model.UserLogin;
import model.UserMember;
import okhttp3.ResponseBody;
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
                    if(response.body().data.getMember_id() == null){
                        showDialogFailed("WARNING","username/password salah\n"+" " +
                                "silahkan forgot password di http://internal.yisc-alazhar.or.id");
                    }
                    if (response.body().data.getMember_id() != null ) {
                        String name = response.body().data.getFirst_name();
                        String member_Id = response.body().data.getMember_id();
                        String email = response.body().data.getUsername();
                        String phone = response.body().data.getPhone();
                        SharedPreferences sharedPref = getSharedPreferences("data",MODE_PRIVATE);
                        SharedPreferences.Editor prefEditor = sharedPref.edit();
                        prefEditor.putInt("isLogged",1);
                        prefEditor.putString("name",name);
                        prefEditor.putString("id",member_Id);
                        prefEditor.putString("email",email);
                        prefEditor.putString("phone",phone);
                        prefEditor.commit();
                        if(member_Id == null){
                            showDialogFailed("WARNING","username/password salah\n"+" " +
                                    "silahkan forgot password di http://internal.yisc-alazhar.or.id");
                        }else{
                            Intent inten = new Intent(LoginActivity.this, Home.class);
                            startActivity(inten);
                        }

                    }
                    if (response.body().data.getMember_id() == null ) {
                        showDialogFailed("WARNING","username/password salah\n"+" " +
                                "silahkan forgot password di http://internal.yisc-alazhar.or.id");
                    }
                }
            }

            @Override
            public void onFailure(Call<UserList> call, Throwable t) {
                showDialogFailed("WARNING","username/password salah\n"+" " +
                        "silahkan forgot password di http://internal.yisc-alazhar.or.id");
            }
        });

    }


    private void showDialogFailed(String message,String content){
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(this);
        }
        builder.setTitle(message)
                .setMessage(content)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent myIntent = new Intent(getApplicationContext(), Home.class);
                        myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        getApplicationContext().startActivity(myIntent);

                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent myIntent = new Intent(getApplicationContext(), Home.class);
                        myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        getApplicationContext().startActivity(myIntent);

                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}

