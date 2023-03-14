package com.likelion.basic1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    int count = 0;
    int id = 1;

    People p = new People();


    //컴퓨터가 이해할 수 있는 주석
    // 개발자가 스트링부트에게 말함
    //아래 메서드를 실행한 후 그 리턴값을 응답으로 삼아줘!
    @GetMapping("/home/main")
    @ResponseBody
    public String showMain() {
        return "안녕하세요";
    }

    @GetMapping("/home/main2")
    @ResponseBody
    public String showMain2() {
        return "반갑습니다 !";
    }

    @GetMapping("/home/main3")
    @ResponseBody
    public String showMain3() {
        return "수고하세요~";
    }

    @GetMapping("/home/main4")
    @ResponseBody
    public String showMain4() {
        return "응답 : " + count++;
    }

    @GetMapping("/home/main5")
    @ResponseBody
    public int showMain5(@RequestParam(defaultValue = "0") int a, @RequestParam int b) {
        return a + b;
    }

    @GetMapping("/home/addPerson")
    @ResponseBody
    public String addPerson(@RequestParam(defaultValue = "0") String name, @RequestParam(defaultValue = "0") int age) {
        p.add(name, age);
        return id++ + "번 사람이 추가되었습니다.";
    }

    @GetMapping("/home/addPerson")
    @ResponseBody
    public String removePerson(@RequestParam(defaultValue = "0") String name, @RequestParam(defaultValue = "0") int age) {
        p.add(name, age);
        return id++ + "번 사람이 추가되었습니다.";
    }

    @GetMapping("/home/people")
    @ResponseBody
    public String showPeople() {
        return p.toString();
    }
}

class People {
    List<Person> list = new ArrayList<>();
    int id = 1;

    public void add(String name, int age) {
        list.add(new Person(this.id++, name, age));

    }

    @Override
    public String toString() {
        return "People{" +
                "list=" + list +
                '}' + "\n";
    }
}


class Person {
    String name;
    int age;
    int id;

    public Person(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", id=" + id +
                '}';
    }
}
