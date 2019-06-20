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

public class FuncFragment extends Fragment implements AdapterView.OnClickListener{

    private ImageButton academy1;
    private ImageButton activity1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frame_func, container);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        academy1 = (ImageButton)getView().findViewById(R.id.academy1);
        academy1.setOnClickListener(this);
        activity1 = (ImageButton)getView().findViewById(R.id.activity1);
        activity1.setOnClickListener(this);




    }

    @Override
    public void onClick(View btn) {

        if (btn.getId() == R.id.academy1) {
            Intent list = new Intent(getActivity(),AcademyInfoActivity.class);
            startActivity(list);

        }if (btn.getId() == R.id.activity1) {
            Intent list = new Intent(getActivity(),MyList2Activity.class);
            startActivity(list);

        }

    }
}
