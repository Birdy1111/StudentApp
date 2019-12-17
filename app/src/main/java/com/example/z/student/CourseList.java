package com.example.z.student;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CourseList extends AppCompatActivity implements  View.OnClickListener,
        AdapterView.OnItemClickListener{

    private Button search;
    private Button menu;
    private Button add;
    private Spinner day;
    private Spinner week;
    CBop cbop = new CBop();

    List<Map<String, Object>> couMap = new ArrayList<Map<String, Object>>();
    List<String> course_12 = new ArrayList<String>();
    List<String> course_34 = new ArrayList<String>();
    List<String> course_56= new ArrayList<String>();
    List<String> course_78 = new ArrayList<String>();
    List<String> Course_910 = new ArrayList<String>();
    ListView courselist;

    private List<CourseInfo> list = new ArrayList<CourseInfo>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);


        search = (Button) findViewById(R.id.search);
        add = (Button) findViewById(R.id.add);
        menu = findViewById(R.id.menu);
        day = findViewById(R.id.day);
        week = findViewById(R.id.week);

        search.setOnClickListener(this);
        add.setOnClickListener(this);
        menu.setOnClickListener(this);
        initSpinner_week("周次");
        cbop.test(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.menu:
                intent.setClass(this, MenuActivity.class);
                startActivity(intent);
                break;
           case R.id.add:
                intent.setClass(this, AddActivity.class);
                startActivity(intent);
                break;
            case R.id.search:

                String et_week = week.getSelectedItem().toString();
                intent.setClass(this, TableActivity.class);

                intent.putExtra("week",et_week);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
    private void initSpinner_day(String aca) {
        String[] arr = {"星期一", "星期二", "星期三", "星期四", "星期五"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arr);

        for (int i = 0; i < arr.length ; i++) {
            if(arr[i].equals(aca))
            {day.setSelection(i);
            }
        }
    }
    private void initSpinner_week(String aca) {
        String[] arr = {"第1周", "第2周", "第3周", "第4周", "第5周","第6周"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arr);
        week.setAdapter(arrayAdapter);
        for (int i = 0; i < arr.length ; i++) {
            if(arr[i].equals(aca))
            {week.setSelection(i);
            }
        }
    }

    public void itemClick(View view) {

        int position = (int) view.getTag();
        Intent intent = new Intent();
        switch (view.getId()) {

        }
    }
}
