package com.nekoder.zamuzhzanemca;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.nekoder.zamuzhzanemca.fragments.fragment_bottom.BottomNavigationViewFragment;
import com.nekoder.zamuzhzanemca.fragments.fragment_center.MenuFragment;
import com.nekoder.zamuzhzanemca.fragments.fragment_up.ToolbarFragment;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String title = getResources().getString(R.string.app_name);
        int selectedItemId = R.id.main___bottom_navigation;

        getSupportFragmentManager().beginTransaction()
                .add(R.id.container___activity_main___up, ToolbarFragment.newInstance(title, false, true))
                .add(R.id.container___activity_main___center, new MenuFragment())
                .add(R.id.container___activity_main___bottom, BottomNavigationViewFragment.newInstance(selectedItemId))
                .commit();

    }
}
