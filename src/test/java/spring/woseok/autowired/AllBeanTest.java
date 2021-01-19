package spring.woseok.autowired;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.woseok.AutoAppConfig;
import spring.woseok.discount.DiscountPolicy;
import spring.woseok.member.Grade;
import spring.woseok.member.Member;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class AllBeanTest {

    @Test
    void findAllBean() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);
        DiscountService discountService = ac.getBean(DiscountService.class);
        Member member = new Member(1L, "userA", Grade.VIP);
        int discountPrice = discountService.discount(member,10000,"fixDiscountPolicy");
        int discountPrice2 = discountService.discount(member,20000,"rateDiscountPolicy");

        assertThat(discountService).isInstanceOf(DiscountService.class);
        assertThat(discountPrice).isEqualTo(1000);
        assertThat(discountPrice2).isEqualTo(2000);
    }

    static class DiscountService{
        // 같은타입의 Bean 객체들을 자동주입 받게된다 !!
        private final Map<String , DiscountPolicy> policyMap; // Map은 키,값 으로 이루어지므로 , Bean객체가 될 클래스이름의 첫글자를 소문자로 변경해서 키로 가진다
        private final List<DiscountPolicy> policyList;

        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policyList) {
            this.policyMap = policyMap;
            this.policyList = policyList;
            System.out.println("policyMap = " + policyMap);
            System.out.println("policyList = " + policyList);
        }

        public int discount(Member member , int price, String discountCode) {
            DiscountPolicy discountPolicy = policyMap.get(discountCode); // 여러개의 같은타입을 가진 Bean객체들을 조회해서 "discountCode"를 키값으로 가진 Bean 객체를 가져온다
            return discountPolicy.discount(member, price);
        }
    }
}
