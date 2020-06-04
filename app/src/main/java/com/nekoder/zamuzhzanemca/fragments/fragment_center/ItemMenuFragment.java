package com.nekoder.zamuzhzanemca.fragments.fragment_center;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.nekoder.zamuzhzanemca.R;
import com.nekoder.zamuzhzanemca.support.ItemWebViewClient;


public class ItemMenuFragment extends Fragment implements View.OnKeyListener {
    private static final String KEY_URL = "url";
    String url;
    static WebView webView;
    FragmentActivity activity;

    public static ItemMenuFragment newInstance(String url, boolean isCurrentCenterItemMenuFragment) {
        Bundle args = new Bundle();
        ItemMenuFragment fragment = new ItemMenuFragment();
        args.putString(KEY_URL, url);
        fragment.setArguments(args);
        if (isCurrentCenterItemMenuFragment) {
            webView.loadUrl(url);
        }
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        activity = getActivity();
        return inflater.inflate(R.layout.fragment_menu_item, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);
        getFragmentArguments();
        onCreateWebView();
    }

    public void initView(View parent) {
        webView = parent.findViewById(R.id.webView___fragment_menu_item___main);
    }

    public void getFragmentArguments() {
        Bundle args = getArguments();
        if (args != null) {
            url = args.getString(KEY_URL);
        }
    }

    /**
     * WebView
     */
    public void onCreateWebView() {
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new ItemWebViewClient());
        webView.loadUrl(url);
        webView.setOnKeyListener(this);
    }

    /**
     * onKey
     */
    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP && webView.canGoBack()) { //событие НАЗАД [down/up] | отпущена ли кнопка [up] | есть ли история страниц.
            webView.goBack();
            return true;
        }
        return false;
    }
}
