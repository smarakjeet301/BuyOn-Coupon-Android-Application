package onlineshopping.buyon.com.buyon;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class WebActivity extends AppCompatActivity {

    public String web_add;
    WebView webView;
    boolean loadingFinished = true;
    boolean redirect = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        webView = findViewById(R.id.webview);
        final ProgressBar progressBar = findViewById(R.id.progress);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                web_add = null;
            } else {
                web_add = extras.getString("redirect_url");
            }
        } else {
            web_add = (String) savedInstanceState.getSerializable("redirect_url");
        }

        progressBar.setVisibility(View.VISIBLE);


        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String urlNewString) {
                if (!loadingFinished) {
                    redirect = true;
                }

                loadingFinished = false;
                view.loadUrl(urlNewString);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap facIcon) {
                loadingFinished = false;
                //SHOW LOADING IF IT ISNT ALREADY VISIBLE
                progressBar.setVisibility(View.VISIBLE);
                //Toast.makeText(WebActivity.this, web_add, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                if(!redirect){
                    loadingFinished = true;
                }

                if(loadingFinished && !redirect){
                    //HIDE LOADING IT HAS FINISHED
                    progressBar.setVisibility(View.INVISIBLE);
                } else{
                    redirect = false;
                }

            }
        });
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.loadUrl(web_add);



    }


}


