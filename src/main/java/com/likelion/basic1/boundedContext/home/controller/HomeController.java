package com.likelion.basic1.boundedContext.home.controller;

import com.likelion.basic1.boundedContext.member.service.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.*;


@Controller
public class HomeController {
    private int count;

    private List<Person> people;


    private MemberService memberService;

    @Autowired
    public HomeController(MemberService memberService) {
        count = 0;
        people = new ArrayList<>();
        this.memberService = memberService;
    }

    @GetMapping("/home/returnMap")
    @ResponseBody
    public Map<String, Object> showReturnMap() {
        Map<String, Object> map = new LinkedHashMap<>() {{
            put("id", 1);
            put("speed", 100);
            put("age", 100);
        }};
        return map;
    }

    @GetMapping("/home/addPerson")
    @ResponseBody
    public String addPerson(String name, int age) {
        Person p = new Person(name, age);

        people.add(p);

        return "%d번 사람이 추가되었습니다.".formatted(p.getId());
    }

    @GetMapping("/home/people")
    @ResponseBody
    public List<Person> showPeople() {
        return people;
    }

    @GetMapping("/home/removePerson")
    @ResponseBody
    public String removePerson(int id) {
        boolean removed = people.removeIf(person -> person.getId() == id);

        if (removed == false) {
            return "%d번 사람이 존재하지 않습니다.".formatted(id);
        }

        return "%d번 사람이 삭제되었습니다.".formatted(id);
    }

    @GetMapping("/home/modifyPerson")
    @ResponseBody
    public String modifyPerson(int id, String name, int age) {
        Person toModify = people.stream()
                .filter(e -> e.getId() == id)
                .findAny()
                .orElse(null);
        if (toModify == null) {
            return "%d번 사람이 존재하지 않습니다.".formatted(id);
        }

        toModify.setAge(age);
        toModify.setName(name);

        return "%d번 사람이 수정되었습니다.".formatted(id);
    }

    @GetMapping("/home/reqAndResp")
    @ResponseBody
    public void showReqAndResp(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int age = Integer.parseInt(req.getParameter("age").replaceAll(" ", ""));
        resp.getWriter().append("Hello, you are %d years old".formatted(age));
    }

    @GetMapping("/home/cookie/increase")
    @ResponseBody
    public int showCookieIncrease(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int countInCookie = 0;

        if (req.getCookies() != null) {
            countInCookie = Arrays.stream(req.getCookies())
                    .filter(cookie -> cookie.getName().equals("count"))
                    .map(Cookie::getValue)
                    .mapToInt(Integer::parseInt)
                    .findFirst()
                    .orElse(0);

        }

        int newCountInCookie = countInCookie + 1;

        resp.addCookie(new Cookie("count", newCountInCookie + ""));
        return newCountInCookie;
    }


}

@AllArgsConstructor
@Getter
@ToString
class Person {
    private static int lastId;
    private final int id;
    @Setter
    private int age;
    @Setter
    private String name;

    static {
        lastId = 0;
    }

    public Person(String name, int age) {
        this(++lastId, age, name);
    }

}


//@AllArgsConstructor
//@Getter
//class CarV2 {
//    private final int id;
//    private final int speed;
//    private final String name;
//    private final int[] relatedIds;
//}
//
//class Car {
//    private final int id;
//    private final int speed;
//    private final String name;
//    private final int[] relatedIds;
//
//    public Car(int id, int speed, String name, int[] relatedIds) {
//        this.id = id;
//        this.speed = speed;
//        this.name = name;
//        this.relatedIds = relatedIds;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public int getSpeed() {
//        return speed;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public int[] getRelatedIds() {
//        return relatedIds;
//    }
//}


//    @GetMapping("/home/returnClassObj")
//    @ResponseBody
//    public Car showReturnClassObj() {
//        Car car = new Car(1, 100, "ferarri", new int[]{ 1, 2, 3 });
//
//        return car;
//    }
//
//    @GetMapping("/home/returnClassObj/v2")
//    @ResponseBody
//    public CarV2 showReturnClassObjv2() {
//        CarV2 carv2 = new CarV2(1, 100, "porsche", new int[]{ 1, 2, 3 });
//
//        return carv2;
//    }