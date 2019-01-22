package adapter;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.memberapps2.R;

public class AbsensiAdapter extends BaseAdapter {
    TextView text1, text2, text3;
    ImageView imgView;
    String[][] data;
    Activity activity;

    public AbsensiAdapter(Activity activity, String[][] data) {
        super();
        this.data = data;
        this.activity = activity;
    }
    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public Object getItem(int position) {
        return data[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(activity);
            v = vi.inflate(R.layout.mylistabsensi, null);
        }

        Object p = getItem(position);

        if (p != null) {
            text1 = (TextView) v.findViewById(R.id.Itemname3);
            text2 = (TextView) v.findViewById(R.id.Desc3);
            text3 = (TextView) v.findViewById(R.id.Time);
            imgView = (ImageView) v.findViewById(R.id.image3);

            int id = activity.getResources().getIdentifier(data[position][3], "drawable", activity.getPackageName());
            Drawable drawable = activity.getResources().getDrawable(id);

            text1.setText(data[position][0]);
            text2.setText(data[position][1]);
            text3.setText(data[position][2]);
            imgView.setImageDrawable(drawable);
        }

        return v;
    }
}
