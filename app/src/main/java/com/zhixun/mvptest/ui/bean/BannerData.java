package com.zhixun.mvptest.ui.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/4/9.
 */

public class BannerData implements Serializable{
  private String  gmtCreate	;//	string
    private String gmtModify;//		string
    private String httpUrl;//	Url地址跳转	string
    private String  id	;//	string
    private String  imgUrl;//	图片	string
    private String  name;//	文件名称	string
    private int priority;//	优先级	number
    private String  title;//	标题	string

    public String getGmtCreate() {
        return gmtCreate == null ? "" : gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getGmtModify() {
        return gmtModify == null ? "" : gmtModify;
    }

    public void setGmtModify(String gmtModify) {
        this.gmtModify = gmtModify;
    }

    public String getHttpUrl() {
        return httpUrl == null ? "" : httpUrl;
    }

    public void setHttpUrl(String httpUrl) {
        this.httpUrl = httpUrl;
    }

    public String getId() {
        return id == null ? "" : id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl == null ? "" : imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getTitle() {
        return title == null ? "" : title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
