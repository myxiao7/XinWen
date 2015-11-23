package com.sizhuo.xinwen.entity;

/**
 * Created by My灬xiao7 on 2015/11/23.
 * 简要新闻列表
 */
public class JianLue {
    /**
     * 文章ID
     */
    public String id;
    /**
     * 文章标题
     */
    public String title;
    /**
     * 文章链接
     */
    public String link;
    /**
     * 文章简述
     */
    public String descr;
    /**
     * 文章来源
     */
    public String refinfo;
    /**
     * 文章缩略图
     */
    public String thumb;
    /**
     * 文章时间
     */
    public String time;

    public JianLue(String id, String title, String link, String descr, String refinfo, String thumb, String time) {
        this.id = id;
        this.title = title;
        this.link = link;
        this.descr = descr;
        this.refinfo = refinfo;
        this.thumb = thumb;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getRefinfo() {
        return refinfo;
    }

    public void setRefinfo(String refinfo) {
        this.refinfo = refinfo;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
