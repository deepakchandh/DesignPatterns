package com.java.designpatterns.creational.prototype;

// generally used to avoid unnecessary creation of new objects

import java.util.ArrayList;
import java.util.List;

class Employees implements Cloneable{
    private List<String> empList;

    public Employees(){
        empList = new ArrayList<>();
    }

    public Employees(List<String> list){
        this.empList=list;
    }
    public void loadData(){
        //read all employees from database and put into the list
        empList.add("Pankaj");
        empList.add("Raj");
        empList.add("David");
        empList.add("Lisa");
    }

    public List<String> getEmpList() {
        return empList;
    }


    // the concept of creating an existing object instead of creating them newly.
    @Override
    public Object clone() throws CloneNotSupportedException{
        List<String> temp =  new ArrayList<>();
        for(String str: this.getEmpList()){
            temp.add(str);
        }
        return new Employees(temp);
    }
}

public class PrototypePatternTest {

    public static void main(String[] args) throws CloneNotSupportedException {
        Employees emp = new Employees();
        emp.loadData();
        Employees empNew = (Employees) emp.clone();
        Employees empNew1 = (Employees) emp.clone();
        List<String> list = empNew.getEmpList();
        list.add("John");
        List<String> list1 = empNew1.getEmpList();
        list1.remove("Pankaj");
        System.out.println("emps List: "+emp.getEmpList());
        System.out.println("empsNew List: "+list);
        System.out.println("empsNew1 List: "+list1);
    }
}
