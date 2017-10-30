package com.example.bbw.openzz.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bbw.openzz.Model.ZhiHuDailyLatest.ZhiHuDailyLatest;
import com.example.bbw.openzz.R;
import com.example.bbw.openzz.util.ResponseHandleUtility;

import org.json.JSONException;

import java.util.List;

/**
 * Created by bbw on 2017/10/23.
 * @author bibingwei
 * 点击标签显示对应的内容
 */

public class FragmentsContent extends Fragment{

    /**
     * 点击的标签
     */
    private String stories;
    private CardView mCardView;
    private ImageView mImageView;
    private TextView mTextView;
    private LinearLayout mLinearLayout;

    @Override
    public void setArguments(Bundle args){
        this.stories = args.getString("story");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_content,container,false);
        mLinearLayout = view.findViewById(R.id.fragment_contentLayout);
        try {
            final List<ZhiHuDailyLatest.StoryBean> storiesList  = ResponseHandleUtility.handleZhuHuDailyLatest(this.stories);
            for (int i=0;i<storiesList.size();i++){
                mCardView = new CardView(getContext());
                mTextView = new TextView(getContext());
                mImageView = new ImageView(getContext());
                mTextView.setText(storiesList.get(i).getTitle());
                mCardView.addView(mTextView);
                Glide.with(getActivity())
                        .load(storiesList.get(i).getImages().get(0))
                        .thumbnail(0.2f)
                        .into(mImageView);
                mCardView.addView(mImageView);
                mLinearLayout.addView(mCardView);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return view;
    }

}
