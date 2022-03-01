package com.example.server.controller;

import com.example.server.pojo.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Httpcontroller {

    @GetMapping("/httpgettt")
    public Person httpgett() {
        Person person = new Person();
        person.setName("aaa");
        person.setAge(1);
        return person;
    }
}