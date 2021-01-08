package spring.woseok;

import spring.woseok.member.Grade;
import spring.woseok.member.Member;
import spring.woseok.member.MemberService;
import spring.woseok.member.MemberServiceImpl;
import spring.woseok.order.OrderService;

import java.util.Optional;
////
public class MemberApp {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        OrderService orderService = appConfig.orderService();
        Member memberA = new Member(1L, "memberA", Grade.BASIC);

        memberService.join(memberA);
        Optional<Member> findMember = memberService.findMember(1l);

        System.out.println("memberA = " + memberA.getName());
        System.out.println("findMember = " + findMember.get().getName());

    }
}
