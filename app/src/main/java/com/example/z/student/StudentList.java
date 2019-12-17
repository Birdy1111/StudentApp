package com.example.z.student;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.NamespaceContext;

public class StudentList extends AppCompatActivity implements View.OnClickListener,
        AdapterView.OnItemClickListener, StudentAdapter.InnerItemOnClickListener {

    private Button sl_add;
    private Button sl_search;
    private Button menu;
    private EditText search_id;
    DBop dbop = new DBop();

    List<Map<String, Object>> stuMap = new ArrayList<Map<String, Object>>();
    List<String> names = new ArrayList<String>();

    List<String> classes = new ArrayList<String>();
    List<String> design = new ArrayList<String>();
    List<String> assembly = new ArrayList<String>();
    List<String> data = new ArrayList<String>();
    List<String> software = new ArrayList<String>();
    ListView studentlist;
    private List<StuInfo> list = new ArrayList<StuInfo>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);


        studentlist = (ListView) findViewById(R.id.studentlist);
        sl_add = (Button) findViewById(R.id.sl_add);
        sl_search = (Button) findViewById(R.id.sl_search);
        menu = (Button) findViewById(R.id.menu);
        search_id=(EditText)findViewById(R.id.search_id);

        sl_add.setOnClickListener(this);
        sl_search.setOnClickListener(this);
        menu.setOnClickListener(this);

        studentlist.setOnItemClickListener(this);


        dbop.test(this);
        list = dbop.queryAll();
        if (!list.isEmpty()) {
            showList(list);
        }
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.sl_add:
                intent.setClass(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.menu:
                intent.setClass(this, MenuActivity.class);
                startActivity(intent);
                break;
            case R.id.sl_search:
                String search = search_id.getText().toString();
                if (search.isEmpty()) {
                    Toast.makeText(StudentList.this, "请先添加查询内容", Toast.LENGTH_SHORT).show();
                    return;
                }
                    intent.setClass(this, searchActivity.class);
                    intent.putExtra("searchKey", search);
                    startActivity(intent);
                break;
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        ListView list = (ListView) parent;
        StuInfo s = (StuInfo) list.getItemAtPosition(position);
    }

    @Override
    public void itemClick(View view) {

        int position = (int) view.getTag();
        Intent intent = new Intent();
        switch (view.getId()) {

        }
    }

    private void showList(List<StuInfo> stus) {
        StudentAdapter studentAdapter = new StudentAdapter(this, stus);
        studentlist.setAdapter(studentAdapter);
    }
}
