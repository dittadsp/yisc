package Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import com.memberapps2.R;

import java.util.ArrayList;

import model.OptionList;
import model.QuestionsList;

public class QuestionAdapter extends ArrayAdapter<QuestionsList> {
    private Context context;
    TextView quiz_title, quiz_desc, quiz_time, question_text, question_id;
    RadioButton questiondata;

    public QuestionAdapter(Context context, ArrayList<QuestionsList> materiAdapter) {
        super(context, 0, materiAdapter);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(this.context);
            convertView = layoutInflater.inflate(R.layout.mylistquestion, null);
        }
        QuestionsList data = getItem(position);
        quiz_title = (TextView) convertView.findViewById(R.id.txttitle);
        quiz_title.setText(data.getQuiz_title());
        quiz_desc = (TextView) convertView.findViewById(R.id.txtdesc);
        quiz_desc.setText(data.getQuiz_desc());
        quiz_time = (TextView) convertView.findViewById(R.id.txtquiztime);
        quiz_time.setText(data.getQuiz_time());
        question_text = (TextView) convertView.findViewById(R.id.question_text);
        question_text.setText(data.getQuestion_text());
        question_id = (TextView) convertView.findViewById(R.id.question_id);
        question_id.setText(data.getQuestion_text());

        return convertView;
    }
}
