package Fragment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.memberapps2.Home;
import com.memberapps2.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import Adapter.QuizAdapter;
import connection.Endpoint;
//import entity.IOnBackPressed;
import helper.RetroClient;
import model.InfoListQuiz;
import model.InfoQuiz;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class QuizList extends Fragment implements View.OnKeyListener {
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
    String quiz_id, quiz_title, quiz_desc,  quiz_start_date, quiz_end_date,quiz_status,id_quiz;
    FragmentManager fmgr ;
    int outdated =0 ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz, container, false);
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    Intent myIntent = new Intent(getActivity(), Home.class);
                    getActivity().startActivity(myIntent);
                    return true;
                }
                return false;
            }
        });
        lv = (ListView) view.findViewById(R.id.listViewQuiz);

        view.setOnKeyListener(this);
        quizAdapter = new QuizAdapter(getActivity().getApplicationContext(), quizArrayList);
        lv.setAdapter(quizAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                getOutDated();
                if(getOutDated()==1){
                    showDialogFailed("Sorry","Quiz has already been expired");
                }
                else{
//                    QuestionFragment question = new QuestionFragment();
//                    FragmentManager fragmentManager = getFragmentManager();
//                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                    Bundle bundle = new Bundle();
//                    bundle.putString("quiz_id", ""+ listQuiz.get(i).getQuiz_id());
//                    question.setArguments(bundle);
//                    fragmentTransaction.replace(R.id.framesii, question);
//                    fragmentTransaction.commit();
                }
            }

        });
        loadData();
        return view;
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

    private void loadData() {
        Log.i("masuk", "loadData");
        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading Data.. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(true);
        pDialog.show();
        SharedPreferences sharedPref = getActivity().getSharedPreferences("data",MODE_PRIVATE);
        String id = sharedPref.getString("id", "");
        schedule(KEY_ANDROID,id);

    }

    private void schedule(String key,  String member_id) {

        RequestBody u_key = RequestBody.create(MediaType.parse("text/plain"), key);
        RequestBody u_id = RequestBody.create(MediaType.parse("text/plain"), member_id);

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

    private int getOutDated(){
         outdated = 0;
        Calendar cal;
         String start ="",end="";
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        for(int i =0; i<listQuiz.size();i++){
             start = listQuiz.get(i).getQuiz_start_date();
             end = listQuiz.get(i).getQuiz_end_date();

            Date strDate = null;
            try {
                strDate = dateFormat.parse(end);
                if (System.currentTimeMillis() > strDate.getTime()) {
                    outdated = 1;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
        return outdated;
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_UP) {
            if (keyCode == KeyEvent.KEYCODE_BACK) {

                return true;
            }
        }

        return false;
    }
}