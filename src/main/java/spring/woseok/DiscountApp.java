package spring.woseok;

import spring.woseok.member.Grade;
import spring.woseok.member.Member;
import spring.woseok.member.MemberService;
import spring.woseok.member.MemberServiceImpl;
import spring.woseok.order.Order;
import spring.woseok.order.OrderService;
import spring.woseok.order.OrderServiceImpl;

public class DiscountApp {

    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        OrderService orderService = appConfig.orderService();

        Long memberId =1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 20000);

        System.out.println("order = " + order);

    }
}
