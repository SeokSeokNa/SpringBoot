package spring.woseok.discount;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import spring.woseok.member.Grade;
import spring.woseok.member.Member;

import static org.assertj.core.api.Assertions.*;

class RateDiscountPolicyTest {
    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();
//  vip 이므로 할인 금액이 제품금액의 10%가 적용되어야 성공
    @Test
    @DisplayName("VIP는 10% 할인이 적용되어야 한다.")
    void vip_o() {
        //given
        Member member = new Member(1L, "memberVip", Grade.VIP);
        //when
        int discount = discountPolicy.discount(member, 10000);
        //then
        assertThat(discount).isEqualTo(1000);
    }
    
//    vip가 아니므로 할인 금액이 0원이 나와야 성공
    @Test
    @DisplayName("VIP가 아니면 할인이 적용되지 않아야 한다.")
    void vip_x() {
        //given
        Member member = new Member(2L, "memberVip", Grade.BASIC);
        //when
        int discount = discountPolicy.discount(member, 10000);
        //thenS
        assertThat(discount).isEqualTo(0);
    }
}