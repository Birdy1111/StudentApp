package com.example.z.student;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DBop {
    private MySQLiteAccess mySQLiteAccess;
    private SQLiteDatabase database;
    public void test(Context context){
        mySQLiteAccess=new MySQLiteAccess(context,3);
        database=mySQLiteAccess.getReadableDatabase();

    }
    public void update(StuInfo stu) {
        database.execSQL("update students set name=?,classes=?,design=?,assembly=?,data=?,software=? where id=?"
                ,new Object[] { stu.getName(),stu.getClasses(),stu.getDesign(),stu.getAssembly(),stu.getData(),stu.getSoftware(),stu.getId()});
    }
    public void insert(StuInfo s){

        database.execSQL("insert into students values(?,?,?,?,?,?,?)",new Object[] {null,s.getName(),s.getClasses(),s.getDesign(),s.getAssembly(),s.getData(),s.getSoftware()});
        System.out.println("插入数据成功");
    }

    public void delete(int id){
        Integer I=new Integer(id);
     //  String n=new String(name);
        database.execSQL("delete from students where id=?",new Object[]{I});
    }

    public List<StuInfo> searchByName(String key){
        List<StuInfo> listByName=new ArrayList<StuInfo>();
        key="%"+key+"%";
        Cursor cursor=database.rawQuery("select * from students where name like?",new String[]{key});
        while(cursor.moveToNext()){


           int id=cursor.getInt(cursor.getColumnIndex("id"));
            String name=cursor.getString(cursor.getColumnIndex("name"));
            String classes = cursor.getString(cursor.getColumnIndex("classes"));
            int design = cursor.getInt(cursor.getColumnIndex("design"));
            int assembly = cursor.getInt(cursor.getColumnIndex("assembly"));
            int data = cursor.getInt(cursor.getColumnIndex("data"));
            int software = cursor.getInt(cursor.getColumnIndex("software"));

            listByName.add(new StuInfo(id,name,classes,design,assembly,data,software));
        }
        return listByName;
    }
    //查询所有记录
    public List<StuInfo> queryAll(){
        List<StuInfo> stus=new ArrayList<StuInfo>();
        Cursor cursor=database.rawQuery("select * from students",null);
        while(cursor.moveToNext()){
            int id=cursor.getInt(cursor.getColumnIndex("id"));
            String name=cursor.getString(cursor.getColumnIndex("name"));
            String classes=cursor.getString(cursor.getColumnIndex("classes"));
            int design=cursor.getInt(cursor.getColumnIndex("design"));
            int assembly=cursor.getInt(cursor.getColumnIndex("assembly"));
            int data = cursor.getInt(cursor.getColumnIndex("data"));
            int software = cursor.getInt(cursor.getColumnIndex("software"));
            stus.add(new StuInfo(id,name,classes,design,assembly,data,software));
        }
        return stus;
    }

   public List<StuInfo> searchByAll(String key){
        List<StuInfo> listByAll=new ArrayList<StuInfo>();
        key="%"+key+"%";
        Cursor cursor=database.rawQuery("select * from students where name like? or classes like? or id like?"
                ,new String[]{key,key,key});
        while(cursor.moveToNext()){
            int id=cursor.getInt(cursor.getColumnIndex("id"));
            String name=cursor.getString(cursor.getColumnIndex("name"));
            String classes=cursor.getString(cursor.getColumnIndex("classes"));
            int design=cursor.getInt(cursor.getColumnIndex("design"));
            int assembly=cursor.getInt(cursor.getColumnIndex("assembly"));
            int data = cursor.getInt(cursor.getColumnIndex("data"));
            int software = cursor.getInt(cursor.getColumnIndex("software"));

            listByAll.add(new StuInfo(id,name,classes,design,assembly,data,software));

            //System.out.print("查询到数据");
        }
        return listByAll;
    }
}
