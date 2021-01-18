package spring.woseok.discount;

import org.springframework.stereotype.Component;
import spring.woseok.annotation.MainDiscountPolicy;
import spring.woseok.member.Grade;
import spring.woseok.member.Member;

@Component
@MainDiscountPolicy //내가 직접만든 Qualifier 어노테이션 !!!
public class RateDiscountPolicy implements DiscountPolicy {

    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        } else {
            return 0;
        }
    }
}
