package android.example.navigationdrawer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class feedback extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feedback, container, false);
        WebView webView =  view.findViewById(R.id.feedback_webView);
        webView.getSettings().setJavaScriptEnabled(true);     // Enable Javascript
        webView.setWebViewClient(new WebViewClient());     // WebViewClient object to open url
        webView.loadUrl("https://docs.google.com/forms/d/e/1FAIpQLSdYjdWWKIaiR4lw9S8VbeSA9eoYStW8mczteAvZWYUKI6nP7A/viewform");
       return view;
    }
}