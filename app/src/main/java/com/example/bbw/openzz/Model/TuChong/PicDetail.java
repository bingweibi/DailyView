package com.example.bbw.openzz.Model.TuChong;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by bbw on 2017/11/10.
 * @author bbw
 * pic参数信息
 * desc:各类参数
 * content:参数信息
 */

public class PicDetail {

    public List<Abstract> abstractList;

    public List<Abstract> getAbstractList() {
        return abstractList;
    }

    public void setAbstractList(List<Abstract> abstractList) {
        this.abstractList = abstractList;
    }

    private static class Abstract {
        public String desc;
        public String content;

        public Abstract(String desc, String content) {
            this.desc = desc;
            this.content = content;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
