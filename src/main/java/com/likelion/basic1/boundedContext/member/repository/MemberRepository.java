package com.likelion.basic1.boundedContext.member.repository;

import com.likelion.basic1.boundedContext.member.entity.Member;

import java.util.ArrayList;
import java.util.List;

public class MemberRepository {

    private List<Member> members;

    public MemberRepository() {
        this.members = new ArrayList<>();

        members.add(new Member("user1", "1234"));
        members.add(new Member("abc", "12345"));
        members.add(new Member("peace", "123413"));
    }

    public Member findByUserName(String username) {
        return members.stream()
                .filter(m -> m.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }
}
