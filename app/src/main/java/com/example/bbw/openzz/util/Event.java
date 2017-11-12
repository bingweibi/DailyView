package com.example.bbw.openzz.util;

import com.example.bbw.openzz.Model.Gank.Gank;

import java.util.List;

/**
 * Created by bbw on 2017/10/31.
 * @author bbw
 */

public class Event {

    private List<Gank.results> showPicList;
    private int position;

    public Event(List<Gank.results> showPicList,int position) {
        this.showPicList = showPicList;
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public List<Gank.results> getShowPicList() {
        return showPicList;
    }

    public void setShowPicList(List<Gank.results> showPicList) {
        this.showPicList = showPicList;
    }
}
