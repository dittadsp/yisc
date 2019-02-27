package Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.memberapps2.R;

import java.util.ArrayList;

import Fragment.Materi;
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
        final MateriList.DatumMateri Datum = getItem(position);
        judul = (TextView) convertView.findViewById(R.id.txtjudul);
        judul.setText(Datum.getTema());
        judul.setTextColor(Color.BLACK);

        resume = (TextView) convertView.findViewById(R.id.txtlihat);
        if (Datum.getResume().equals("kosong")) {
            resume.setText("Tidak ada resume");
        } else {

            resume.setText("Lihat Resume");
            resume.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Materi materi = new Materi();
//                    materi.showDialog(Datum.getTema(), Datum.getResume());
                }
            });
        }
        video = (TextView) convertView.findViewById(R.id.txtvideo);
        if (Datum.getVideo().equals("kosong")) {
            video.setText("Tidak ada video");
        } else {
            video.setText("Download Video");
            video.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Uri uri = Uri.parse(Datum.getVideo().toString());
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        }

        file = (TextView) convertView.findViewById(R.id.txtdownload);
        if (Datum.getFile().equals("kosong")) {
            file.setText("Tidak ada materi");
        } else {
            file.setText("Download Materi");
            file.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Uri uri = Uri.parse(Datum.getFile().toString());
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        }
        return convertView;
    }
}
