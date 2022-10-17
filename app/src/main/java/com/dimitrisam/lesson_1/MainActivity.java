package com.dimitrisam.lesson_1;

import static com.dimitrisam.lesson_1.R.layout.activity_main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.security.KeyException;

public class MainActivity extends AppCompatActivity {
    public Spinner kommunalTypeDropDown;
    public EditText kommunalValuesInput;
    public Button getCostBtn;
    public boolean getCostBtnFlag = false;
    public Button startPayingBtn;
    public ProgressBar progressBar;
    public TextView resultTitle;
    public WebView resultDescWebView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_main);
        kommunalTypeDropDown = findViewById(R.id.kommunal_type_drop_down);
        kommunalValuesInput = findViewById(R.id.kommunal_values_input);
        getCostBtn = findViewById(R.id.get_cost_btn);
        startPayingBtn = findViewById(R.id.start_paying_btn);
        progressBar = findViewById(R.id.progressBar);
        resultDescWebView = findViewById(R.id.result_desc);
        resultDescWebView.setWebViewClient(new MyWebViewClient());
        resultDescWebView.setWebChromeClient(new WebChromeClient());
        resultTitle = findViewById(R.id.result_title);

    }

    public void onClickGetCostBtn(View view) {
        if (!getCostBtnFlag) { //Слушатель кнопки
            getCostBtnFlag = true;
            getCostBtn.setText("Остановить");

            getWebClickAndResultsParsing();
        }
    }


    @Override
    protected void onDestroy() { //Юзер смахивает (закрывает) приложение.
        super.onDestroy();
        getCostBtnFlag = false;
    }

    public void getWebClickAndResultsParsing() {
        if (getCostBtnFlag) {
            getCostBtnFlag = false;
            getCostBtn.setText("Узнать стоимость");
            resultDescWebView.getSettings().setJavaScriptEnabled(true);
            resultDescWebView.loadUrl("https://google.com");
            resultTitle.setText("Открыт сайт...");

            new Thread(new Runnable() {

                @Override
                public void run() {
                    System.out.println("hohoho");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("hohoho");
                        }
                    });
                }
            }).start();
        }
    }


    private static class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(request.getUrl().toString());
            return true;
        }
    }
}