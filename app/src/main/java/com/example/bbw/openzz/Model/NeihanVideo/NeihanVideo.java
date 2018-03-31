package com.example.bbw.openzz.Model.NeihanVideo;

import java.util.List;

/**
 * @author bbw
 * @date 2017/11/20
 */

public class NeihanVideo {

    private NeihanData data;

    public NeihanData getData() {
        return data;
    }

    public static class NeihanData {

        private List<NeihanVideoData> data;
        public List<NeihanVideoData> getData() {
            return data;
        }
        public static class NeihanVideoData {
            public NeihanDataGroup getGroup() {
                return group;
            }
            private NeihanDataGroup group;
            public static class NeihanDataGroup {
                private String mp4_url;
                private String text;

                public NeihanDataGroup(String mp4_url,String text) {
                    this.mp4_url = mp4_url;
                    this.text = text;
                }

                public String getText() {
                    return text;
                }

                public void setText(String text) {
                    this.text = text;
                }

                public String getMp4_url() {
                    return mp4_url;
                }

                public void setMp4_url(String mp4_url) {
                    this.mp4_url = mp4_url;
                }
            }
        }
    }
}
