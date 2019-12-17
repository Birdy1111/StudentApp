package com.example.z.student;

import android.content.Intent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;


import java.util.List;

public class TableActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_table);

        //找到控件
        ClassScheduleView csv = findViewById(R.id.class_view);
        CBop cBop =new CBop();
        Bundle bundle=getIntent().getExtras();
        String week=bundle.getString("week");
        System.out.println("week-----------------"+week);
        cBop.test(this);
        List<CourseInfo> list1= cBop.searchByWeek(week,"星期一");
        List<CourseInfo> list2= cBop.searchByWeek(week,"星期二");
        List<CourseInfo> list3= cBop.searchByWeek(week,"星期三");
        List<CourseInfo> list4= cBop.searchByWeek(week,"星期四");
        List<CourseInfo> list5= cBop.searchByWeek(week,"星期五");

        //设置Item数据
        if(!list1.isEmpty()) {
            csv.setItemText(1, 1, list1.get(0).getCourse_12(), "高老师");//星期一
            csv.setItemText(1, 2, list1.get(0).getCourse_34(), "杜老师");
            csv.setItemText(1, 3, list1.get(0).getCourse_56(), "马老师");
            csv.setItemText(1, 4, list1.get(0).getCourse_78(), "冉老师");
            csv.setItemText(1, 5, list1.get(0).getCourse_910(), "张老师");
        }

        if(!list2.isEmpty()) {
            csv.setItemText(2, 1, list2.get(0).getCourse_12(), "高老师");//星期二
            csv.setItemText(2, 2, list2.get(0).getCourse_34(), "杜老师");
            csv.setItemText(2, 3, list2.get(0).getCourse_56(), "马老师");
            csv.setItemText(2, 4, list2.get(0).getCourse_78(), "冉老师");
            csv.setItemText(2, 5, list2.get(0).getCourse_910(), "张老师");
        }

        if(!list3.isEmpty()) {
            csv.setItemText(3, 1, list3.get(0).getCourse_12(), "高老师");//星期三
            csv.setItemText(3, 2, list3.get(0).getCourse_34(), "杜老师");
            csv.setItemText(3, 3, list3.get(0).getCourse_56(), "马老师");
            csv.setItemText(3, 4, list3.get(0).getCourse_78(), "冉老师");
            csv.setItemText(3, 5, list3.get(0).getCourse_910(), "张老师");
        }

        if (!list4.isEmpty()) {
            csv.setItemText(4, 1, list4.get(0).getCourse_12(), "高老师");//星期四
            csv.setItemText(4, 2, list4.get(0).getCourse_34(), "杜老师");
            csv.setItemText(4, 3, list4.get(0).getCourse_56(), "马老师");
            csv.setItemText(4, 4, list4.get(0).getCourse_78(), "冉老师");
            csv.setItemText(4, 5, list4.get(0).getCourse_910(), "张老师");
        }
        if(!list5.isEmpty()) {
            csv.setItemText(5, 1, list5.get(0).getCourse_12(), "高老师");//星期五
            csv.setItemText(5, 2, list5.get(0).getCourse_34(), "杜老师");
            csv.setItemText(5, 3, list5.get(0).getCourse_56(), "马老师");
            csv.setItemText(5, 4, list5.get(0).getCourse_78(), "冉老师");
            csv.setItemText(5, 5, list5.get(0).getCourse_910(), "张老师");
        }
        //设置标题
        csv.setTitle("学生课表管理");

        csv.setWeekItemAllTextSize(15, 10);
        csv.setSectionItemTextSize(8, 14, 10);


        csv.setRowCount(6);
        csv.setColumnCount(6);

        //返回事件
        csv.setOnBackPressListener(new ClassScheduleView.OnBackPressListener() {
            @Override
            public void onBackPressed() {
                Intent intent = new Intent(TableActivity.this, CourseList.class);
                startActivity(intent);
            }
        });
        //Item点击事件
        csv.setOnItemClickListener(new ClassScheduleView.OnItemClickListener() {
            @Override
            public void onItemClick(ItemOptions options, int row, int column, String name, String mark) {
                Log.e("RRL", "name:" + name + ",mark:" + mark);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
