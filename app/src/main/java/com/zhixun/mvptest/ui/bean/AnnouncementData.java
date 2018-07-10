package com.zhixun.mvptest.ui.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/6/12.
 */

public class AnnouncementData implements Serializable {
    private String  content;//内容
    private String  noticeDate;// 过期时间
    private String title;//标题

    public String getContent() {
        return content == null ? "" : content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNoticeDate() {
        return noticeDate == null ? "" : noticeDate;
    }

    public void setNoticeDate(String noticeDate) {
        this.noticeDate = noticeDate;
    }

    public String getTitle() {
        return title == null ? "" : title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
