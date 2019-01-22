package Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.memberapps2.R;

public class Pendaftaran extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pendaftaran, container, false);
        WebView myWebView = (WebView)view.findViewById(R.id.webview);
        myWebView.loadUrl("https://ppab.yisc-alazhar.or.id/frontend/web/site/index?mobile-apps=1");
        return view;
    }
}
