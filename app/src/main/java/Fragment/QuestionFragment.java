package Fragment;

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
import android.os.CountDownTimer;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
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
//import entity.BroadcastService;
//import entity.IOnBackPressed;
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

import static android.content.Context.MODE_PRIVATE;

//public class QuestionFragment extends Fragment implements IOnBackPressed {
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
    SharedPreferences sharedPref;
    public boolean mTimerRunning;
    int startDateTime = 0;
    int time = 0;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_question, container, false);
        txttimer = (TextView) view.findViewById(R.id.txttimer);
        btnsubmit = (Button) view.findViewById(R.id.btnsubmit);

        getCurrentDateTime();
//        getActivity().startService(new Intent(getActivity(), BroadcastService.class));
//        Log.i("START", "Started service");
        loadData();

        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                   showDialogFailed("WARNING","You Can not Back while taking quiz");
                    return true;
                }
                return false;
            }
        });
        return view;
    }

    private String getCurrentDateTime(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String start = dateFormat.format(date);
        SharedPreferences sharedPref = getActivity().getSharedPreferences("data",MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = sharedPref.edit();
        prefEditor.putString("datestart",start);
        prefEditor.commit();
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
        SharedPreferences sharedPref = getActivity().getSharedPreferences("data",MODE_PRIVATE);
        question(KEY_ANDROID, "", ""+quiz_id);
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
                        takeAction();
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
//        SharedPreferences sharedPref = getActivity().getSharedPreferences("data",MODE_PRIVATE);
//        int startDateTime = sharedPref.getInt("datettime", 0);
//        if(startDateTime!=0){
//            time = startDateTime;
//        }else{
//            time = noOfMinutes;
//        }
        //Convert milliseconds into hour,minute aand seconds

        CountDownTimer countDownTimer = new CountDownTimer(noOfMinutes, 1000) {
            public void onTick(long millisUntilFinished) {
                    long millis = millisUntilFinished;
                    String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis), TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)), TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
                    txttimer.setText(hms);
            }

            public void onFinish() {
                txttimer.setText("TIME'S UP!!"); //On finish change timer text
                submitAnswerSuccess();

            }
        }.start();

    }

    private BroadcastReceiver br = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            updateGUI(intent); // or whatever method used to update your GUI fields
        }
    };

    private void updateGUI(Intent intent) {
        if (intent.getExtras() != null) {
            long millisUntilFinished = intent.getLongExtra("countdown", 0);
            long millis = millisUntilFinished;
            String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis), TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)), TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
            txttimer.setText(hms);
            Log.i("UPDATE GUI", "Countdown seconds remaining: " +  millisUntilFinished / 1000);
        }
    }

    private void takeAction()
    {
        int noOfMinutes = Integer.parseInt(questionsList.getQuiz_time()) * 60 * 1000;
        time = noOfMinutes;
//        SharedPreferences sharedPref = getActivity().getSharedPreferences("data",MODE_PRIVATE);
//        SharedPreferences.Editor prefEditor = sharedPref.edit();
//        prefEditor.putInt("datettime", time);
//        prefEditor.commit();
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

        p.setMargins(15, 15, 15, 15);

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
        p.setMargins(15, 15, 15, 15);

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
        SharedPreferences sharedPref = getActivity().getSharedPreferences("data",MODE_PRIVATE);
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


//    @Override
//    public void onStop() {
//        SharedPreferences sharedPref = getActivity().getSharedPreferences("data",MODE_PRIVATE);
//        SharedPreferences.Editor prefEditor = sharedPref.edit();
//        prefEditor.putInt("datettime", time);
//        prefEditor.commit();
//        super.onStop();
//    }


    @Override
    public void onResume() {
        super.onResume();
//        getActivity().registerReceiver(br, new IntentFilter(BroadcastService.COUNTDOWN_BR));
        Log.i("on resume", "Registered broacast receiver");
    }

    @Override
    public void onPause() {
        super.onPause();
//        getActivity().unregisterReceiver(br);
        Log.i("on pause", "Unregistered broacast receiver");
    }

    @Override
    public void onStop() {
        try {
//            getActivity().unregisterReceiver(br);
        } catch (Exception e) {
            // Receiver was probably already stopped in onPause()
        }
        super.onStop();
    }

    @Override
    public void onDestroy() {
//        getActivity().stopService(new Intent(getActivity(), BroadcastService.class));
        Log.i("On Destroy", "Stopped service");
        super.onDestroy();
    }

//    @Override
//    public void onBackPressed() {
//        Intent myIntent = new Intent(getActivity(), Home.class);
//        getActivity().startActivity(myIntent);
//    }
}
