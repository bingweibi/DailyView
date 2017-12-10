package com.example.bbw.openzz.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.bbw.openzz.R;
import com.example.bbw.openzz.activity.AppIntroduce;
import com.example.bbw.openzz.activity.License;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bbw on 2017/10/21.
 * @author bibingwei
 */

public class FragmentFour extends Fragment{

    private ListView mListView;
    private View mView;
    private ArrayAdapter<String> aboutInfoAdapter;
    private String[] infoData={"应用介绍","夜间模式","清理缓存","点个赞吧","作者博客","注意版权"};

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_four,container,false);
        mListView = mView.findViewById(R.id.appInfo);
        aboutInfoAdapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_list_item_1,infoData);
        mListView.setAdapter(aboutInfoAdapter);
        return mView;
    }

    @Override
    public void onStart() {
        super.onStart();
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        startActivity(new Intent(getActivity(), AppIntroduce.class));
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://github.com/bingweibi/OpenZZ")));
                        break;
                    case 4:
                        startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://bingweibi.github.io")));
                        break;
                    case 5:
                        startActivity(new Intent(getActivity(), License.class));
                        break;
                        default:
                }
            }
        });
    }
}
