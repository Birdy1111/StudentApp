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

public class Edit extends AppCompatActivity implements View.OnClickListener, TimePicker.OnTimeChangedListener {

    Button bt_add;
    EditText et_name;
    Spinner classesspinner;
    EditText et_design;
    EditText et_assembly;
    EditText et_data;
    EditText et_software;
    DBop dBop = new DBop();
    StuInfo altStu = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Bundle bundle = getIntent().getExtras();

        dBop.test(this);
        bt_add = (Button) findViewById(R.id.bt_add);
        classesspinner = (Spinner) findViewById(R.id.classes);
        et_name = (EditText) findViewById(R.id.et_name);
        et_assembly=findViewById(R.id.et_assembly);
        et_design = findViewById(R.id.et_design);
        et_data = findViewById(R.id.et_data);
        et_software= findViewById(R.id.et_software);
        bt_add.setOnClickListener(this);
        initEditText();
    }


    private void initEditText() {
        Bundle bundle = getIntent().getExtras();
        altStu = (StuInfo) bundle.get("altStu");
        if (altStu != null) {//还原数据
            et_name.setText(altStu.getName());
            String aca = altStu.getClasses();
            initSpinner(aca);

            et_assembly.setText(String.valueOf(altStu.getAssembly()));
            et_design.setText(String.valueOf(altStu.getDesign()));
            et_data.setText(String.valueOf(altStu.getData()));
            et_software.setText(String.valueOf(altStu.getSoftware()));

        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_add:
                Intent intent = new Intent();
                String name = et_name.getText().toString();
                String aca = classesspinner.getSelectedItem().toString();
                int design = Integer.parseInt(et_design.getText().toString());
                int assembly = Integer.parseInt(et_assembly.getText().toString());
                int data = Integer.parseInt(et_data.getText().toString());
                int software =  Integer.parseInt(et_software.getText().toString());
                if (altStu != null) {
                    altStu.setName(name);
                    altStu.setClasses(aca);
                    altStu.setDesign(design);
                    altStu.setAssembly(assembly);
                    altStu.setData(data);
                    altStu.setSoftware(software);
                    dBop.update(altStu);
                }
                intent.setClass(this, StudentList.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    @Override
    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {

    }


    private void initSpinner(String aca) {
        String[] arr = {"计科1班", "计科2班", "计科3班", "软件1班", "软件2班","软件3班"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arr);
        classesspinner.setAdapter(arrayAdapter);
        for (int i = 0; i < arr.length ; i++) {
           if(arr[i].equals(aca))
            {classesspinner.setSelection(i);
            }
        }

    }



}


