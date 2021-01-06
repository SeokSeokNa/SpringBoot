package spring.woseok;

import spring.woseok.member.Grade;
import spring.woseok.member.Member;
import spring.woseok.member.MemberServiceImpl;
import spring.woseok.order.Order;
import spring.woseok.order.OrderServiceImpl;

public class DiscountApp {

    public static void main(String[] args) {
        MemberServiceImpl memberService = new MemberServiceImpl();
        OrderServiceImpl orderService = new OrderServiceImpl();

        Long memberId =1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);

        System.out.println("order = " + order);

    }
}
