package Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

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
        View view = inflater.inflate(R.layout.fragment_sii, container, false);
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
                    QuizList quiz = new QuizList();
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.framesii, quiz);
                    fragmentTransaction.commit();
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
}
