import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.z.student.MainActivity;
import com.example.z.student.MySQLiteAccess;
import com.example.z.student.StuInfo;

import java.util.ArrayList;
import java.util.List;

public class DBop {
    private MySQLiteAccess mySQLiteAccess;
    private SQLiteDatabase database;

    public void test(Context context){
        mySQLiteAccess=new MySQLiteAccess(context,3);
        database=mySQLiteAccess.getReadableDatabase();
    }
    public void insert(StuInfo s){
        database.execSQL("insert into students values(?,?,?,?,?,?)",new Object[] {s.getName(),s.getClasses(),s.getDesign(),s.getAssembly(),s.getData(),s.getSoftware()});
        System.out.println("插入数据成功");
    }
    public void delete(String name){
       // Integer I=new Integer(id);
        String n=new String(name);
        database.execSQL("delete from student where name=?",new Object[]{n});
    }
   public List<StuInfo> searchByName(String key){
        List<StuInfo> listByName=new ArrayList<StuInfo>();
        key="%"+key+"%";
        Cursor cursor=database.rawQuery("select * from student where name like?",new String[]{key});
        while(cursor.moveToNext()){
            int id=cursor.getInt(cursor.getColumnIndex("id"));
            String name=cursor.getString(cursor.getColumnIndex("name"));
            String classes=cursor.getString(cursor.getColumnIndex("classes"));
            int design=cursor.getInt(cursor.getColumnIndex("design"));
            int assembly=cursor.getInt(cursor.getColumnIndex("assembly"));
            int data = cursor.getInt(cursor.getColumnIndex("data"));
            int software = cursor.getInt(cursor.getColumnIndex("software"));
            listByName.add(new StuInfo(id,name,classes,design,assembly,data,software));
        }
        return listByName;
    }

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

  public void update(StuInfo stu) {
        database.execSQL("update student set name=?,classes=?,design=?,assembly=?,data=?,software=?"
                ,new Object[] { stu.getName(),stu.getClasses(),stu.getDesign(),stu.getAssembly(),stu.getData(),stu.getSoftware() });
    }

    /*public List<StuInfo> searchByAll(String key){
        List<StuInfo> listByAll=new ArrayList<StuInfo>();
        key="%"+key+"%";
        Cursor cursor=database.rawQuery("select * from student where  name like? or sex like? or age like?"
                ,new String[]{key,key,key});
        while(cursor.moveToNext()){
           //
           int id=cursor.getInt(cursor.getColumnIndex("id"));
            String name=cursor.getString(cursor.getColumnIndex("name"));
            String sex=cursor.getString(cursor.getColumnIndex("sex"));
            String age=cursor.getString(cursor.getColumnIndex("age"));
            listByAll.add(new StuInfo(name,sex,age));
        }
        return listByAll;
    }*/
}
