package com.yisc;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.gson.Gson;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import Adapter.QuestionAdapter;
import connection.Endpoint;
import entity.BroadcastService;
import helper.RetroClient;
import model.OptionList;
import model.Question;
import model.QuestionModel;
import model.QuestionsList;
import model.SubmitData;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionActivity1 extends Activity {
    private String str_time;
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
    SharedPreferences sharedPref;
    int qid = 1;
    String user_id,quiz_id,member_id;
    LinearLayout mLinearLayout,sLinearLayout;
    private View view;
    String[] option ={"A","B","C","D","E"};
    RadioButton radioButtonView;
    RadioGroup radioGroup, radioGroup1;
    QuestionsList currentQ;
    OptionList currentO;
    Button btnsubmit;
    ArrayList<String> correctasw = new ArrayList<String>();
    ArrayList<Question> questions;
    public boolean mTimerRunning;
    int startDateTime = 0;
    int time = 0;
    int flag = 0;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_question);
        txttimer = (TextView) findViewById(R.id.txttimer);
        btnsubmit = (Button) findViewById(R.id.btnsubmit);

        getCurrentDateTime();
        loadData();
    }

    private String getCurrentDateTime(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String start = dateFormat.format(date);
        SharedPreferences sharedPref = getSharedPreferences( "data",MODE_PRIVATE);
        SharedPreferences sharedPref1 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor prefEditor = sharedPref.edit();
        prefEditor.putString("datestart",start);
        prefEditor.commit();
        return start;
    }


    private void loadData() {
        Log.i("masuk", "loadData");
        if(flag== 1){
            showDialogFailed("warning","time is up");
        }
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading Data.. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(true);
        pDialog.show();
        Intent mIntent = new Intent(this, QuestionActivity1.class);
//        Bundle bundle = mIntent.getExtras();
//        quiz_id  = getIntent().getExtras().getString("quiz_id");
        SharedPreferences sharedPref = getSharedPreferences("data",MODE_PRIVATE);
        String quiz_id = sharedPref.getString("quiz_id","");
         flag = sharedPref.getInt("flaquiz",0);

        question(KEY_ANDROID, "", ""+quiz_id);
        Intent getIntent = getIntent();
        member_id = sharedPref.getString("id","");

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
                    }
//                    Handler handler = new Handler();
//                    handler.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
                            takeAction(quiz_time);

//                        }
//                    }, 3000);
                }
            }

            @Override
            public void onFailure(Call<QuestionModel> call, Throwable t) {
                Log.d("FAILED", call.toString());
                pDialog.dismiss();
            }
        });
    }


    private void takeAction(String quiz_time)
    {

        if(txttimer.getText().toString().equals("00:00")){
            showDialogFailed("warning","your time is up");
        }
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        String date_time = simpleDateFormat.format(calendar.getTime());
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor prefEditor = sharedPref.edit();
        prefEditor.putString("calendar", date_time);
        prefEditor.putString("datettime", quiz_time);
        prefEditor.commit();

        txtquestion = (TextView) findViewById(R.id.txtquestion);
        txtquestion.setText(listQuiz.get(0).getQuestion_text());
        txtquestion.setTextColor(Color.BLACK);
        mLinearLayout = (LinearLayout) findViewById(R.id.linear1);
        radioGroup = new RadioGroup(this);

        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        p.setMargins(15, 15, 15, 15);

        mLinearLayout.addView(radioGroup, p);
        for (int i = 0; i < listQuiz.get(0).getOptions().size(); i++) {
            radioButtonView = new RadioButton(this);
            radioButtonView.setText(listQuiz.get(0).getOptions().get(i).getOptionText());
            radioButtonView.setId(i);
            radioGroup.addView(radioButtonView, p);
        }
        Button btnnext = (Button)findViewById(R.id.btnnext);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selection = radioGroup.getCheckedRadioButtonId();
                if(selection == -1){
                    showDialogWarning("warning","please select your answer");
                }else {
                    for (int i = 0; i < questionsList.getOptions().size(); i++) {
                        if (i == selection) {
//                        Toast.makeText(getApplicationContext(), listOption.get(i).getOptionValue(), Toast.LENGTH_LONG).show();
                            correctasw.add(listOption.get(i).getOptionValue());
                        }
                    }
                    setQuestionView();
                }

            }

        });

    }

    private void setQuestionView(){
        mLinearLayout.removeAllViews();
        txtquestion.setText(listQuiz.get(qid).getQuestion_text());
        mLinearLayout = (LinearLayout) findViewById(R.id.linear1);
        radioGroup1 = new RadioGroup(this);

        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        p.setMargins(15, 15, 15, 15);

        mLinearLayout.addView(radioGroup1, p);
        for(int i =0; i<listQuiz.get(qid).getOptions().size();i++)
        {
            radioButtonView = new RadioButton(this);
            radioButtonView.setText(listQuiz.get(qid).getOptions().get(i).getOptionText());
            radioButtonView.setId(i);
            radioGroup1.addView(radioButtonView, p);
        }

        Button btnnext = (Button) findViewById(R.id.btnnext);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selection = radioGroup1.getCheckedRadioButtonId();

                for (int i = 0; i < questionsList.getOptions().size(); i++)
                {
                    if (i == selection) {
//                        Toast.makeText(getApplicationContext(), listOption.get(i).getOptionValue(), Toast.LENGTH_LONG).show();
                        correctasw.add(listOption.get(i).getOptionValue());
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
                    if(selection == -1){
                        showDialogWarning("warning","please select your answer");
                    }else {
                        for (int i = 0; i < questionsList.getOptions().size(); i++) {
                            if (i == selection) {
                                correctasw.add(listOption.get(i).getOptionValue());
                            }
                        }

                        submitAnswerSuccess();
                    }
                }
            });
            btnnext.setVisibility(View.GONE);
        }

        qid++;
    }

    private void submitAnswerSuccess(){

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor prefEditor = sharedPref.edit();
        prefEditor.putInt("flagquiz", 1);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();

        String end = dateFormat.format(date);
       String start = sharedPref.getString("datestart", "");
        HashMap<String,Object> param = new HashMap<>();
        questions = new ArrayList<Question>();
        param.put("key",KEY_ANDROID);
        param.put("member_id",member_id);
        param.put("quiz_id",quiz_id);
        param.put("date_start",start);
        param.put("date_end",end);
        for(int i = 0; i<listQuiz.size();i++){
            param.put("questions["+i+"][question_id]",listQuiz.get(i).getQuestion_id());
            param.put("questions["+i+"][question_answer]",correctasw.get(i));
        }
        RetroClient.getClient().create(Endpoint.class).responseSubmitQuiz(param).enqueue(new Callback<SubmitData>() {
            @Override
            public void onResponse(Call<SubmitData> call, Response<SubmitData> response) {
                if (response.isSuccessful()) {
                    Gson gson = new Gson();
                    String j = gson.toJson(response.body());
                    int correctasw,wrongasw,score,totalquestion;
                    String message,title;
                    boolean status;
                    status = response.body().getStatus();
                    message = response.body().getMessage();
                    if(status == true) {
                        score = response.body().getData().getScore();
                        correctasw = response.body().getData().getCorrect_answer();
                        wrongasw = response.body().getData().getWrong_answer();
                        totalquestion = response.body().getData().getTotal_question();
                        showDialogSuccess(message,score,correctasw,wrongasw,totalquestion);
                        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                        SharedPreferences.Editor prefEditor = sharedPref.edit();
                        prefEditor.putString("quiz_id", quiz_id);
                        prefEditor.commit();

                    }else{
                        showDialogFailed(message,message);

                    }

                }
            }

            @Override
            public void onFailure(Call<SubmitData> call, Throwable t) {
                Log.d("FAILED", call.toString());
            }
        });
    }

    private void showDialogSuccess(String message,int score,int correctasw,int wrongasw,int totalquestion){
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(this);
        }
        builder.setTitle(message)
                .setMessage("Score "+"\n"+score+"Correct Answer "+correctasw+"\n"+"Wrong Answer "+wrongasw+"\n"+"Total Question "+totalquestion)
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

    private void showDialogWarning(String message,String content){
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


                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {


                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
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

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(broadcastReceiver,new IntentFilter(BroadcastService.str_receiver));

    }

    @Override
    protected void onPause() {
        super.onPause();
//        unregisterReceiver(broadcastReceiver);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent myIntent = new Intent(this, Home.class);
        startActivity(myIntent);
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
              String finish = intent.getStringExtra("finish");

             if(finish!=null && finish.equals("finish"))
             {
                 showDialogFailed("Warning","TIME'S UP!");
                 SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                 SharedPreferences.Editor prefEditor = sharedPref.edit();
                 prefEditor.putString("time", quiz_id);
             }
                  str_time = intent.getStringExtra("time");
                 txttimer.setText(str_time);
                 if(str_time.equals("00:00")|| str_time.equals("0:00")|| str_time.equals(0)){
                     showDialogFailed("Warning","TIME'S UP!");
                     SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                     SharedPreferences.Editor prefEditor = sharedPref.edit();
                     prefEditor.putString("time", quiz_id);


                 }
             }

    };

    @Override
    protected void onStart() {
        super.onStart();

    }
}
