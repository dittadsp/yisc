package Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.memberapps2.R;

import java.util.ArrayList;

import model.InfoJadwal;
import model.InfoQuiz;

public class QuizAdapter extends ArrayAdapter<InfoQuiz> {
    private Context context;
    TextView quiz_title, quiz_desc, quiz_start_date, quiz_start_time, quiz_end_date, quiz_end_time,judulstartdate,judulstarttime,judulendate,judulendtime;
    String start_date, start_time, end_date, end_time;
    public QuizAdapter(Context context, ArrayList<InfoQuiz> quizAdapter) {
        super(context, 0, quizAdapter);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(this.context);
            convertView = layoutInflater.inflate(R.layout.quizlist, null);
        }

        InfoQuiz quiz = getItem(position);
        quiz_title = (TextView) convertView.findViewById(R.id.txttitle);
        quiz_title.setText(quiz.getQuiz_title());
        quiz_title.setTextColor(Color.BLACK);

        quiz_desc = (TextView) convertView.findViewById(R.id.txtdesc);
        quiz_desc.setText(quiz.getQuiz_desc());
        quiz_desc.setTextColor(Color.BLACK);

        judulstartdate = (TextView) convertView.findViewById(R.id.judulstartdate);
        judulstartdate.setText("Start Date");
        judulstartdate.setTextColor(Color.BLACK);
        quiz_start_date = (TextView) convertView.findViewById(R.id.txtstartdate);
        start_date = quiz.getQuiz_start_date().substring(0,10);
        quiz_start_date.setText(start_date);
        quiz_start_date.setTextColor(Color.BLACK);


        quiz_start_time = (TextView) convertView.findViewById(R.id.txtstarttime);
        start_time = quiz.getQuiz_start_date().substring(11,16);
        quiz_start_time.setText(start_time);
        quiz_start_time.setTextColor(Color.BLACK);

        judulendate    = (TextView) convertView.findViewById(R.id.judulenddate);
        judulendate.setTextColor(Color.BLACK);
        judulendate.setText("End Date");
        quiz_end_date = (TextView) convertView.findViewById(R.id.txtenddate);
        end_date = quiz.getQuiz_end_date().substring(0,10);
        quiz_end_date.setText(end_date);
        quiz_end_date.setTextColor(Color.BLACK);

        quiz_end_time = (TextView) convertView.findViewById(R.id.txtendtime);
        end_time = quiz.getQuiz_end_date().substring(11,16);
        quiz_end_time.setText(end_time);
        quiz_end_time.setTextColor(Color.BLACK);
        return convertView;
    }
}