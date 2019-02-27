package Adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.memberapps2.R;

import java.util.ArrayList;

import model.MateriList;

public class MateriAdapter extends ArrayAdapter<MateriList.DatumMateri> {
    private Context context;
    TextView judul, file, video, resume;

    public MateriAdapter(Context context, ArrayList<MateriList.DatumMateri> materiAdapter) {
        super(context, 0, materiAdapter);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(this.context);
            convertView = layoutInflater.inflate(R.layout.listmateri, null);
        }
        MateriList.DatumMateri Datum = getItem(position);
        judul = (TextView) convertView.findViewById(R.id.txtjudul);
        judul.setText(Datum.getTema());
        judul.setTextColor(Color.BLACK);

        resume = (TextView) convertView.findViewById(R.id.txtlihat);
        resume.setMovementMethod(LinkMovementMethod.getInstance());
        String linkresume = "<a href = '" + Datum.getResume() + "'>Lihat Resume</a>";
        resume.setText(Html.fromHtml(linkresume));

        video = (TextView) convertView.findViewById(R.id.txtvideo);
        video.setMovementMethod(LinkMovementMethod.getInstance());
        String linkvideo = "<a href = '" + Datum.getVideo() + "'>Video</a>";
        video.setText(Html.fromHtml(linkvideo));

        file = (TextView) convertView.findViewById(R.id.txtdownload);
        file.setMovementMethod(LinkMovementMethod.getInstance());
        String linkfile = "<a href = '" + Datum.getFile() + "'>Materi File</a>";
        file.setText(Html.fromHtml(linkfile));
        return convertView;
    }
}
