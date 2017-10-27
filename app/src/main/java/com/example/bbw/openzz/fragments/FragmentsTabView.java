package com.example.bbw.openzz.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bbw.openzz.R;

/**
 * Created by bbw on 2017/10/23.
 * @author bibingwei
 * 点击标签显示对应的内容
 */

public class FragmentsTabView extends Fragment{

    /**
     * 点击的标签
     */
    private String selectedTag;

    @Override
    public void setArguments(Bundle args){
        this.selectedTag = args.getString("name");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content,container,false);
        ((TextView)view.findViewById(R.id.fragmentContent_CardViewText)).setText(this.selectedTag);
        return view;
    }

}
