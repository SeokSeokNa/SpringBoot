package spring.woseok.order;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring.woseok.annotation.MainDiscountPolicy;
import spring.woseok.discount.DiscountPolicy;
import spring.woseok.discount.FixDiscountPolicy;
import spring.woseok.discount.RateDiscountPolicy;
import spring.woseok.member.Member;
import spring.woseok.member.MemberRepository;
import spring.woseok.member.MemoryMemberRepository;

import java.util.Optional;
@Component
//@RequiredArgsConstructor // final 키워드를 가진 필드를 파라미터로 가지는 생성자를 자동으로 만들어준다 !!!!
public class OrderServiceImpl implements OrderService {


    private  final MemberRepository memberRepository;
    private  final DiscountPolicy discountPolicy;;


    @Autowired // 생성자가 하나 일 경우 생략 가능하다
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
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
