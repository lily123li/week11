package com.ibm.kk.second;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class HomeFragment extends Fragment implements AdapterView.OnClickListener{

    private ImageButton student;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frame_home, container);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TextView tv = (TextView)getView().findViewById(R.id.homeTextView1);
        student = (ImageButton)getView().findViewById(R.id.students);
        student.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        Intent list = new Intent(getActivity(),StudentInfoActivity.class);
        startActivity(list);
    }
}
