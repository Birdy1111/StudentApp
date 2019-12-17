package com.example.z.student;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, TimePicker.OnTimeChangedListener {
    Button bt_add;
    Button bt_send;
    EditText et_name;
    EditText et_software;
    EditText et_design;
    EditText et_assembly;
    EditText et_data;
    Spinner classesspinner;
    DBop dbop = new DBop();
    ArrayList<StuInfo> studentlist = new ArrayList<StuInfo>();
    MySQLiteAccess mySQLiteAccess = new MySQLiteAccess(MainActivity.this, 1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt_add = (Button) findViewById(R.id.bt_add);
        bt_send = (Button) findViewById(R.id.bt_send);
        classesspinner = (Spinner) findViewById(R.id.classes);

        et_name = (EditText) findViewById(R.id.et_name);
        et_design = (EditText) findViewById(R.id.et_design);
        et_assembly= (EditText) findViewById(R.id.et_assembly);
        et_data = (EditText) findViewById(R.id.et_data);
        et_software = (EditText) findViewById(R.id.et_software);



        bt_add.setOnClickListener(this);
        bt_send.setOnClickListener(this);

        dbop.test(this);
        initSpinner();
    }


    private boolean isDigital(String num)  //正则表达式判断输入是否为0-100的数字
    {
        return num.matches("([0-9])|([1-9][0-9])|(100)");
    }



    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            //添加按钮
            case R.id.bt_add:
                String aca = classesspinner.getSelectedItem().toString();
                String name = et_name.getText().toString();
                String design = et_design.getText().toString();
                String assembly = et_assembly.getText().toString();
                String data = et_data.getText().toString();
                String software = et_software.getText().toString();

                //对输入的消息进行判断
                if (TextUtils.isEmpty((et_name.getText())) | TextUtils.isEmpty(et_design.getText()) | TextUtils.isEmpty(et_assembly.getText())
                        | TextUtils.isEmpty(et_data.getText()) | TextUtils.isEmpty(et_software.getText())) {
                    Toast.makeText(MainActivity.this, "请输入完整信息", Toast.LENGTH_SHORT).show();
                    return;
                } else if (aca == "选择班级") {
                    Toast.makeText(MainActivity.this, "请选择正确的班级", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!isDigital(design.toString())) {
                    et_design.setError("请输入数字");
                }
                else if (!isDigital(assembly.toString())) {
                    et_assembly.setError("请输入数字");
                }
                else if (!isDigital(data.toString())) {
                    et_data.setError("请输入数字");
                }
                else if (!isDigital(software.toString())) {
                    et_software.setError("请输入数字");
                }
                else {
                    StuInfo stu = new StuInfo(name,aca,Integer.parseInt(design),Integer.parseInt(assembly),Integer.parseInt(data),Integer.parseInt(software));
                    studentlist.add(stu);
                    StuInfo s = new StuInfo(name,aca,Integer.parseInt(design),Integer.parseInt(assembly),Integer.parseInt(data),Integer.parseInt(software));
                    dbop.insert(s);
                    Toast.makeText(MainActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.bt_send:

                    intent.putExtra("students", (Serializable) studentlist);
                    intent.setClass(MainActivity.this, StudentList.class);
                    startActivity(intent);
                break;

        }
    }
    private void initSpinner() {
        String[] arr = {"计科1班", "计科2班", "计科3班", "软件1班", "软件2班", "软件3班","选择班级"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arr);
        classesspinner.setAdapter(arrayAdapter);
        classesspinner.setSelection(arr.length - 1, true);
    }
    @Override
    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
    }

    private void test() {
        MySQLiteAccess mySQLiteAccess = new MySQLiteAccess(this, 1);
        mySQLiteAccess.getReadableDatabase();
    }
}
