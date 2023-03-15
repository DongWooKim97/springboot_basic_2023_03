package com.likelion.basic1.boundedContext.member.controller;

import com.likelion.basic1.base.rsData.RsData;
import com.likelion.basic1.boundedContext.member.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MemberController {
    private final MemberService memberService;

    public MemberController() {
        this.memberService = new MemberService();
    }

    @GetMapping("/member/login")
    @ResponseBody
    public RsData login(String username, String password) {

        return memberService.tryLogin(username, password);
    }
}