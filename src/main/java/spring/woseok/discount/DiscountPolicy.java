package spring.woseok.discount;

import spring.woseok.member.Member;

public interface DiscountPolicy {
 //////
    /*
    * 할인 대상금액 리턴해주는 메소드
    * */
    int discount(Member member , int price);
}
