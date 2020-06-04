package com.nekoder.zamuzhzanemca.support;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.RequiresApi;

public class ItemWebViewClient extends WebViewClient {
    Intent intent;

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if (url.contains("zamuzh-za-nemca")) {//это страница нашего сайта?
            return false;//не использовать браузер, а использовать приложение.
        }
        intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        view.getContext().startActivity(intent);
        return true;//использовать браузер
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        if (request.getUrl().toString().contains("zamuzh-za-nemca")) {
            return false;
        }
        intent = new Intent(Intent.ACTION_VIEW, Uri.parse(request.getUrl().toString()));
        view.getContext().startActivity(intent);
        return true;
    }
}
