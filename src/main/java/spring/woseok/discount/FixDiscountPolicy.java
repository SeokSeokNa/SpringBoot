package spring.woseok.discount;

import org.springframework.stereotype.Component;
import spring.woseok.member.Grade;
import spring.woseok.member.Member;

@Component
public class FixDiscountPolicy implements DiscountPolicy {

    private  int discountFixAmount = 1000; // vip는 천원 무적권 할인


    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return discountFixAmount;
        } else {
            return 0;
        }

    }
}
