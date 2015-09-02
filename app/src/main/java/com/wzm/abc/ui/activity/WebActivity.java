package com.wzm.abc.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

import com.wzm.abc.R;

/**
 * Created by zhiyou007 on 2015/9/1.
 */
public class WebActivity extends Activity {
    private WebView wb = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        wb = (WebView)findViewById(R.id.web);
        //wb.loadUrl("http://www.baiducom");
        wb.loadUrl(getIntent().getStringExtra("BUNDLE_KEY_URL"));
    }
}
