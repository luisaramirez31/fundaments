package com.fundamentsproyect.demo.bean;

import jdk.swing.interop.SwingInterOpUtils;

public class MyBeanWithDependencyImplement implements  MyBeanWithDependency
{

    private final MyOperation myOperation;

    public MyBeanWithDependencyImplement(MyOperation myOperation)
    {
        this.myOperation = myOperation;
    }

    @Override
    public void printWithDependency()
    {
        int numero = 1;
        System.out.println(myOperation.sum(numero));
        System.out.println("Hola desde la implementacion de un bean con dependencia");
    }
}
