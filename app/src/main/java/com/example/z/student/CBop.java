package com.example.z.student;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class CBop {
    private MySQLiteAccess mySQLiteAccess;
    private SQLiteDatabase database;
   public void test(Context context){
        mySQLiteAccess=new MySQLiteAccess(context,3);
        database=mySQLiteAccess.getReadableDatabase();
    }
    public void update(CourseInfo cou) {
        database.execSQL("update course set course_12=?,course_34=?,course_56=?,course_78=?,course_910=? where week is ? and day is ?"
                ,new Object[] {cou.getCourse_12(),cou.getCourse_34(),cou.getCourse_56(),cou.getCourse_78(),cou.getCourse_910(),cou.getWeek(),cou.getDay()});
    }
    public void insert(CourseInfo cou){
        database.execSQL("insert into course values(?,?,?,?,?,?,?)",new Object[] {cou.getWeek(),cou.getDay(),cou.getCourse_12(),cou.getCourse_34(),cou.getCourse_56(),cou.getCourse_78(),cou.getCourse_910()});
        System.out.println("插入数据成功");
    }
    public List<CourseInfo> searchByWeek(String key1, String key2){
        List<CourseInfo> listByWeek=new ArrayList<CourseInfo>();
        System.out.println("Week       "+key1+"            day            "+key2);
        Cursor cursor=database.rawQuery("select * from course where week is ? and day is ?",new String[]{key1,key2});

        while(cursor.moveToNext()) {
            String week = cursor.getString(cursor.getColumnIndex("week"));
            String day = cursor.getString(cursor.getColumnIndex("day"));
            String course_1 = cursor.getString(cursor.getColumnIndex("course_12"));
            String course_2 = cursor.getString(cursor.getColumnIndex("course_34"));
            String course_3 = cursor.getString(cursor.getColumnIndex("course_56"));
            String course_4 = cursor.getString(cursor.getColumnIndex("course_78"));
            String course_5 = cursor.getString(cursor.getColumnIndex("course_910"));
            listByWeek.add(new CourseInfo(week, day, course_1, course_2, course_3, course_4, course_5));
        }
        return listByWeek;
    }
}
