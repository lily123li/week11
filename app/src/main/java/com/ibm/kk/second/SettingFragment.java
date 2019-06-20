package com.ibm.kk.second;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.TextView;

public class SettingFragment extends Fragment implements AdapterView.OnClickListener{

    private ImageButton sc;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frame_setting, container);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        sc = (ImageButton)getView().findViewById(R.id.sc);
        sc.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent list = new Intent(getActivity(),StudentListActivity.class);
        startActivity(list);
    }
}
