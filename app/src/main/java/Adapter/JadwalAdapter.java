package Adapter;


import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.memberapps2.R;


import java.util.ArrayList;
import model.InfoJadwal;

public class JadwalAdapter extends ArrayAdapter<InfoJadwal> {
    private Context context;
    TextView tanggal, bulan, nama,materi,tempat;
    String date,month;
    String [] arrmonth = {"Jan","Feb","Mar","Apr","Mei","Jun","Jul","Agt","Sept","Okt","Nov","Des"};
    public JadwalAdapter(Context context, ArrayList<InfoJadwal> jadwalAdapter) {
        super(context, 0, jadwalAdapter);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(this.context);
            convertView = layoutInflater.inflate(R.layout.listjadwal, null);
        }

        InfoJadwal jadwal = getItem(position);

        nama = (TextView) convertView.findViewById(R.id.nama);
        nama.setText(jadwal.getNama());
        nama.setTextColor(Color.BLACK);
        tanggal = (TextView) convertView.findViewById(R.id.tanggal);
        date = jadwal.getTanggal().substring(9,10);
        tanggal.setText(date);
        tanggal.setTextColor(Color.BLACK);
        bulan = (TextView) convertView.findViewById(R.id.bulan);
        month = jadwal.getTanggal().substring(6,7);
        bulan.setText(arrmonth[Integer.parseInt(month)-1]);
        bulan.setTextColor(Color.BLACK);
        materi = (TextView) convertView.findViewById(R.id.materi);
        materi.setText(jadwal.getTema());
        materi.setTextColor(Color.BLACK);
        tempat = (TextView) convertView.findViewById(R.id.tempat);
        tempat.setText(jadwal.getTempat());
        tempat.setTextColor(Color.BLACK);

        return convertView;
    }
}

