package spring.woseok.order;

import spring.woseok.discount.DiscountPolicy;
import spring.woseok.discount.FixDiscountPolicy;
import spring.woseok.member.Member;
import spring.woseok.member.MemberRepository;
import spring.woseok.member.MemoryMemberRepository;

import java.util.Optional;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy= new FixDiscountPolicy();


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findByid(memberId).get();
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId,itemName,itemPrice,discountPrice);
    }
}
