package Adapter;


import android.content.Context;
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

//        Log.d("JADWAl",jadwal.getNama());
//        nama = (TextView) convertView.findViewById(R.id.nama);
//        nama.setText(jadwal.getNama());
        tanggal = (TextView) convertView.findViewById(R.id.tanggal);
        date = jadwal.getTanggal().substring(7,8);
        tanggal.setText((CharSequence) jadwal.getTanggal());
        bulan = (TextView) convertView.findViewById(R.id.bulan);
        month = jadwal.getTanggal().substring(4,6);
        bulan.setText(jadwal.getTanggal());
        materi = (TextView) convertView.findViewById(R.id.materi);
        materi.setText(jadwal.getTema());
        tempat = (TextView) convertView.findViewById(R.id.tempat);
        tempat.setText(jadwal.getTempat());

        return convertView;
    }
}

