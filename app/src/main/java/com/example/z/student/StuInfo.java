package com.example.z.student;

import java.io.Serializable;
import java.security.PrivilegedExceptionAction;

public class StuInfo implements Serializable {
    private int id;
    private String name;
    private String classes;
    private int design;
    private int assembly;
    private int data;
    private int software;

    public StuInfo(int a,String b,String c,int d,int e,int f,int g) {
        id = a;
        name = b;
        classes = c;
        design = d;
        assembly = e;
        data = f;
        software = g;
    }
    public StuInfo(String b,String c,int d,int e,int f,int g) {
        name = b;
        classes = c;
        design = d;
        assembly = e;
        data = f;
        software = g;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public int getDesign() {
        return design;
    }

    public void setDesign(int design) {
        this.design = design;
    }

    public int getAssembly() {
        return assembly;
    }

    public void setAssembly(int assembly) {
        this.assembly = assembly;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public int getSoftware() {
        return software;
    }

    public void setSoftware(int software) {
        this.software = software;
    }
}
