package com.example.z.student;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class IBop {
    private MySQLiteAccess mySQLiteAccess;
    private SQLiteDatabase database;
    public void test(Context context){
        mySQLiteAccess=new MySQLiteAccess(context,3);
        database=mySQLiteAccess.getReadableDatabase();
    }
    public void update(Info stu) {
        database.execSQL("update info set name=?,sex=?,age=?,academy=?,major=?,date=? where id=?"
                ,new Object[] { stu.getName(),stu.getSex(),stu.getAge(),stu.getAcademy(),stu.getMajor(),stu.getDate(),stu.getId()});
    }
    public void insert(Info s){
        int age=Integer.parseInt(s.getAge());
        database.execSQL("insert into info values(?,?,?,?,?,?,?" +
                ")",new Object[] {null,s.getName(),s.getSex(),age,s.getAcademy(),s.getMajor(),s.getDate()});
        System.out.println("插入数据成功");
    }

    public void delete(int id){
        Integer I=new Integer(id);
        //  String n=new String(name);
        database.execSQL("delete from info where id=?",new Object[]{I});
    }

    public List<Info> searchByName(String key){
        List<Info> listByName=new ArrayList<Info>();
        key="%"+key+"%";
        Cursor cursor=database.rawQuery("select * from info where name like?",new String[]{key});
        while(cursor.moveToNext()){

            //   int id=cursor.getInt(cursor.getColumnIndex("id"));

            String name=cursor.getString(cursor.getColumnIndex("name"));
            String sex=cursor.getString(cursor.getColumnIndex("sex"));
            String age=cursor.getString(cursor.getColumnIndex("age"));
            listByName.add(new Info(name,sex,age));
        }
        return listByName;
    }
    //查询所有记录
    public List<Info> queryAll(){
        List<Info> stus=new ArrayList<Info>();
        Cursor cursor=database.rawQuery("select * from info",null);
        while(cursor.moveToNext()){
            int id=cursor.getInt(cursor.getColumnIndex("id"));
            String name=cursor.getString(cursor.getColumnIndex("name"));
            String sex=cursor.getString(cursor.getColumnIndex("sex"));
            String age=String.valueOf(cursor.getInt(cursor.getColumnIndex("age")));
            String aca=cursor.getString(cursor.getColumnIndex("academy"));
            String major=cursor.getString(cursor.getColumnIndex("major"));
            String date=cursor.getString(cursor.getColumnIndex("date"));
            stus.add(new Info(id,name,sex,age,aca,major,date));
        }
        return stus;
    }

    public List<Info> searchByAll(String key){
        List<Info> listByAll=new ArrayList<Info>();
        key="%"+key+"%";
        Cursor cursor=database.rawQuery("select * from info where name like? or academy like? or id like?"
                ,new String[]{key,key,key});
        while(cursor.moveToNext()){
            int id=cursor.getInt(cursor.getColumnIndex("id"));
            String name=cursor.getString(cursor.getColumnIndex("name"));
            String sex=cursor.getString(cursor.getColumnIndex("sex"));
            String age=String.valueOf(cursor.getInt(cursor.getColumnIndex("age")));
            String aca=cursor.getString(cursor.getColumnIndex("academy"));
            String major=cursor.getString(cursor.getColumnIndex("major"));
            String date=cursor.getString(cursor.getColumnIndex("date"));

            listByAll.add(new Info(id,name,sex,age,aca,major,date));

            //System.out.print("查询到数据");
        }
        return listByAll;
    }
}
