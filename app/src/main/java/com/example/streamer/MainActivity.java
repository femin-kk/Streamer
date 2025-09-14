package com.example.streamer;

import android.graphics.Color;
import android.os.Bundle;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Keep fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Keep screen on while in foreground
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        webView = new WebView(this);
        webView.setBackgroundColor(Color.BLACK);
        setContentView(webView);

        webView.setWebViewClient(new WebViewClient());

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setMediaPlaybackRequiresUserGesture(false); // allow autoplay

        webView.loadUrl("http://192.168.0.103:5945");
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Destroy WebView when app goes to background
        if (webView != null) {
            webView.loadUrl("about:blank");
            webView.stopLoading();
            webView.onPause();
            webView.removeAllViews();
            webView.destroy();
            webView = null;
        }
        finish(); // close the activity
    }

    @Override
    public void onBackPressed() {
        // Back button exits immediately
        finish();
    }
}

