package com.example.z.student;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;


import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.math.MathContext;
import java.util.List;

public class StudentAdapter extends BaseAdapter implements View.OnClickListener{
    private List<StuInfo> stuDates;//数据源
    private Context stuContext;//布局加载器
    private InnerItemOnClickListener innerItemOnClickListener;
    DBop dbOperate=new DBop();
    public StudentAdapter(Context context,List list) {
        this.stuDates=list;
        this.stuContext=context;
        dbOperate.test(context);
    }
    @Override
    public int getCount() {
        return stuDates.size();
    }
    //返回position对应位置的数据
    @Override
    public Object getItem(int position) {
        return stuDates.get(position);
    }
    //返回position对应位置item的id
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       View stuView=View.inflate(stuContext,R.layout.list,null); //加载学生成绩列表的布局器
       TextView tv_id=stuView.findViewById(R.id.li_id);
       TextView tv_name=stuView.findViewById(R.id.li_name);

        TextView tv_design=stuView.findViewById(R.id.li_design);
        TextView tv_assembly=stuView.findViewById(R.id.li_assembly);
        TextView tv_data=stuView.findViewById(R.id.li_data);
        TextView tv_software = stuView.findViewById(R.id.li_software);

        ImageView iv_edit=stuView.findViewById(R.id.li_edit);
        ImageView iv_delete=stuView.findViewById(R.id.li_delete);

       final StuInfo student=(StuInfo) stuDates.get(position);//得到当前的位置

        tv_id.setText(String.valueOf(student.getId()));
        tv_name.setText(String.valueOf(student.getName()));

        tv_design.setText(String.valueOf(student.getDesign()));
        tv_assembly.setText(String.valueOf(student.getAssembly()));
        tv_data.setText(String.valueOf(student.getData()));
        tv_software.setText(String.valueOf(student.getSoftware()));

        iv_edit.setImageResource(R.drawable.edit);
        iv_delete.setImageResource(R.drawable.delete);

        //增加监听

        iv_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Context context=v.getContext();
                Intent intent=new Intent();
                intent.setClass(context,Edit.class);
                intent.putExtra("altStu",student);
                ((Activity)context).startActivity(intent);
            }
        });


        iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Context context=v.getContext();
                AlertDialog.Builder dialog = new AlertDialog.Builder (context);
                dialog.setTitle ("温馨提示").setMessage ("你确定要删除该学生成绩吗？");

                dialog.setPositiveButton ("确定", new DialogInterface.OnClickListener () {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dbOperate.delete(student.getId());
                        stuDates.remove(student);
                        StudentAdapter.this.notifyDataSetChanged();
                    }
                });
                //如果取消，就什么都不做，关闭对话框
                dialog.setNegativeButton ("取消",null);
                dialog.show ();

            }
        });

        //给删除和编辑设置标志
        iv_edit.setTag(position);
        iv_delete.setTag(position);
        return stuView;
    }


    //创建内部控件监听接口
    interface  InnerItemOnClickListener{
        abstract  void itemClick(View view);
    }

    public void setOnInnerOnClickListenner(InnerItemOnClickListener listener)
    {
        this.innerItemOnClickListener=listener;
    }
    @Override
    public void onClick(View v) {
        innerItemOnClickListener.itemClick(v);
    }


}
