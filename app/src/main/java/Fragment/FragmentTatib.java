package Fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.memberapps2.R;

public class FragmentTatib extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tatib, container, false);
        String tatibDesc = "1. Mohon kerjakan soal menurut tipe soal , angkatan dasar mengerjakan soal angkatan dasar , angkatan" +
                "lanjutan mengerjakan soal angkatan lanjutan"+
                "\n"+"2. setiap soal memiliki waktu mohon mengerjakan sesuai waktu"+
                "\n"+"3. soal tercatat ketika menekan tombol submit dan keluar hasil score"+"" +
                "\n"+"4. soal hanya bisa dikerjakan 1x dan tercatat ketika menekan tombol submit";

        TextView txttatib = (TextView) view.findViewById(R.id.txttatib);
        txttatib.setText(tatibDesc);
        Button btnstart = (Button) view.findViewById(R.id.btnstart);
        btnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTatib tatib = new FragmentTatib();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.framesii, tatib);
                fragmentTransaction.commit();

            }

        });

        return view;
    }
}
