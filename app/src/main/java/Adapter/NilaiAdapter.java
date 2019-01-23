package adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.memberapps2.R;

import java.util.ArrayList;

import model.NilaiList;

public class NilaiAdapter extends ArrayAdapter<NilaiList.Datum> {
    TextView tema, score, updateat;
    private Context context;

    public NilaiAdapter(Context context, ArrayList<NilaiList.Datum> nilaiAdapter) {
        super(context, 0, nilaiAdapter);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(this.context);
            convertView = layoutInflater.inflate(R.layout.mylistnilai, null);
        }
        NilaiList.Datum Datum = getItem(position);
        tema = (TextView) convertView.findViewById(R.id.txttema);
        tema.setText(Datum.getParticipantQuiz());
        tema.setTextColor(Color.RED);
        score = (TextView) convertView.findViewById(R.id.txtscore);
        score.setText(Datum.getParticipantScore());
        score.setTextColor(Color.RED);
        updateat = (TextView) convertView.findViewById(R.id.txtupdateat);
        updateat.setText(Datum.getUpdatedAt());
        updateat.setTextColor(Color.RED);

        return convertView;
    }
}
