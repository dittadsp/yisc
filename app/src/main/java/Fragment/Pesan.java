package Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.memberapps2.R;


public class Pesan extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pendaftaran, container, false);
        WebView myWebView = (WebView)view.findViewById(R.id.webview);
        myWebView.loadUrl("https://www.yisc-alazhar.or.id/#contact-us");
        return view;
    }
}
