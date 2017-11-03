package com.example.bbw.openzz.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.bbw.openzz.R;


/**
 * Created by bbw on 2017/10/23.
 * @author bibingwei
 */

public class FragmentsContent extends Fragment{

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_content,container,false);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
