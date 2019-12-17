package com.example.z.student;

public class CourseInfo {
    private String day;
    private String course_12;
    private String course_34;
    private String course_56;
    private String course_78;
    private String course_910;
    private String week;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getCourse_12() {
        return course_12;
    }

    public void setCourse_12(String course_12) {
        this.course_12 = course_12;
    }

    public String getCourse_34() {
        return course_34;
    }

    public void setCourse_34(String course_34) {
        this.course_34 = course_34;
    }

    public String getCourse_56() {
        return course_56;
    }

    public void setCourse_56(String course_56) {
        this.course_56 = course_56;
    }

    public String getCourse_78() {
        return course_78;
    }

    public void setCourse_78(String course_78) {
        this.course_78 = course_78;
    }

    public String getCourse_910() {
        return course_910;
    }

    public void setCourse_910(String course_910) {
        this.course_910 = course_910;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }


    public CourseInfo(String week, String day,
                      String course_1, String course_2, String course_3, String course_4, String course_5){
        this.week=week;
        this.day = day;
        this.course_12=course_1;
        this.course_34 = course_2;
        this.course_56 = course_3;
        this.course_78 = course_4;
        this.course_910 = course_5;
    }
    public CourseInfo(String week, String day){
        this.week=week;
        this.day = day;
    }
}
