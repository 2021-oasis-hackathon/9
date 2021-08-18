package com.hamseong.hohaeng.model;

import java.io.Serializable;

public class AllOtherCourseInfo implements Serializable {
    private AllCourseInfo allCourseInfo;
    private String name;
    private String star;
    private String content;

    public AllOtherCourseInfo(AllCourseInfo allCourseInfo, String name, String star, String content) {
        this.allCourseInfo = allCourseInfo;
        this.name = name;
        this.star = star;
        this.content = content;
    }

    public AllCourseInfo getAllCourseInfo() {
        return allCourseInfo;
    }

    public void setAllCourseInfo(AllCourseInfo allCourseInfo) {
        this.allCourseInfo = allCourseInfo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
