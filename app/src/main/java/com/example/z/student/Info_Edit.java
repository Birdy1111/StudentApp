package com.example.z.student;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class Info_Edit extends AppCompatActivity implements View.OnClickListener, TimePicker.OnTimeChangedListener {

    Button bt_add;
    EditText et_name;
    AutoCompleteTextView et_major;
    EditText et_age;
    RadioGroup rg_sex;
    Spinner academyspinner;
    EditText et_date;
    private Calendar cal;
    private int y, m, d;
    RadioButton rg_nan;
    RadioButton rg_nv;
    IBop dBop = new IBop();
    Info altStu = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_edit);
        Bundle bundle = getIntent().getExtras();

        dBop.test(this);
        bt_add = (Button) findViewById(R.id.bt_add);
        academyspinner = (Spinner) findViewById(R.id.academy);
        et_date = (EditText) findViewById(R.id.et_date);
        rg_nan = (RadioButton) findViewById(R.id.rb_nan);
        rg_nv = (RadioButton) findViewById(R.id.rb_nv);

        et_name = (EditText) findViewById(R.id.et_name);
        et_major = (AutoCompleteTextView) findViewById(R.id.et_major);
        et_age = (EditText) findViewById(R.id.et_age);
        rg_sex = (RadioGroup) findViewById(R.id.rg_sex);
        bt_add.setOnClickListener(this);
        et_date.setKeyListener(null);
        et_date.setOnClickListener(this);
        initEditText();
    }


    private void initEditText() {
        Bundle bundle = getIntent().getExtras();
        altStu = (Info) bundle.get("altStu");
        if (altStu != null) {//还原数据
            et_name.setText(altStu.getName());
            et_age.setText(String.valueOf(altStu.getAge()));
            et_date.setText(altStu.getDate());
            et_major.setText(altStu.getMajor());
            // System.out.println(altStu.getMajor());
            String aca = altStu.getAcademy();

            System.out.println("学院信息是:" + aca);
            initSpinner(aca);

            String sex = altStu.getSex();
            if (sex.toString().equals("男")) {
                rg_nan.setChecked(true);
            } else if (sex.toString().equals("女")) {
                rg_nv.setChecked(true);
            }
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_add:
                Intent intent = new Intent();
                String name = et_name.getText().toString();
                String sex = getCheckedRadioInfo(rg_sex);
                String age = et_age.getText().toString();
                String major = et_major.getText().toString();
                String aca = academyspinner.getSelectedItem().toString();
                String date = et_date.getText().toString();


                if (altStu != null) {
                    altStu.setName(name);
                    altStu.setSex(sex);
                    altStu.setAge(age);
                    altStu.setMajor(major);
                    altStu.setDate(date);
                    altStu.setAcademy(aca);
                    dBop.update(altStu);
                    //dBop.onUpgrade(altStu);
                }
                intent.setClass(this, Info_List.class);
                startActivity(intent);
                finish();
                break;
            //选择日期
            case R.id.et_date:
                DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        et_date.setText(year + "-" + (++month) + "-" + dayOfMonth);
                    }
                };
                getDate();
                DatePickerDialog datePickerDialog = new DatePickerDialog(Info_Edit.this, listener, y, m, d);
                datePickerDialog.show();
                break;
        }
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


    private void initSpinner(String aca) {
        String[] arr = {"计算机科学与工程学院", "土木学院", "艺术学院", "人文学院", "建筑与艺术学院"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arr);
        academyspinner.setAdapter(arrayAdapter);
        for (int i = 0; i < arr.length ; i++) {

            if(arr[i].equals(aca))
            {
                academyspinner.setSelection(i);
            }
        }

    }


    private String getCheckedRadioInfo(RadioGroup radioGroup) {
        String sex = "";
        int num = radioGroup.getChildCount();
        for (int i = 0; i < num; i++) {
            RadioButton rd = (RadioButton) radioGroup.getChildAt(i);
            if (rd.isChecked()) {
                sex = rd.getText().toString();
                break;
            }
        }
        return sex;
    }
}


