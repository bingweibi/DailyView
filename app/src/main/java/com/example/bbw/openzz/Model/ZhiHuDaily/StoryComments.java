package com.example.bbw.openzz.Model.ZhiHuDaily;

import java.util.List;

/**
 * Created by bbw on 2017/11/9.
 * @author bbw
 */

public class StoryComments {

    private List<CommentBean> commentBeanList;

    public List<CommentBean> getCommentBeanList() {
        return commentBeanList;
    }

    public void setCommentBeanList(List<CommentBean> commentBeanList) {
        this.commentBeanList = commentBeanList;
    }

    public static class CommentBean {
        private String author;
        private String content;
        private String avatar;

        public CommentBean(String author, String content, String avatar) {
            this.author = author;
            this.content = content;
            this.avatar = avatar;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }
    }
}
