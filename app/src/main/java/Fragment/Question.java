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

import Adapter.QuestionAdapter;
import connection.Endpoint;
import helper.RetroClient;
import model.InfoListQuiz;
import model.InfoQuiz;
import model.MateriList;
import model.OptionList;
import model.QuestionModel;
import model.QuestionsList;
import model.UserMateri;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class Question extends Fragment {
    ListView lv;
    public static String KEY_ANDROID = "wkkssks0g88sss004wko08ok44kkw80osss40gkc";
    ProgressDialog pDialog;
    final ArrayList<QuestionsList> quizArrayList = new ArrayList<>();
    final ArrayList<OptionList> optionArrayList = new ArrayList<>();
    QuestionAdapter questionAdapter;
    List<QuestionsList> listQuiz;
    List<OptionList> listOption;
    StringBuffer sb;
    private int load = 1;
    private Context context;
    private boolean loadMore = false;
    String question_id, quiz_id, quiz_title, quiz_desc, quiz_time, question_text;
    String option_id, option_no, option_text, option_value;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_question, container, false);
        lv = (ListView) view.findViewById(R.id.listViewQuestion);
        QuestionAdapter questionAdapter = new QuestionAdapter(getActivity().getApplicationContext(), quizArrayList);
        lv.setAdapter(questionAdapter);
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
        SharedPreferences sharedPref = getActivity().getSharedPreferences("data", MODE_PRIVATE);
        String id = sharedPref.getString("id", "");

        question(KEY_ANDROID, "", "1");
    }

    private void question(String key, String type, String quiz_id) {
        Log.i("masuk", "question");

        RequestBody u_key = RequestBody.create(MediaType.parse("text/plain"), key);
        RequestBody u_id = RequestBody.create(MediaType.parse("text/plain"), quiz_id);

        RetroClient.getClient().create(Endpoint.class).getQuestion(u_key, u_id).enqueue(new Callback<QuestionModel>() {
            @Override
            public void onResponse(Call<QuestionModel> call, Response<QuestionModel> response) {
                if (response.isSuccessful()) {
                    pDialog.dismiss();
                    Gson gson = new Gson();
                    String j = gson.toJson(response.body());
                    Log.i("responsequestion", j);
                    Log.i("responsequestion2", response.raw().request().url().toString());

                    listQuiz = response.body().getData();
                    for (int i = 0; i < listQuiz.size(); i++) {
                        question_id = listQuiz.get(i).getQuiz_id();
                        quiz_title = listQuiz.get(i).getQuiz_title();
                        quiz_desc = listQuiz.get(i).getQuiz_desc();
                        question_text = listQuiz.get(i).getQuestion_text();
                        quiz_time = listQuiz.get(i).getQuiz_time();

//                        for (int k = 0; k < listOption.size(); k++) {
//                            option_id = listOption.get(k).getOptionId();
//                            option_no = listOption.get(k).getOptionNo();
//                            option_text = listOption.get(k).getOptionText();
//                            option_value = listOption.get(k).getOptionValue();
//                        }
                        quizArrayList.add(new QuestionsList(question_id, quiz_title, quiz_desc, question_text, quiz_time, listOption));
                    }
                    questionAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<QuestionModel> call, Throwable t) {
                Log.d("FAILED", call.toString());
                pDialog.dismiss();
            }
        });
    }
}
