package Fragment;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import Adapter.MateriAdapter;
import connection.Endpoint;
import helper.RetroClient;
import model.Datum;
import model.MateriList;
import model.UserMateri;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.google.gson.Gson;
import com.memberapps2.R;


import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;


public class Materi extends Fragment {
    ListView lv;
    TextView tv;
    public static String KEY_ANDROID = "wkkssks0g88sss004wko08ok44kkw80osss40gkc";
    ProgressDialog pDialog;
    final ArrayList<MateriList.DatumMateri> materiArrayList = new ArrayList<>();
    MateriAdapter materiAdapter;
    List<MateriList.DatumMateri> listMateri;
    private int load = 1;
    private Context context;
    private boolean loadMore = false;
    String id, tema, category, semester, resume, video, file;
    String userid;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_materi, container, false);

        lv = (ListView) view.findViewById(R.id.listViewMateri);

        materiAdapter = new MateriAdapter(getActivity().getApplicationContext(), materiArrayList);
        lv.setAdapter(materiAdapter);

        loadData();
        return view;
    }

    private void loadData() {
        Log.i("masuk", "loadData");
        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading Data.. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
        SharedPreferences sp = getActivity().getSharedPreferences("data", MODE_PRIVATE);
//        userid = sp.getString("userid","");
        materi(KEY_ANDROID, "402");
    }

    public void materi(String key, String user_id) {

        RequestBody u_key = RequestBody.create(MediaType.parse("text/plain"), key);
        RequestBody u_id = RequestBody.create(MediaType.parse("text/plain"), user_id);

        RetroClient.getClient().create(Endpoint.class).getMateri(u_key, u_id).enqueue(new Callback<MateriList>() {
            @Override
            public void onResponse(Call<MateriList> call, Response<MateriList> response) {
                if (response.isSuccessful()) {
                    pDialog.dismiss();
                    Gson gson = new Gson();
                    String j = gson.toJson(response.body());
                    Log.i("responsemateri", j);
                    Log.i("responsemateri2", response.raw().request().url().toString());
                    listMateri = response.body().getData();
                    for (int i = 0; i < listMateri.size(); i++) {
                        id = listMateri.get(i).getId();
                        tema = listMateri.get(i).getTema();
                        category = listMateri.get(i).getCategory();
                        semester = listMateri.get(i).getSemester();
                        resume = listMateri.get(i).getResume();
                        video = (String) listMateri.get(i).getVideo();
                        file = (String) listMateri.get(i).getFile();
                        if (video == null) {
                            video = "kosong";
                        }
                        if (file == null) {
                            file = "kosong";
                        }
                        if (resume == null) {
                            resume = "kosong";
                        }
                        materiArrayList.add(new MateriList.DatumMateri(id, tema, category, semester, resume, video, file));
                    }
                    materiAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<MateriList> call, Throwable t) {

            }
        });
    }

    public void showDialog(String message, String content) {

        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(getContext(), android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(getContext());
        }
        builder.setTitle(message)
                .setMessage(Html.fromHtml(Html.fromHtml(content).toString()))
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton("", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }
}
