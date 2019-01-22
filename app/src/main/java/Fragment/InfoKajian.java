package Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.memberapps2.R;

import java.util.ArrayList;
import java.util.List;

import connection.Endpoint;
import helper.RetroClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfoKajian extends Fragment {
    ListView lv;

    ProgressDialog pDialog;
    final ArrayList<model.InfoKajian.Datum> infokajian = new ArrayList<>();
    adapter.InfoKajianAdapter infoKajianAdapter;
    List<model.InfoKajian.Datum> listdatum;
    List<model.InfoKajian.PostCat> categoryList;
    String id, post_title, post_date, post_url, post_picture, term_id, name, total_pages;
    private int load = 1;
    private Context context;
    private boolean loadMore = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info_kajian, container, false);
        lv = (ListView) view.findViewById(R.id.listViewinfokajian);
        infoKajianAdapter = new adapter.InfoKajianAdapter(getActivity().getApplicationContext(), infokajian);
        lv.setAdapter(infoKajianAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Uri uri = Uri.parse(listdatum.get(i).getPostUrl());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }

        });
        lv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                int lastInScreen = firstVisibleItem + visibleItemCount;
                if ((lastInScreen == totalItemCount - 1) && load > 1) {
                    loadMore = true;
                    loadData();
                }
            }
        });
        loadData();
        return view;
    }

    private void loadData() {
        Log.i("masuk", "loadData");
        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading Data.. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(true);
        if (load == 1) {
            pDialog.show();
            infokajian(Integer.toString(load));
        } else if (load <= Integer.parseInt(total_pages)) {
            infokajian(Integer.toString(load));
        }
    }

    private void infokajian(String page) {
        Log.i("masuk page ", page);
        RetroClient.getClient().create(Endpoint.class).responseInfoKajian(page, "kajian").enqueue(new Callback<model.InfoKajian>() {
            @Override
            public void onResponse(Call<model.InfoKajian> call, Response<model.InfoKajian> response) {
                if (response.isSuccessful()) {
                    load += 1;
                    pDialog.dismiss();
                    Gson gson = new Gson();
                    String j = gson.toJson(response.body());
                    Log.i("responseinfokajian", j);
                    Log.i("responseinfokajian2", response.raw().request().url().toString());

                    total_pages = response.body().getMaxPages().toString();

                    listdatum = response.body().getData();
                    for (int i = 0; i < listdatum.size(); i++) {
                        id = listdatum.get(i).getID();
                        post_title = listdatum.get(i).getPostTitle();
                        post_date = listdatum.get(i).getPostDate();
                        post_url = listdatum.get(i).getPostUrl();

                        post_picture = (String) listdatum.get(i).getPostPicture();
                        if (post_picture == null) {
                            post_picture = (String) listdatum.get(1).getPostPicture();
                        } else {
                            post_picture = (String) listdatum.get(i).getPostPicture();
                        }
                        categoryList = listdatum.get(i).getPostCats();
                        for (int k = i; k < categoryList.size(); k++) {
                            term_id = categoryList.get(k).getTermId();
                            name = categoryList.get(k).getName();
                        }
                        infokajian.add(new model.InfoKajian.Datum(name, post_title, post_date, post_url, post_picture));
                    }
                    infoKajianAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<model.InfoKajian> call, Throwable throwable) {

            }
        });
    }
}
