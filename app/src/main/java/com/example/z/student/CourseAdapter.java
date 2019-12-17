package com.example.z.student;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CourseAdapter extends BaseAdapter implements View.OnClickListener {
    private List<CourseInfo> couDates;//数据源
    private Context couContext;//布局加载器
    private StudentAdapter.InnerItemOnClickListener innerItemOnClickListener;
    DBop dbOperate=new DBop();
    public CourseAdapter(Context context,List list) {
        this.couDates=list;
        this.couContext=context;
        dbOperate.test(context);
    }
    @Override
    public void onClick(View view) {

    }

    @Override
    public int getCount() {
        return couDates.size();
    }
    //返回position对应位置的数据
    @Override
    public Object getItem(int position) {
        return couDates.get(position);
    }
    //返回position对应位置item的id
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View couView=View.inflate(couContext,R.layout.cou_list,null); //加载学生成绩列表的布局器

        TextView tv_course_12=couView.findViewById(R.id.course_12);
        TextView tv_course_34=couView.findViewById(R.id.course_34);
        TextView tv_course_56=couView.findViewById(R.id.course_56);
        TextView tv_course_78=couView.findViewById(R.id.course_78);
        TextView tv_course_910=couView.findViewById(R.id.course_910);


        ImageView iv_edit=couView.findViewById(R.id.li_edit);

        final CourseInfo course=(CourseInfo)couDates.get(i);//得到当前的位置

       tv_course_12.setText(String.valueOf(course.getCourse_12()));
        tv_course_34.setText(String.valueOf(course.getCourse_34()));
        tv_course_56.setText(String.valueOf(course.getCourse_56()));
        tv_course_78.setText(String.valueOf(course.getCourse_78()));
        tv_course_910.setText(String.valueOf(course.getCourse_910()));

        iv_edit.setImageResource(R.drawable.edit);

        //增加监听

        iv_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            /*    Context context=v.getContext();
                Intent intent=new Intent();
                intent.setClass(context,Edit.class);
                intent.putExtra("altStu",student);
                ((Activity)context).startActivity(intent);*/
            }
        });
        return couView;
    }

    public interface InnerItemOnClickListener {
    }
}
