package Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
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
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.memberapps2.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import Adapter.QuestionAdapter;
import connection.Endpoint;
import helper.RetroClient;
import model.InfoListQuiz;
import model.InfoQuiz;
import model.MateriList;
import model.OptionList;
import model.Question;
import model.QuestionModel;
import model.QuestionsList;
import model.SubmitData;
import model.SubmitModel;
import model.UserLogin;
import model.UserMateri;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class QuestionFragment extends Fragment {
    ListView lv;
    public static String KEY_ANDROID = "wkkssks0g88sss004wko08ok44kkw80osss40gkc";
    ProgressDialog pDialog;
    final ArrayList<QuestionsList> quizArrayList = new ArrayList<>();
    final ArrayList<OptionList> optionArrayList = new ArrayList<>();
    QuestionAdapter questionAdapter;
    List<QuestionsList> listQuiz;
    List<OptionList> listOption;
    HashMap<String, String> arrQuest = new HashMap<String, String>();
    String question_id, quiz_time, question_text;
    String option_id, option_no, option_text, option_value,questionid_option;
    TextView txtquestion,txttimer;
    QuestionsList questionsList;
     int qid = 1,id;
     String user_id;
     LinearLayout mLinearLayout,sLinearLayout;
     private View view;
    String[] option ={"A","B","C","D","E"};
    RadioButton radioButtonView;
    RadioGroup radioGroup, radioGroup1;
    QuestionsList currentQ;
    OptionList currentO;
    Button btnsubmit;
    ArrayList<String> correctasw = new ArrayList<String>();
    Question question;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_question, container, false);
        txttimer = (TextView) view.findViewById(R.id.txttimer);
        btnsubmit = (Button) view.findViewById(R.id.btnsubmit);
        loadData();
        getCurrentDateTime();
        return view;
    }

    private String getCurrentDateTime(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String start = dateFormat.format(date);
        return start;
    }

    private void loadData() {
        Log.i("masuk", "loadData");
        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading Data.. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(true);
        pDialog.show();
        Bundle bundle = this.getArguments();
         id  = bundle.getInt("quiz_id", 0);
        question(KEY_ANDROID, "", String.valueOf(id));
        SharedPreferences sharedPref = getActivity().getSharedPreferences("data",MODE_PRIVATE);
        user_id = sharedPref.getString("id", "");
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
                        listOption = listQuiz.get(i).getOptions();
                        for (int k = 0; k < listOption.size(); k++) {
                            option_id = listOption.get(k).getOptionId();
                            option_no = listOption.get(k).getOptionNo();
                            option_text = listOption.get(k).getOptionText();
                            option_value = listOption.get(k).getOptionValue();
                            questionid_option = listOption.get(k).getQuestionId();
                        }
                        question_id = listQuiz.get(i).getQuestion_id();
                        question_text = listQuiz.get(i).getQuestion_text();
                        quiz_time = listQuiz.get(i).getQuiz_time();

                       questionsList =  new QuestionsList(question_id,question_text, quiz_time,listOption);
//                        takeAction();
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

    //Start Countodwn method
    private void startTimer(int noOfMinutes) {
        CountDownTimer countDownTimer = new CountDownTimer(noOfMinutes, 1000) {
            public void onTick(long millisUntilFinished) {
                long millis = millisUntilFinished;
                //Convert milliseconds into hour,minute and seconds
                String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis), TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)), TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
                txttimer.setText(hms);//set text
            }

            public void onFinish() {

                txttimer.setText("TIME'S UP!!"); //On finish change timer text

            }
        }.start();

    }

    private void takeAction()
    {
        int noOfMinutes = Integer.parseInt(questionsList.getQuiz_time()) * 60 * 1000;
        startTimer(noOfMinutes);
        txtquestion = (TextView) view.findViewById(R.id.txtquestion);
        txtquestion.setText(listQuiz.get(0).getQuestion_text());
        txtquestion.setTextColor(Color.BLACK);
        mLinearLayout = (LinearLayout) view.findViewById(R.id.linear1);
        radioGroup = new RadioGroup(getContext());

        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );


        mLinearLayout.addView(radioGroup, p);
           for (int i = 0; i < listQuiz.get(0).getOptions().size(); i++) {
                radioButtonView = new RadioButton(getContext());
                radioButtonView.setText(listQuiz.get(0).getOptions().get(i).getOptionText());
                radioButtonView.setId(i);
                radioGroup.addView(radioButtonView, p);
            }
        Button btnnext = (Button) view.findViewById(R.id.btnnext);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selection = radioGroup.getCheckedRadioButtonId();
                for (int i = 0; i < questionsList.getOptions().size(); i++)
                {
                    if (i == selection) {
                        Toast.makeText(getContext(), option[i], Toast.LENGTH_LONG).show();
                        correctasw.add(option[i]);
                    }
                }
                setQuestionView();

            }

        });

    }

    private void setQuestionView(){
        mLinearLayout.removeAllViews();
        txtquestion.setText(listQuiz.get(qid).getQuestion_text());
        mLinearLayout = (LinearLayout) view.findViewById(R.id.linear1);
        radioGroup1 = new RadioGroup(getContext());

        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );


        mLinearLayout.addView(radioGroup1, p);
        for(int i =0; i<listQuiz.get(qid).getOptions().size();i++)
        {
            radioButtonView = new RadioButton(getContext());
            radioButtonView.setText(listQuiz.get(qid).getOptions().get(i).getOptionText());
            radioButtonView.setId(i);
            radioGroup1.addView(radioButtonView, p);
        }

        Button btnnext = (Button) view.findViewById(R.id.btnnext);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selection = radioGroup1.getCheckedRadioButtonId();
                for (int i = 0; i < questionsList.getOptions().size(); i++)
                {
                    if (i == selection) {
                        Toast.makeText(getContext(), option[i], Toast.LENGTH_LONG).show();
                        correctasw.add(option[i]);
                    }
                }
                setQuestionView();
            }
        });

        if(qid == listQuiz.size()-1){
            btnsubmit.setVisibility(View.VISIBLE);
            btnsubmit.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    int selection = radioGroup1.getCheckedRadioButtonId();
                    for (int i = 0; i < questionsList.getOptions().size(); i++)
                    {
                        if (i == selection) {
                            Toast.makeText(getContext(), option[i], Toast.LENGTH_LONG).show();
                            correctasw.add(option[i]);
                        }
                    }

                    submitAnswer2();
                }
            });
            btnnext.setVisibility(View.GONE);
        }

        qid++;
    }


    private void submitAnswer2(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String end = dateFormat.format(date);
        question = new Question();

        for(int i = 0; i<listQuiz.size();i++){
          question.setQuestion_id(listQuiz.get(i).getQuestion_id());
          question.setQuestion_answer(correctasw.get(i));
        }
        final SubmitModel submitModel = new SubmitModel(KEY_ANDROID,user_id,""+id,getCurrentDateTime(),end,question);
        RetroClient.getClient().create(Endpoint.class).responseSubmit(submitModel).enqueue(new Callback<SubmitData>() {
            @Override
            public void onResponse(Call<SubmitData> call, Response<SubmitData> response) {
                if (response.isSuccessful()) {
                    Gson gson = new Gson();
                    String j = gson.toJson(response.body());
                    Log.d("ISI",j);
                    Log.d("RESPONSE",response.raw().request().url().toString());
                }
            }

            @Override
            public void onFailure(Call<SubmitData> call, Throwable t) {
                Log.d("FAILED", call.toString());
            }
        });
    }


    private void submitAnswer() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String end = dateFormat.format(date);

        for(int i = 0; i<listQuiz.size();i++){
            arrQuest.put(listQuiz.get(i).getQuestion_id(),correctasw.get(i));
        }

        RequestBody u_key = RequestBody.create(MediaType.parse("text/plain"), KEY_ANDROID);
        RequestBody u_user_id = RequestBody.create(MediaType.parse("text/plain"), "3");
        RequestBody u_quizid = RequestBody.create(MediaType.parse("text/plain"), ""+id);
        RequestBody u_date_start = RequestBody.create(MediaType.parse("text/plain"), ""+getCurrentDateTime());
        RequestBody u_date_end = RequestBody.create(MediaType.parse("text/plain"), ""+end);
        RequestBody u_question_id = RequestBody.create(MediaType.parse("text/plain"), ""+listQuiz);
        RequestBody u_question_asw= RequestBody.create(MediaType.parse("text/plain"), ""+correctasw);

        RetroClient.getClient().create(Endpoint.class).submitQuiz(u_key,u_user_id,u_quizid,u_date_start,u_date_end,u_question_id,u_question_asw).enqueue(new Callback<SubmitData>() {
            @Override
            public void onResponse(Call<SubmitData> call, Response<SubmitData> response) {
                if (response.isSuccessful()) {
                    Gson gson = new Gson();
                    String j = gson.toJson(response.body());
                    Log.d("ISI",j);
                    Log.d("RESPONSE",response.raw().request().url().toString());
                }
            }

            @Override
            public void onFailure(Call<SubmitData> call, Throwable t) {
                Log.d("FAILED", call.toString());
            }
        });

    }
}
