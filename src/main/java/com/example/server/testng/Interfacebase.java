package com.example.server.testng;

import com.example.server.pojo.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;


public class Interfacebase {
    private static Logger logger = LoggerFactory.getLogger(Interfacebase.class);

    public Person createbase(){
        Person person = new Person();
        person.setName("张三");
        person.setAge(30);

        return person;
    }
    public List shuju(){
        List<Person> list = new ArrayList<>();
        int b=10;
        for (int a=0;a<2;a++){
            Person person = new Person();
            person.setName("张三");
            person.setAge(b);
            b=b+100;
            list.add(person);
        }
        return list;
    }


    @Test
    public void baseGroup(){
        List shu = shuju();
//        Object[] objects = shu.toArray();
//        for (int a=0;a<objects.length;a++){
//            Person person= (Person) objects[a];
//            System.out.println(person.getName()+"..."+person.getAge());
//        }
        
        for (int a=0;a<shu.size();a++){
            Person person = (Person) shu.get(a);
            System.out.println(person.getName()+"..."+person.getAge());

        }

    }



}
