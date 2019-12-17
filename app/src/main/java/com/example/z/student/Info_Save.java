package com.example.z.student;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.Serializable;
import java.time.Month;
import java.time.MonthDay;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class Info_Save extends AppCompatActivity implements View.OnClickListener, TimePicker.OnTimeChangedListener {
    Button bt_add;
    Button bt_send;
    EditText et_name;
    AutoCompleteTextView et_major;
    EditText et_age;
    RadioGroup rg_sex;
    Spinner academyspinner;
    EditText et_date;
    private Calendar cal;
    private int y, m, d;
    IBop dbop = new IBop();
    ArrayList<Info> studentlist = new ArrayList<Info>();
    MySQLiteAccess mySQLiteAccess = new MySQLiteAccess(Info_Save.this, 1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_save);
        bt_add = (Button) findViewById(R.id.bt_add);
        bt_send = (Button) findViewById(R.id.bt_send);
        academyspinner = (Spinner) findViewById(R.id.academy);
        et_date = (EditText) findViewById(R.id.et_date);

        et_name = (EditText) findViewById(R.id.et_name);
        et_major = (AutoCompleteTextView) findViewById(R.id.et_major);
        et_age = (EditText) findViewById(R.id.et_age);
        rg_sex = (RadioGroup) findViewById(R.id.rg_sex);

        String[] arr = {"物联网工程", "计算机科学", "电子信息", "通信工程"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(this, R.layout.au_textview, arr);
        et_major.setAdapter(arrayAdapter);


        bt_add.setOnClickListener(this);
        bt_send.setOnClickListener(this);
        et_date.setKeyListener(null);
        et_date.setOnClickListener(this);

        dbop.test(this);
        initSpinner();
    }


    private boolean isDigital(String num)  //正则表达式判断输入是否为数字
    {
        return num.matches("[0-9]{1,}");
    }

    private String getRadioInfo(RadioGroup radioGroup) {
        String info = "";
        int num = radioGroup.getChildCount();
        for (int i = 0; i < num; i++) {
            RadioButton rd = (RadioButton) radioGroup.getChildAt(i);
            if (rd.isChecked()) {
                info = rd.getText().toString();
                break;
            }
        }
        return info;
    }
    //单选项
    private String getCheckBoxInfo(LinearLayout CheckBoxgroup) {
        String info = "";
        int num = CheckBoxgroup.getChildCount();
        for (int i = 0; i < num; i++) {
            CheckBox cb = (CheckBox) CheckBoxgroup.getChildAt(i);
            if (cb.isChecked()) {
                info += cb.getText().toString() + "\n";
            }
        }
        return info;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            //添加按钮
            case R.id.bt_add:
                String aca = academyspinner.getSelectedItem().toString();
                String name = et_name.getText().toString();
                String major = et_major.getText().toString();
                String age = et_age.getText().toString();
                String sex = getRadioInfo(rg_sex);
//                String kecheng = getCheckBoxInfo(check).toString();
                String date = et_date.getText().toString();
                CharSequence a = et_age.getText();
                //对输入的消息进行判断
                if (TextUtils.isEmpty(a) | TextUtils.isEmpty((et_major.getText())) | TextUtils.isEmpty(et_name.getText())) {
                    Toast.makeText(Info_Save.this, "请输入完整信息", Toast.LENGTH_SHORT).show();
                    return;
                } else if (aca == "选择学院") {
                    Toast.makeText(Info_Save.this, "请选择正确的学院", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!isDigital(a.toString())) {
                    et_age.setError("请输入数字");
                } else {
                    Info stu = new Info(name, major, age, sex,aca, date);
                    studentlist.add(stu);
                    Info s = new Info(name, sex, age, aca, major, date);
                    dbop.insert(s);
                    Toast.makeText(Info_Save.this, "添加成功", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.bt_send:

                    intent.putExtra("students", (Serializable) studentlist);
                    intent.setClass(Info_Save.this, Info_List.class);
                    startActivity(intent);
                break;
            case R.id.et_date:
                DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        et_date.setText(year + "-" + (++month) + "-" + dayOfMonth);
                    }
                };
                getDate();
                DatePickerDialog datePickerDialog = new DatePickerDialog(Info_Save.this, listener, y, m, d);
                datePickerDialog.show();
                break;
        }
    }
    private void initSpinner() {
        String[] arr = {"计算机科学与工程学院", "土木学院", "艺术学院", "人文学院", "建筑与艺术学院", "选择学院"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arr);
        academyspinner.setAdapter(arrayAdapter);
        academyspinner.setSelection(arr.length - 1, true);
    }
    @Override
    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
    }
    private void getDate() {
        cal = Calendar.getInstance();
        y = cal.get(Calendar.YEAR);
        m = cal.get(Calendar.MONTH);
        d = cal.get(Calendar.DAY_OF_MONTH);
    }
    private void test() {
        MySQLiteAccess mySQLiteAccess = new MySQLiteAccess(this, 1);
        mySQLiteAccess.getReadableDatabase();
    }
}
