package Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.memberapps2.AppController;
import com.memberapps2.R;
import com.memberapps2.VideoKajianActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import model.Kajian;


public class AdapterVideo extends ArrayAdapter<Kajian> {
    private Context context;
    ImageView post_picture;
    private YouTubePlayerView playerView;
    private YouTubePlayer player;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    public AdapterVideo(Context context, ArrayList<Kajian> adapterVideo) {
        super(context, 0, adapterVideo);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(this.context);
            convertView = layoutInflater.inflate(R.layout.listvideo, null);
        }
        Kajian kajian = getItem(position);
        NetworkImageView networkImageView = (NetworkImageView) convertView.findViewById(R.id.video_image);
        final TextView name = (TextView) convertView.findViewById(R.id.Itemname);
        name.setText(kajian.getVideoName());
        name.setTextColor(Color.BLACK);
        final TextView post_title = (TextView) convertView.findViewById(R.id.Desc);
        post_title.setText(kajian.getVideoDesc());
        post_title.setTextColor(Color.BLACK);
        final TextView id = (TextView) convertView.findViewById(R.id.tv_videoId) ;
        id.setText(kajian.getVideoId());
        Log.d("URL Kajian",kajian.getURL());
        networkImageView.setImageUrl(kajian.getURL(), this.imageLoader);
        ((LinearLayout) convertView.findViewById(R.id.asser)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("ID VIDEO",id.getText().toString());
                Intent intent=new Intent(view.getContext(), VideoKajianActivity.class);
                intent.putExtra("videoId",id.getText().toString());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                view.getContext().startActivity(intent);

            }
        });


        return convertView;
    }
}
