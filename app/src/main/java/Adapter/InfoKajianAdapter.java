package adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.memberapps2.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import model.InfoKajian;

public class InfoKajianAdapter extends ArrayAdapter<InfoKajian.Datum> {
    private Context context;
    TextView name, post_title, post_date;
    ImageView post_picture;

    public InfoKajianAdapter(Context context, ArrayList<InfoKajian.Datum> InfoKajianAdapter) {
        super(context, 0, InfoKajianAdapter);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(this.context);
            convertView = layoutInflater.inflate(R.layout.mylist, null);
        }

        InfoKajian.Datum Datum = getItem(position);
        name = (TextView) convertView.findViewById(R.id.Itemname);
        name.setText(Datum.getName());
        name.setTextColor(Color.BLACK);
        post_title = (TextView) convertView.findViewById(R.id.Desc);
        post_title.setText(Datum.getPostTitle());
        post_title.setTextColor(Color.BLACK);
        post_date = (TextView) convertView.findViewById(R.id.Bidang);
        post_date.setText(Datum.getPostDate());
        post_date.setTextColor(Color.BLACK);
        post_picture = (ImageView) convertView.findViewById(R.id.image1);
        Picasso.with(context)
                .load(Datum.getPostPicture().toString())
                .into(post_picture);

        return convertView;
    }
}
