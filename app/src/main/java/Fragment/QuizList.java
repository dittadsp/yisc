package Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.memberapps2.R;

import java.util.ArrayList;
import java.util.List;

import Adapter.QuizAdapter;
import connection.Endpoint;
import helper.RetroClient;
import model.InfoListQuiz;
import model.InfoQuiz;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class QuizList extends Fragment {
    ListView lv;
    public static String KEY_ANDROID ="wkkssks0g88sss004wko08ok44kkw80osss40gkc";
    ProgressDialog pDialog;
    final ArrayList<InfoQuiz> quizArrayList = new ArrayList<>();
    QuizAdapter quizAdapter;
    List<InfoQuiz> listQuiz;
    StringBuffer sb;
    private int load = 1;
    private Context context;
    private boolean loadMore = false;
    String quiz_id, quiz_title, quiz_desc,  quiz_start_date, quiz_end_date,quiz_status;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz, container, false);

        lv = (ListView) view.findViewById(R.id.listViewQuiz);

        quizAdapter = new QuizAdapter(getActivity().getApplicationContext(), quizArrayList);
        lv.setAdapter(quizAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Question question = new Question();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.framebsq, question);
                fragmentTransaction.commit();
            }

        });
        loadData();
        return view;
    }

    private void loadData() {
        Log.i("masuk", "loadData");
        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading Data.. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(true);
        pDialog.show();
        SharedPreferences sharedPref = getActivity().getSharedPreferences("data",MODE_PRIVATE);
        String id = sharedPref.getString("id", "");

        schedule(KEY_ANDROID,"23");
    }

    private void schedule(String key,  String user_id) {

        RequestBody u_key = RequestBody.create(MediaType.parse("text/plain"), key);
        RequestBody u_id = RequestBody.create(MediaType.parse("text/plain"), user_id);

        RetroClient.getClient().create(Endpoint.class).getQuiz(u_key,u_id).enqueue(new Callback<InfoListQuiz>() {
            @Override
            public void onResponse(Call<InfoListQuiz> call, Response<InfoListQuiz> response) {
                if (response.isSuccessful()) {
                    pDialog.dismiss();
                    Gson gson = new Gson();
                    String j = gson.toJson(response.body());
                    Log.i("response",j);
                    Log.i("response2", response.raw().request().url().toString());
                    pDialog.dismiss();

                    listQuiz = response.body().getData();
                    for(int i = 0 ; i< listQuiz.size() ; i++) {
                        quiz_id       = listQuiz.get(i).getQuiz_id();
                        quiz_title     = listQuiz.get(i).getQuiz_title();
                        quiz_desc  = listQuiz.get(i).getQuiz_desc();
                        quiz_status    = listQuiz.get(i).getQuiz_status();
                        quiz_start_date     = listQuiz.get(i).getQuiz_start_date();
                        quiz_end_date = listQuiz.get(i).getQuiz_end_date();
                        quizArrayList.add(new InfoQuiz(quiz_id,quiz_title,quiz_desc,quiz_start_date,quiz_end_date,quiz_status));
                    }

                    quizAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<InfoListQuiz> call, Throwable t) {
                Log.d("FAILED",call.toString());
            }
        });

    }

}