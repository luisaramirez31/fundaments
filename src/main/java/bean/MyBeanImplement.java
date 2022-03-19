package bean;

import java.sql.SQLOutput;

public class MyBeanImplement implements MyBean
{
    @Override
    public void print() {
        System.out.println("my bean");
    }
}
