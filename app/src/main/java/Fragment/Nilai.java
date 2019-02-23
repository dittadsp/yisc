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
    String participant_quiz, total, id, quizid, memberid, participant_start_date, participant_end_date, score, participant_name, hp, email, createdat, updateat, userid;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nilai, container, false);

        lv = (ListView) view.findViewById(R.id.listViewNilai);

        nilaiAdapter = new NilaiAdapter(getActivity().getApplicationContext(), nilaiArrayList);
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
//        Bundle bundle = this.getArguments();
//        quizid  = bundle.getString("quiz_id", "");
        SharedPreferences sharedPref = getActivity().getSharedPreferences("data",MODE_PRIVATE);
        userid = sharedPref.getString("userid", "");

        nilai(KEY_ANDROID,  userid);
    }

    public void nilai(String key, String user_id) {
        final UserNilai userNilai = new UserNilai();
        userNilai.setKey(key);
        userNilai.setUser_id(user_id);
        RequestBody u_key = RequestBody.create(MediaType.parse("text/plain"), KEY_ANDROID);
        final RequestBody member_id = RequestBody.create(MediaType.parse("text/plain"), user_id);
        RetroClient.getClient().create(Endpoint.class).getNilai(u_key,member_id).enqueue(new Callback<NilaiList>() {
            @Override
            public void onResponse(Call<NilaiList> call, Response<NilaiList> response) {
                if(response.isSuccessful()){
                    pDialog.dismiss();
                    listNilai = response.body().getData();
                    for(int i = 0; i< listNilai.size(); i++){
                        participant_quiz = listNilai.get(i).getParticipant_quiz();
                        total = listNilai.get(i).getParticipant_score();
                        quizid = listNilai.get(i).getParticipant_quizid();
                        memberid = listNilai.get(i).getParticipant_memberid();
                        participant_start_date = listNilai.get(i).getParticipant_startdate();
                        participant_end_date = listNilai.get(i).getParticipant_enddate();
                        score = listNilai.get(i).getParticipant_score();
                        participant_name = listNilai.get(i).getParticipant_name();
                        hp = listNilai.get(i).getParticipant_enddate();
                        email = listNilai.get(i).getParticipant_enddate();
                        createdat = listNilai.get(i).getParticipant_enddate();
                        updateat = listNilai.get(i).getParticipant_memberid();
                        nilaiArrayList.add(new NilaiModel( participant_quiz, participant_name, participant_start_date, participant_end_date,
                                                   quizid, memberid, score));
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
