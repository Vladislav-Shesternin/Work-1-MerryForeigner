package com.nekoder.zamuzhzanemca.fragments.fragment_center;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.nekoder.zamuzhzanemca.R;
import com.nekoder.zamuzhzanemca.fragments.fragment_up.ToolbarFragment;


public class OtherMenuFragment extends Fragment implements View.OnClickListener {
    ImageView imgContact, imgReply, imgConfidentiality;
    TextView tvContact, tvReply, tvConfidentiality;
    FragmentActivity activity;
    String url, title;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        activity = getActivity();
        return inflater.inflate(R.layout.fragment_other_menu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initListener();
    }

    public void initView(View parent) {
        //items: ImageView
        imgContact = parent.findViewById(R.id.imageView___fragment_other_menu___contact);
        imgReply = parent.findViewById(R.id.imageView___fragment_other_menu___reply);
        imgConfidentiality = parent.findViewById(R.id.imageView___fragment_other_menu___confidentiality);
        //items: TextView
        tvContact = parent.findViewById(R.id.textView___fragment_other_menu___contact);
        tvReply = parent.findViewById(R.id.textView___fragment_other_menu___reply);
        tvConfidentiality = parent.findViewById(R.id.textView___fragment_other_menu___confidentiality);
    }

    public void initListener() {
        //items: ImageView
        imgContact.setOnClickListener(this);
        imgReply.setOnClickListener(this);
        imgConfidentiality.setOnClickListener(this);
        //items: TextView
        tvContact.setOnClickListener(this);
        tvReply.setOnClickListener(this);
        tvConfidentiality.setOnClickListener(this);
    }

    /**
     * onClick
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageView___fragment_other_menu___contact:
            case R.id.textView___fragment_other_menu___contact:
                title = getResources().getString(R.string.contact);
                url = "http://zamuzh-za-nemca.ru/contacts/";
                break;
            case R.id.imageView___fragment_other_menu___reply:
            case R.id.textView___fragment_other_menu___reply:
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("application/vnd.android.package-archive");
                intent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=com.nekoder.zamuzhzanemca");
                startActivity(Intent.createChooser(intent, "Поделиться приложением"));
                return;
            case R.id.imageView___fragment_other_menu___confidentiality:
            case R.id.textView___fragment_other_menu___confidentiality:
                title = getResources().getString(R.string.confidentiality);
                url = "http://zamuzh-za-nemca.ru/contacts/#policy";
                break;
        }

        activity.getSupportFragmentManager().beginTransaction()
                .replace(R.id.container___activity_main___up, ToolbarFragment.newInstance(title, true, false)).addToBackStack(null)
                .replace(R.id.container___activity_main___center, ItemMenuFragment.newInstance(url, false)).addToBackStack(null)
                //R.id.container___activity_main___bottom не заменяем, потому что изменения в него (тут) не нужны
                .commit();
    }
}
