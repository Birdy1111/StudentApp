package com.example.z.student;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

public class AddActivity extends AppCompatActivity implements View.OnClickListener, TimePicker.OnTimeChangedListener{
    Button bt_add;
    Button bt_send;
    EditText et_course12;
    EditText et_course34;
    EditText et_course56;
    EditText et_course78;
    EditText et_course910;
    Spinner week;
    Spinner day;
    CBop cbop = new CBop();
    ArrayList<CourseInfo> courselist = new ArrayList<CourseInfo>();
   MySQLiteAccess mySQLiteAccess = new MySQLiteAccess(this, 1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        bt_add= findViewById(R.id.bt_add);
        bt_send=findViewById(R.id.bt_send);
        et_course12=findViewById(R.id.et_course12);
        et_course34=findViewById(R.id.et_course34);
        et_course56=findViewById(R.id.et_course56);
        et_course78=findViewById(R.id.et_course78);
        et_course910=findViewById(R.id.et_course910);
        week= findViewById(R.id.week);
        day= findViewById(R.id.day);
        bt_add.setOnClickListener(this);
        bt_send.setOnClickListener(this);

        cbop.test(this);
        initSpinner_week();
        initSpinner_day();
    }
    private void initSpinner_day() {
        String[] arr = {"星期一", "星期二", "星期三", "星期四", "星期五"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arr);
        day.setAdapter(arrayAdapter);
        day.setSelection(arr.length - 1, true);
    }

    private void initSpinner_week() {
        String[] arr = {"第1周", "第2周", "第3周", "第4周", "第5周","第6周"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arr);
        week.setAdapter(arrayAdapter);
        week.setSelection(arr.length - 1, true);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            //添加按钮
            case R.id.bt_add:
                String week1 = week.getSelectedItem().toString();
                String day1 = day.getSelectedItem().toString();
                String course12=et_course12.getText().toString();
                String course34=et_course34.getText().toString();
                String course56=et_course56.getText().toString();
                String course78=et_course78.getText().toString();
                String course910=et_course910.getText().toString();

                CBop cBop =new CBop();
                cBop.test(this);
                List<CourseInfo> list1= cBop.searchByWeek(week1,day1);

                //对输入的消息进行判断
                if (TextUtils.isEmpty((et_course12.getText()))| TextUtils.isEmpty(et_course34.getText())
                        | TextUtils.isEmpty(et_course56.getText())| TextUtils.isEmpty(et_course78.getText())
                        | TextUtils.isEmpty(et_course910.getText())) {
                    Toast.makeText(AddActivity.this, "请输入完整信息", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(!list1.isEmpty()){
                    Toast.makeText(AddActivity.this, week1+day1+"已添加过课程", Toast.LENGTH_SHORT).show();
                }
                else {
                    //添加课程信息
                    CourseInfo stu = new CourseInfo(week1,day1,course12,course34,course56,course78,course910);
                    courselist.add(stu);
                    CourseInfo s = new CourseInfo(week1,day1,course12,course34,course56,course78,course910);
                    cbop.insert(s);
                    Toast.makeText(AddActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.bt_send:
                    intent.setClass(AddActivity.this, CourseList.class);
                    startActivity(intent);
                break;

        }
    }
    @Override
    public void onTimeChanged(TimePicker timePicker, int i, int i1) {

    }
}
