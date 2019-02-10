package Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
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

import Adapter.NilaiAdapter;
import connection.Endpoint;
import helper.RetroClient;
import model.Artikel;
import model.Datum;
import model.NilaiList;
import model.NilaiModel;
import model.QuestionModel;
import model.QuestionsList;
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
    final ArrayList<NilaiModel> nilaiArrayList = new ArrayList<>();
    NilaiAdapter nilaiAdapter;
    List<NilaiModel> listNilai;
    private int load = 1;
    private Context context;
    private boolean loadMore = false;
    String quiz, total, id, quizid, memberid, startdate, enddate, score, name, hp, email, createdat, updateat,quizmemberid;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nilai, container, false);

        lv = (ListView) view.findViewById(R.id.listViewNilai);

        nilaiAdapter = new NilaiAdapter(getContext(), nilaiArrayList);
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
        Bundle bundle = this.getArguments();
        quizid  = bundle.getString("quiz_id", "");
        SharedPreferences sharedPref = getActivity().getSharedPreferences("data",MODE_PRIVATE);
        memberid = sharedPref.getString("id", "");

        nilai(KEY_ANDROID,  memberid);
    }

    public void nilai(String key, String memberid) {
        final UserNilai userNilai = new UserNilai();
        userNilai.setKey(key);
        userNilai.setUser_id(memberid);
        RequestBody u_key = RequestBody.create(MediaType.parse("text/plain"), KEY_ANDROID);
        final RequestBody member_id = RequestBody.create(MediaType.parse("text/plain"), memberid);
        RetroClient.getClient().create(Endpoint.class).getNilai(u_key,member_id).enqueue(new Callback<NilaiList>() {
            @Override
            public void onResponse(Call<NilaiList> call, Response<NilaiList> response) {
                if(response.isSuccessful()){
                    listNilai = response.body().getData();
                    for(int i = 0; i< listNilai.size(); i++){
                        quiz = listNilai.get(i).getParticipant_quiz();
                        name = listNilai.get(i).getParticipant_name();
                        startdate = listNilai.get(i).getParticipant_startdate();
                        enddate = listNilai.get(i).getParticipant_enddate();
                        quizid = listNilai.get(i).getParticipant_quizid();
                        quizmemberid = listNilai.get(i).getParticipant_memberid();
                        score = listNilai.get(i).getParticipant_score();

                        nilaiArrayList.add(new NilaiModel(quiz,name,startdate,enddate,quizid,quizmemberid,score));
                    }
                }
            }

            @Override
            public void onFailure(Call<NilaiList> call, Throwable t) {

            }
        });


    }
}
