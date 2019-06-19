package com.ibm.kk.second;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

public class FuncFragment extends Fragment implements AdapterView.OnClickListener{

    private Button academy;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frame_func, container);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        academy = (Button)getView(). findViewById(R.id.academy);
        academy.setOnClickListener(this);





    }

    @Override
    public void onClick(View v) {
        Intent list = new Intent(getActivity(),AcademyInfoActivity.class);
        startActivity(list);

    }
}
