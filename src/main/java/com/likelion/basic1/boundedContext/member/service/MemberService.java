package com.likelion.basic1.boundedContext.member.service;

import com.likelion.basic1.base.rsData.RsData;
import com.likelion.basic1.boundedContext.member.entity.Member;
import com.likelion.basic1.boundedContext.member.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public RsData tryLogin(String username, String password) {
        Member member = memberRepository.findByUserName(username);

        if (member == null) {
            return RsData.of("F-2", "%s(은)는 존재하지 않는 회원입니다.".formatted(username));
        } else if (!member.getPassword().equals(password)) {
            return RsData.of("F-2", "비밀번호가 일치하지 않습니다.");
        }
        return RsData.of("S-1", "%s님 환영합니다.".formatted(username), member);
    }

    public Member findByUsername(String username) {
        return memberRepository.findByUserName(username);
    }

    public Member findById(long id) {
        return memberRepository.findById(id);
    }
}
