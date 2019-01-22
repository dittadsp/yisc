package Fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import com.memberapps2.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import Adapter.AdapterVideo;
import model.Kajian;

import static com.android.volley.VolleyLog.TAG;

public class VideoKajian extends Fragment {
    ProgressDialog pDialog;
    public static final String DEVELOPER_KEY = "AIzaSyDXsnjNJO8o-zOdT_uDK1kXpx7s4dcbbHc";
    ListView listView;
    private YouTubePlayerView playerView;
    private YouTubePlayer player;
    ArrayList<Kajian> videoDetail = new ArrayList<>();;
    AdapterVideo adapterVideo;
    private FragmentActivity myContext;
    String URL="https://www.googleapis.com/youtube/v3/search?part=snippet&channelId=UCLGTGGY_KFCAtb11zhy6xHA&key=AIzaSyD9-nbKBoUys23cVeHpZFUd4Yj8di_a09A";
    @Override
    public void onAttach(Activity activity) {

        if (activity instanceof FragmentActivity) {
            myContext = (FragmentActivity) activity;
        }

        super.onAttach(activity);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video_kajian, container, false);

        listView = (ListView)view.findViewById(R.id.videoList);

        adapterVideo = new AdapterVideo(getActivity().getApplicationContext(), videoDetail);
        listView.setAdapter(adapterVideo);

        showVideo();

//        playerView = (YouTubePlayerView)view.findViewById(R.id.player);
//        YouTubePlayerSupportFragment youTubePlayerFragment = YouTubePlayerSupportFragment.newInstance();
//        android.support.v4.app.FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
//        transaction.add(R.id.playerVideo, youTubePlayerFragment).commit();
//
//        youTubePlayerFragment.initialize("AIzaSyD9-nbKBoUys23cVeHpZFUd4Yj8di_a09A", new YouTubePlayer.OnInitializedListener() {
//
//            @Override
//            public void onInitializationSuccess(YouTubePlayer.Provider arg0, YouTubePlayer youTubePlayer, boolean b) {
//                if (!b) {
//                    player = youTubePlayer;
////                    player.setFullscreen(true);
//                    player.loadVideo("1pnBat4vmJs");
//                    player.play();
//                }
//            }
//
//            @Override
//            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
//
//            }
//
//        });

        Log.i("masuk", "masuk video");

//        VideoKajian("snippet", "UCLGTGGY_KFCAtb11zhy6xHA", "AIzaSyD9-nbKBoUys23cVeHpZFUd4Yj8di_a09A");
        return view;
    }


    private void showVideo() {
        RequestQueue requestQueue= Volley.newRequestQueue(getContext());

        StringRequest stringRequest=new StringRequest(Request.Method.GET, URL, new com.android.volley. Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray jsonArray=jsonObject.getJSONArray("items");
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        JSONObject jsonVideoId=jsonObject1.getJSONObject("id");
                        JSONObject jsonsnippet= jsonObject1.getJSONObject("snippet");
                        JSONObject jsonObjectdefault = jsonsnippet.getJSONObject("thumbnails").getJSONObject("medium");
                        Kajian videoDetails=new Kajian();

                        String videoid=jsonVideoId.getString("videoId");

                        Log.e(TAG," New Video Id" +videoid);
                        videoDetails.setURL(jsonObjectdefault.getString("url"));
                        videoDetails.setVideoName(jsonsnippet.getString("title"));
                        videoDetails.setVideoDesc(jsonsnippet.getString("description"));
                        videoDetails.setVideoId(videoid);

                        videoDetail.add(videoDetails);
                    }
                    listView.setAdapter(adapterVideo);
                    adapterVideo.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new com.android.volley. Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        int socketTimeout = 30000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        requestQueue.add(stringRequest);

    }

}
