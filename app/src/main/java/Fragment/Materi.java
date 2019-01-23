package Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import Adapter.MateriAdapter;
import connection.Endpoint;
import helper.RetroClient;
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
    public static String KEY_ANDROID = "wkkssks0g88sss004wko08ok44kkw80osss40gkc";
    ProgressDialog pDialog;
    final ArrayList<MateriList.DatumMateri> materiArrayList = new ArrayList<>();
    MateriAdapter materiAdapter;
    List<MateriList.DatumMateri> listMateri;
    private int load = 1;
    private Context context;
    private boolean loadMore = false;
    String id, tema, category, semester, resume, video, file;

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
        String id = sp.getString("id","");

        materi(KEY_ANDROID, "", "23");
    }

    public void materi(String key, String type, String user_id) {

        final UserMateri userMateri = new UserMateri();
        userMateri.setKey(key);
        userMateri.setType(type);
        userMateri.setUser_id(user_id);
        RequestBody u_key = RequestBody.create(MediaType.parse("text/plain"), "wkkssks0g88sss004wko08ok44kkw80osss40gkc");
        RequestBody u_type = RequestBody.create(MediaType.parse("text/plain"), "1");
        RequestBody u_id = RequestBody.create(MediaType.parse("text/plain"), "23");
//        Log.i("valuemateri", userMateri.getKey() + " " + userMateri.getUser_id());

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
                        if (video == null){
                            video = "kosong";
                        }
                        if  (file == null){
                            file = "kosong";
                        }
                        materiArrayList.add(new MateriList.DatumMateri(id,tema,category,semester,resume,video,file));
                    }
                    materiAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<MateriList> call, Throwable t) {

            }
        });
    }
}
