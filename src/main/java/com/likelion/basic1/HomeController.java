package com.likelion.basic1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
    int count=0;

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
        return "응답 : "+count++;
    }

    @GetMapping("/home/main5")
    @ResponseBody
    public int showMain5(@RequestParam(defaultValue = "0") int a, @RequestParam int b) {
        return a+b;
    }
}
