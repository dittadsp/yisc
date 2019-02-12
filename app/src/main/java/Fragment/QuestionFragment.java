package Fragment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
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
import com.memberapps2.Home;
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
        quiz_id  = bundle.getString("quiz_id", "");
        question(KEY_ANDROID, "", ""+quiz_id);
        SharedPreferences sharedPref = getActivity().getSharedPreferences("data",MODE_PRIVATE);
        member_id = sharedPref.getString("id", "");
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
                submitAnswerSuccess();

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
                        Toast.makeText(getContext(), listOption.get(i).getOptionValue(), Toast.LENGTH_LONG).show();
                        correctasw.add( listOption.get(i).getOptionValue());
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
                        Toast.makeText(getContext(), listOption.get(i).getOptionValue(), Toast.LENGTH_LONG).show();
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
                    for (int i = 0; i < questionsList.getOptions().size(); i++)
                    {
                        if (i == selection) {
                            Toast.makeText(getContext(),listOption.get(i).getOptionValue(), Toast.LENGTH_LONG).show();
                            correctasw.add( listOption.get(i).getOptionValue());
                        }
                    }

                    submitAnswerSuccess();
                }
            });
            btnnext.setVisibility(View.GONE);
        }

        qid++;
    }

    private void submitAnswerSuccess(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String end = dateFormat.format(date);
        HashMap<String,Object> param = new HashMap<>();
        questions = new ArrayList<Question>();
        param.put("key",KEY_ANDROID);
        param.put("member_id",member_id);
        param.put("quiz_id",quiz_id);
        param.put("date_start",getCurrentDateTime());
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
            builder = new AlertDialog.Builder(getContext(), android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(getContext());
        }
        builder.setTitle(message)
                .setMessage("Score "+"\n"+score+"Correct Answer "+correctasw+"\n"+"Wrong Answer "+wrongasw+"\n"+"Total Question "+totalquestion)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent myIntent = new Intent(getActivity(), Home.class);
                        getActivity().startActivity(myIntent);

                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent myIntent = new Intent(getActivity(), Home.class);
                        getActivity().startActivity(myIntent);

                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private void showDialogFailed(String message,String content){
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(getContext(), android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(getContext());
        }
        builder.setTitle(message)
                .setMessage(content)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent myIntent = new Intent(getActivity(), Home.class);
                        getActivity().startActivity(myIntent);

                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent myIntent = new Intent(getActivity(), Home.class);
                        getActivity().startActivity(myIntent);

                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }


    private void submitAnswer2(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String end = dateFormat.format(date);
        questions = new ArrayList<Question>();
        String question_id, question_answer;
        for(int i = 0; i<listQuiz.size();i++){
            question_id = listQuiz.get(i).getQuestion_id();
            question_answer = correctasw.get(i);
            questions.add(new Question(question_id,question_answer));
        }

        final SubmitModel submitModel = new SubmitModel(KEY_ANDROID,"1190240",""+quiz_id,getCurrentDateTime(),end,questions);
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
        RequestBody u_user_id = RequestBody.create(MediaType.parse("text/plain"), member_id);
        RequestBody u_quizid = RequestBody.create(MediaType.parse("text/plain"), ""+quiz_id);
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
