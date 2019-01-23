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

import com.google.gson.Gson;
import com.memberapps2.R;

import java.util.ArrayList;
import java.util.List;

import connection.Endpoint;
import helper.RetroClient;
import model.NilaiList;
import model.UserNilai;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class Nilai extends Fragment {
    ListView lv;
    public static String KEY_ANDROID = "wkkssks0g88sss004wko08ok44kkw80osss40gkc";
    ProgressDialog pDialog;
    final ArrayList<NilaiList.Datum> nilaiArrayList = new ArrayList<>();
    adapter.NilaiAdapter nilaiAdapter;
    List<NilaiList.Datum> listNilai;
    private int load = 1;
    private Context context;
    private boolean loadMore = false;
    String quiz, total, id, quizid, memberid, startdate, enddate, score, name, hp, email, createdat, updateat;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nilai, container, false);

        lv = (ListView) view.findViewById(R.id.listViewNilai);

        nilaiAdapter = new adapter.NilaiAdapter(getActivity().getApplicationContext(), nilaiArrayList);
        lv.setAdapter(nilaiAdapter);
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
        String id = sp.getString("id", "");

        nilai(KEY_ANDROID, "", "23");
    }

    public void nilai(String key, String type, String user_id) {
        final UserNilai userNilai = new UserNilai();
        userNilai.setKey(key);
        userNilai.setType(type);
        userNilai.setUser_id(user_id);
        RequestBody u_key = RequestBody.create(MediaType.parse("text/plain"), "wkkssks0g88sss004wko08ok44kkw80osss40gkc");
        RequestBody u_type = RequestBody.create(MediaType.parse("text/plain"), "");
        RequestBody u_id = RequestBody.create(MediaType.parse("text/plain"), "23");

        RetroClient.getClient().create(Endpoint.class).getNilai(u_key, u_id).enqueue(new Callback<NilaiList>() {
            @Override
            public void onResponse(Call<NilaiList> call, Response<NilaiList> response) {
                if (response.isSuccessful()) {
                    pDialog.dismiss();
                    Gson gson = new Gson();
                    String j = gson.toJson(response.body());
                    Log.i("responsenilai", j);
                    Log.i("responsenilai2", response.raw().request().url().toString());
                    listNilai = response.body().getData();
                    for (int i = 0; i < listNilai.size(); i++) {
                        quiz = listNilai.get(i).getParticipantQuiz();
                        total = listNilai.get(i).getParticipantTotal();
                        id = listNilai.get(i).getId();
                        memberid = listNilai.get(i).getParticipantMemberid();
                        startdate = listNilai.get(i).getParticipantStartdate();
                        enddate = listNilai.get(i).getParticipantEnddate();
                        score = listNilai.get(i).getParticipantScore();
                        name = listNilai.get(i).getParticipantName();
                        hp = listNilai.get(i).getParticipantHp();
                        email = listNilai.get(i).getParticipantEmail();
                        createdat = listNilai.get(i).getCreatedAt();
                        updateat = listNilai.get(i).getUpdatedAt();
                        nilaiArrayList.add(new NilaiList.Datum(quiz, total, id, quizid, memberid, startdate, enddate, score, name, hp, email, createdat, updateat));
                    }
                    nilaiAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<NilaiList> call, Throwable t) {

            }
        });
    }
}
