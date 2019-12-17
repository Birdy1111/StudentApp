package com.example.z.student;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteAccess extends SQLiteOpenHelper {

    public MySQLiteAccess(Context context, int version) {
        super(context, "stu.db", null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("数据库创建");
        db.execSQL("create table students(\n"+
                "id integer primary key  autoincrement,\n"+
                "name text,\n"+
                "classes text,\n"+
                "design text,\n"+
                "assembly text,\n"+
                "data text\n,"+
                "software text\n"+
                ");");
        db.execSQL("create table info(\n"+
                "id integer primary key  autoincrement,\n"+
                "name text,\n"+
                "sex text,\n"+
                "age integer,\n"+
                "academy text,\n"+
                "major text\n,"+
                "date text\n"+
                ");");
        db.execSQL("create table course(\n" +
                "  week text,\n"+
                "  day text,\n" +
                "  course_12 text,\n" +
                "  course_34 text,\n" +
                "  course_56 text,\n" +
                "  course_78 text,\n" +
                "  course_910 text,\n" +
                " primary key(week,day));");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
