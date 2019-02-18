package Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.memberapps2.Home;
import com.memberapps2.R;

import Adapter.InfoAdapter;


public class Sii extends Fragment {
    ListView lv;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        String[][] title = new String[][]{
                {"Jadwal Pendidikan", "", "", "ic_menu_home"},
                {"Materi", "", "", "ic_menu_home"},
                {"Quiz", "", "", "ic_menu_home"},
                {"Nilai", "", "", "ic_menu_home"},
        };

        final String tatibDesc = "1. Mohon kerjakan soal menurut tipe soal , angkatan dasar mengerjakan soal angkatan dasar , angkatan " +
                "lanjutan mengerjakan soal angkatan lanjutan"+
                "\n"+"2. DILARANG keras menyontek dalam bentuk apapun"+
                "\n"+"3. soal tercatat ketika menekan tombol submit dan keluar hasil score"+"" +
                "\n"+"4. soal hanya bisa dikerjakan 1x dan tercatat ketika menekan tombol submit"+
                "\n"+"5 Kerjakan dengan jujur dan berdo'a terlebih dahulu sebelum mengerjakan"+"" +
                "\n"+"6 setiap soal memiliki waktu mohon mengerjakan sesuai waktu"+
                "\n"+"7. Good Luck";
        View view = inflater.inflate(R.layout.fragment_sii, container, false);
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
        lv = (ListView) view.findViewById(R.id.listViewsii);
        InfoAdapter adapter = new InfoAdapter(this.getActivity(), title);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    Jadwal jadwal = new Jadwal();
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.framesii, jadwal);
                    fragmentTransaction.commit();
                } else if (i == 1) {
                    Materi materi = new Materi();
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.framesii, materi);
                    fragmentTransaction.commit();
                } else if (i == 2) {
                    showDialog("Warning",tatibDesc);
                } else if (i == 3) {
                    Nilai nilai = new Nilai();
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.framesii, nilai);
                    fragmentTransaction.commit();
                }
            }

        });
        return view;
    }

    private void showDialog(String message,String content){
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(getContext(), android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(getContext());
        }
        builder.setTitle(message)
                .setMessage(content)
                .setPositiveButton("Bismillah", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        QuizList quiz = new QuizList();
                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.framesii, quiz);
                        fragmentTransaction.commit();

                    }
                })
                .setNegativeButton("ga deh nanti aja", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {


                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
