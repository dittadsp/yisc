package Fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.memberapps2.R;

public class DetailHome extends Fragment {
    WebView webView;

//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {

//        View view = inflater.inflate(R.layout.fragment_detail_home, container, false);
//         webView = (WebView) view.findViewById(R.id.webview);
//        Bundle b = getArguments();
//        String url = b.getString("url");
//        goToUrl(url);
//        return view;
//    }

    public void goToUrl(String url) {

        webView.loadUrl("http://yisc-alazhar.or.id/?p=" + url);

        // Enable Javascript
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDefaultTextEncodingName("utf-8");

        // Force links and redirects to open in the WebView instead of in a browser
        webView.setWebViewClient(new WebViewClient());
    }

}
