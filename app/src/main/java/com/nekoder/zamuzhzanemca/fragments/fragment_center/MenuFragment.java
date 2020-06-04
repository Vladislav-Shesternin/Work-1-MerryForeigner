package com.nekoder.zamuzhzanemca.fragments.fragment_center;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.nekoder.zamuzhzanemca.R;
import com.nekoder.zamuzhzanemca.fragments.fragment_bottom.BottomNavigationViewFragment;
import com.nekoder.zamuzhzanemca.fragments.fragment_up.ToolbarFragment;

public class MenuFragment extends Fragment implements View.OnClickListener {
    FragmentActivity activity;
    ImageView aboutAuthor, datingProject, coaching, VIP, score, webinar, blog, free, consultation;
    String url, title;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        activity = getActivity();
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initListener();
    }

    public void initView(View parent) {
        //menu
        aboutAuthor = parent.findViewById(R.id.imageView___fragment_menu___aboutAuthor);
        datingProject = parent.findViewById(R.id.imageView___fragment_menu___datingProject);
        coaching = parent.findViewById(R.id.imageView___fragment_menu___coaching);
        VIP = parent.findViewById(R.id.imageView___fragment_menu___VIP);
        score = parent.findViewById(R.id.imageView___fragment_menu___score);
        webinar = parent.findViewById(R.id.imageView___fragment_menu___webinar);
        blog = parent.findViewById(R.id.imageView___fragment_menu___blog);
        free = parent.findViewById(R.id.imageView___fragment_menu___free);
        consultation = parent.findViewById(R.id.imageView___fragment_menu___consultation);
    }

    public void initListener() {
        aboutAuthor.setOnClickListener(this);
        datingProject.setOnClickListener(this);
        coaching.setOnClickListener(this);
        VIP.setOnClickListener(this);
        score.setOnClickListener(this);
        webinar.setOnClickListener(this);
        blog.setOnClickListener(this);
        free.setOnClickListener(this);
        consultation.setOnClickListener(this);
    }

    /**
     * onClick
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageView___fragment_menu___aboutAuthor:
                url = "http://zamuzh-za-nemca.ru/ob_avtore/";
                title = getResources().getString(R.string.aboutAuthor);
                break;
            case R.id.imageView___fragment_menu___datingProject:
                url = "http://zamuzh-za-nemca.ru/prezentaciya-kursa-kak-vyjti-zamuzh-za-nemca/";
                title = getResources().getString(R.string.datingProjectTitle);
                break;
            case R.id.imageView___fragment_menu___coaching:
                url = "http://zamuzh-za-nemca.ru/kouching/";
                title = getResources().getString(R.string.coaching);
                break;
            case R.id.imageView___fragment_menu___VIP:
                url = "http://zamuzh-za-nemca.ru/zamuzh-za-inostranza/";
                title = getResources().getString(R.string.VIP);
                break;
            case R.id.imageView___fragment_menu___score:
                url = "http://zamuzh-za-nemca.ru/magazin/";
                title = getResources().getString(R.string.score);
                break;
            case R.id.imageView___fragment_menu___webinar:
                url = "http://zamuzh-za-nemca.ru/spisok-vebinarov-zamuzh-za-inostranza/";
                title = getResources().getString(R.string.webinar);
                break;
            case R.id.imageView___fragment_menu___blog:
                url = "http://zamuzh-za-nemca.ru/blog/";
                title = getResources().getString(R.string.blog);
                break;
            case R.id.imageView___fragment_menu___free:
                url = "http://zamuzh-za-nemca.ru/besplatno/";
                title = getResources().getString(R.string.free);
                break;
            case R.id.imageView___fragment_menu___consultation:
                url = "http://zamuzh-za-nemca.ru/konsultation/";
                title = getResources().getString(R.string.consultationTitle);
                break;
        }

        activity.getSupportFragmentManager().beginTransaction()
                .replace(R.id.container___activity_main___up, ToolbarFragment.newInstance(title, true, true)).addToBackStack(null)
                .replace(R.id.container___activity_main___center, ItemMenuFragment.newInstance(url, false)).addToBackStack(null)
                .replace(R.id.container___activity_main___bottom, BottomNavigationViewFragment.newInstance(true)).addToBackStack(null)
                .commit();
    }
}