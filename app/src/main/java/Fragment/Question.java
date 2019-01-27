package Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

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
    TextView txtquestion;
    QuestionsList questionsList;
     RadioButton[] rb;
     RadioGroup rg;
     int qid = 1;
     QuestionsList currentQ ;
     LinearLayout mLinearLayout;
     private View view;
    String[] ab ={"1","2"};
    RadioButton radioButtonView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_question, container, false);

//        currentQ=listQuiz.get(qid);

//        loadData();
        takeAction();
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

        question(KEY_ANDROID, "", "2");
    }

    private void question(String key, String type, String quiz_id) {
        Log.i("masuk", "question");

        RequestBody u_key = RequestBody.create(MediaType.parse("text/plain"), key);
        RequestBody u_quizid = RequestBody.create(MediaType.parse("text/plain"), quiz_id);

        RetroClient.getClient().create(Endpoint.class).getQuestion(u_key, u_quizid).enqueue(new Callback<QuestionModel>() {
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
                        question_text = listQuiz.get(i).getQuestion_text();
                        quiz_time = listQuiz.get(i).getQuiz_time();
                        listOption = listQuiz.get(i).getOptions();

                        for (int k = 0; k < listOption.size(); k++) {
                            option_id = listOption.get(k).getOptionId();
                            option_no = listOption.get(k).getOptionNo();
                            option_text = listOption.get(k).getOptionText();
                            option_value = listOption.get(k).getOptionValue();
                        }

                       questionsList =  new QuestionsList(question_id,question_text, quiz_time, listOption);
                    }
                 Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                takeAction();

                            }
                        }, 3000);
                }
            }

            @Override
            public void onFailure(Call<QuestionModel> call, Throwable t) {
                Log.d("FAILED", call.toString());
                pDialog.dismiss();
            }
        });
    }



    private void takeAction()
    {
//        currentQ=listQuiz.get(qid);
        txtquestion = (TextView) view.findViewById(R.id.txtquestion);
//        txtquestion.setText(listQuiz.get(0).getQuestion_text());
//        txtquestion.setTextColor(Color.BLACK);
//        rg = new RadioGroup(getContext());
//        rg.setOrientation(RadioGroup.VERTICAL);
//        rb = new RadioButton[questionsList.getOptions().size()];
        mLinearLayout = (LinearLayout) view.findViewById(R.id.linear1);
//        for (int i = 0; i < questionsList.getOptions().size(); i++) {
//            rg.addView(rb[i]);
//            rb[i].setText("ditta");
//        }
//        mLinearLayout.addView(rg);
        final RadioGroup radioGroup = new RadioGroup(getContext());

        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        // adding Radio Group
        mLinearLayout.addView(radioGroup, p);

        // creating Radio buttons Object//
//        RadioButton radioButtonView = new RadioButton(this);


        for(int i =0; i<ab.length;i++)
        {
             radioButtonView = new RadioButton(getContext());
            radioButtonView.setText(ab[i]);
            radioButtonView.setId(i);
            radioGroup.addView(radioButtonView, p);
//            ((ViewGroup)mLinearLayout.getParent()).removeView(mLinearLayout);
        }
        Button btnnext = (Button) view.findViewById(R.id.btnnext);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selection = radioGroup.getCheckedRadioButtonId();
                for (int i = 0; i < ab.length; i++)
                {
                    if (i == selection)
                        Toast.makeText(getContext(),ab[i] ,Toast.LENGTH_LONG).show();
                }
//                currentQ=listQuiz.get(qid);
//                if(Integer.parseInt(question_id )== qid)
//                    setQuestionView();
            }
        });


    }

    private void setQuestionView(){
        txtquestion.setText(questionsList.getQuestion_text());
//        for (int i = 0; i < questionsList.getOptions().size(); i++) {
//            rg.addView(rb[i]);
//            rb[i].setText(questionsList.getOptions().get(i).getOptionText());
//        }
        mLinearLayout.addView(rg);

        qid++;
    }
}
