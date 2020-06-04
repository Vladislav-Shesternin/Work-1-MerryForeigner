package com.nekoder.zamuzhzanemca.fragments.fragment_bottom;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.nekoder.zamuzhzanemca.R;
import com.nekoder.zamuzhzanemca.fragments.fragment_center.ItemMenuFragment;
import com.nekoder.zamuzhzanemca.fragments.fragment_up.ToolbarFragment;


public class BottomNavigationViewFragment extends Fragment {
    private static final String KEY_SELECTED_ITEM_ID = "selected_item_id";
    private static final String KEY_MAIN_DISCONNECT = "main_disconnect";
    BottomNavigationView bottomNavigationView;
    FragmentActivity activity;
    String url, title;
    int selectedItemId;
    boolean mainDisconnect;

    public static BottomNavigationViewFragment newInstance(int selectedItemId) {
        Bundle args = new Bundle();
        args.putInt(KEY_SELECTED_ITEM_ID, selectedItemId);
        BottomNavigationViewFragment fragment = new BottomNavigationViewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static BottomNavigationViewFragment newInstance(boolean mainDisconnect) {
        Bundle args = new Bundle();
        args.putBoolean(KEY_MAIN_DISCONNECT, mainDisconnect);
        BottomNavigationViewFragment fragment = new BottomNavigationViewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        activity = getActivity();
        return inflater.inflate(R.layout.fragment_bottom_navigation_view, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getFragmentArguments();
        initView(view);
        initBottomNavigationView();
        initBottomNavigationViewListener();
    }

    public void getFragmentArguments() {
        Bundle args = getArguments();
        if (args != null) {
            selectedItemId = args.getInt(KEY_SELECTED_ITEM_ID);
            mainDisconnect = args.getBoolean(KEY_MAIN_DISCONNECT);
        }
    }

    public void initView(View parent) {
        bottomNavigationView = parent.findViewById(R.id.bottomNavigationView);
    }

    public void initBottomNavigationView() {
        if (mainDisconnect) {
            bottomNavigationView.getMenu().getItem(0).setCheckable(false);//невыбирать ниодного элемента
        } else {
            bottomNavigationView.setSelectedItemId(selectedItemId);//установить выбраный элемент
        }
    }

    public void initBottomNavigationViewListener() {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                final int main = R.id.main___bottom_navigation;
                final int youtube = R.id.youtube___bottom_navigation;
                final int site = R.id.site___bottom_navigation;
                final int email = R.id.email___bottom_navigation;

                switch (item.getItemId()) {
                    case main:
                        if (selectedItemId != main) {
                            activity.getSupportFragmentManager().popBackStack(activity.getSupportFragmentManager().getBackStackEntryAt(0).getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
                        }
                        return false;

                    case youtube:
                        url = "https://www.youtube.com/channel/UCRJvzyH0cOVek--wL70uEWg?sub_confirmation=1";
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        activity.startActivity(intent);
                        return false;

                    case site:
                        if (selectedItemId != site) {
                            title = activity.getResources().getString(R.string.site);
                            url = "http://zamuzh-za-nemca.ru";
                            selectedItemId = site;

                            break; //Выход со switch
                        }
                        return false;//Выход со onNavigationItemSelected

                    case email:
                        if (selectedItemId != email) {
                            title = activity.getResources().getString(R.string.email);
                            url = "http://zamuzh-za-nemca.ru/contacts/#feedback";
                            selectedItemId = email;

                            break;
                        }
                        return false;
                }
                FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
                transaction
                        .replace(R.id.container___activity_main___up, ToolbarFragment.newInstance(title, true, false)).addToBackStack(null)
                        .replace(R.id.container___activity_main___bottom, BottomNavigationViewFragment.newInstance(selectedItemId)).addToBackStack(null);

                loadOrReplace(transaction);
                transaction.commit();
                return false;
            }
        });
    }

    public void loadOrReplace(FragmentTransaction transaction) {
        Fragment currentFragment = activity.getSupportFragmentManager().findFragmentById(R.id.container___activity_main___center);
        if (currentFragment instanceof ItemMenuFragment) {
            ItemMenuFragment.newInstance(url, true);
        } else {
            transaction.replace(R.id.container___activity_main___center, ItemMenuFragment.newInstance(url, false)).addToBackStack(null);
        }
    }
}