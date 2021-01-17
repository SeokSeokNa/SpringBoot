package spring.woseok.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring.woseok.discount.DiscountPolicy;
import spring.woseok.discount.FixDiscountPolicy;
import spring.woseok.discount.RateDiscountPolicy;
import spring.woseok.member.Member;
import spring.woseok.member.MemberRepository;
import spring.woseok.member.MemoryMemberRepository;

import java.util.Optional;
@Component
public class OrderServiceImpl implements OrderService {

    
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;;

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findByid(memberId).get();
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId,itemName,itemPrice,discountPrice);
    }

    // 테스트 용도(스프링이 같은객체 쓰는지)
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }

}
