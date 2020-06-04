package com.nekoder.zamuzhzanemca.fragments.fragment_up;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.nekoder.zamuzhzanemca.MainActivity;
import com.nekoder.zamuzhzanemca.R;
import com.nekoder.zamuzhzanemca.fragments.fragment_bottom.BottomNavigationViewFragment;
import com.nekoder.zamuzhzanemca.fragments.fragment_center.OtherMenuFragment;


public class ToolbarFragment extends Fragment {
    private static final String KEY_TITLE = "title";
    private static final String KEY_BACK = "back";
    private static final String KEY_MENU = "menu";
    String title;
    boolean back;
    boolean menu;
    Toolbar toolbar;
    TextView tvTitle;
    FragmentActivity activity;

    public static ToolbarFragment newInstance(String title, boolean back, boolean menu) {
        Bundle args = new Bundle();
        args.putString(KEY_TITLE, title);
        args.putBoolean(KEY_BACK, back);
        args.putBoolean(KEY_MENU, menu);
        ToolbarFragment fragment = new ToolbarFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        activity = getActivity();
        return inflater.inflate(R.layout.fragment_toolbar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getFragmentArguments();
        initView(view);
        initToolbar();
        initToolbarNavigationListener();
    }

    public void getFragmentArguments() {
        Bundle args = getArguments();
        if (args != null) {
            title = args.getString(KEY_TITLE);
            back = args.getBoolean(KEY_BACK);
            menu = args.getBoolean(KEY_MENU);
        }
    }

    public void initView(View parent) {
        //toolbar
        toolbar = parent.findViewById(R.id.toolbar);
        //title
        tvTitle = parent.findViewById(R.id.textView___toolbar___title);
    }

    public void initToolbar() {
        toolbar.setNavigationIcon(R.drawable.back);
        ((MainActivity) activity).setSupportActionBar(toolbar);
        ((MainActivity) activity).getSupportActionBar().setDisplayShowTitleEnabled(false);
        ((MainActivity) activity).getSupportActionBar().setDisplayHomeAsUpEnabled(back);
        tvTitle.setText(title);
        setHasOptionsMenu(menu);
    }

    public void initToolbarNavigationListener() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.getSupportFragmentManager().popBackStack();
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar___menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu___toolbar:
                String title = getResources().getString(R.string.app_name);

                activity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container___activity_main___up, ToolbarFragment.newInstance(title, true, false)).addToBackStack(null)
                        .replace(R.id.container___activity_main___center, new OtherMenuFragment()).addToBackStack(null)
                        .replace(R.id.container___activity_main___bottom, BottomNavigationViewFragment.newInstance(true))
                        .commit();
                return true;
        }
        return false;
    }
}