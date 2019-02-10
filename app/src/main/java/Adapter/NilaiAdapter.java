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

import model.NilaiList;
import model.NilaiModel;

public class NilaiAdapter extends ArrayAdapter<NilaiModel> {
    TextView tema, score, updateat;
    private Context context;

    public NilaiAdapter(Context context, ArrayList<NilaiModel> nilaiAdapter) {
        super(context, 0, nilaiAdapter);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(this.context);
            convertView = layoutInflater.inflate(R.layout.mylistnilai, null);
        }
        NilaiModel nilaiModel = getItem(position);
        tema = (TextView) convertView.findViewById(R.id.txttema);
        tema.setText(nilaiModel.getParticipant_quiz());
        tema.setTextColor(Color.RED);
        score = (TextView) convertView.findViewById(R.id.txtscore);
        score.setText(nilaiModel.getParticipant_score());
        score.setTextColor(Color.RED);

        return convertView;
    }
}
