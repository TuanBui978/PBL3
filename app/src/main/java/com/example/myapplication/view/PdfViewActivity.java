package com.example.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleCoroutineScope;
import androidx.lifecycle.LifecycleOwnerKt;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebViewClient;

import com.example.myapplication.R;
import com.example.myapplication.controller.ApiService;
import com.example.myapplication.databinding.ActivityPdfViewBinding;

public class PdfViewActivity extends AppCompatActivity {
    public static String STR_BUNDLE = "url";
    String url;
    ActivityPdfViewBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (getIntent().getStringExtra(STR_BUNDLE) != null) {
            url = getIntent().getStringExtra(STR_BUNDLE);
        }
        super.onCreate(savedInstanceState);
        binding = ActivityPdfViewBinding.inflate(getLayoutInflater());
//        binding.pdfView.getSettings().setJavaScriptEnabled(true);
//        binding.pdfView.setWebViewClient(new WebViewClient());
//        binding.pdfView.loadUrl(url);



        setContentView(binding.getRoot());
    }
}