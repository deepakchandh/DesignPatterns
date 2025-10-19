package com.java.designpatterns;

import java.util.ArrayList;
import java.util.List;


class Employee implements Cloneable{

    private List<String> empList;

    public Employee(){
        empList = new ArrayList<>();
    }
    public Employee(List<String> empList) {
        this.empList = empList;
    }

    public void loadData(){
        empList.add("sasdas");
        empList.add("raj");
        empList.add("lisa");
    }

    public List<String> getEmpList() {
        return empList;
    }

    @Override
    public Object clone() throws CloneNotSupportedException{
        List<String> temp =  new ArrayList<>();
        for(String str: this.getEmpList()){
            temp.add(str);
        }
        return new Employee(temp);
    }
}

public class Student
{
    public static void main(String[] args)
    {
    }


}
