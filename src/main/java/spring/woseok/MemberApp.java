package spring.woseok;

import spring.woseok.member.Grade;
import spring.woseok.member.Member;
import spring.woseok.member.MemberService;
import spring.woseok.member.MemberServiceImpl;

import java.util.Optional;
//
public class MemberApp {
    public static void main(String[] args) {
        MemberServiceImpl memberService = new MemberServiceImpl();
        Member memberA = new Member(1L, "memberA", Grade.BASIC);

        memberService.join(memberA);
        Optional<Member> findMember = memberService.findMember(1l);

        System.out.println("memberA = " + memberA.getName());
        System.out.println("findMember = " + findMember.get().getName());

    }
}
