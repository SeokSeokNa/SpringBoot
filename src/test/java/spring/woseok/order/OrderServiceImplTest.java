package spring.woseok.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import spring.woseok.discount.FixDiscountPolicy;
import spring.woseok.member.Grade;
import spring.woseok.member.Member;
import spring.woseok.member.MemberRepository;
import spring.woseok.member.MemoryMemberRepository;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {

    @Test
    void createOrder() {
        MemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L,"name", Grade.VIP));
        OrderServiceImpl orderService = new OrderServiceImpl(memberRepository , new FixDiscountPolicy());
        Order order = orderService.createOrder(1L, "itemA", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}