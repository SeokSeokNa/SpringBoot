package spring.woseok;

import spring.woseok.discount.DiscountPolicy;
import spring.woseok.discount.FixDiscountPolicy;
import spring.woseok.discount.RateDiscountPolicy;
import spring.woseok.member.MemberRepository;
import spring.woseok.member.MemberService;
import spring.woseok.member.MemberServiceImpl;
import spring.woseok.member.MemoryMemberRepository;
import spring.woseok.order.OrderService;
import spring.woseok.order.OrderServiceImpl;

//DIP 를 지키기 위해 AppConfig에서 구현체를 주입해준다 !!!
//각 인터페이스들이 배우라면 배역을 정하는건 AppConfig
// 이로서 각 인터페이스(배우)들은 상대역이 누구든간에 자기 할일만 시키면 되는 형태가 됨!!
public class AppConfig {
    // 지금 과 같이 나타내면 어떤 서비스를 쓰며 어떤 구현객체가 필요한지 명확하게 보이고
    // 중복을 제거 할 수있다 ( memberService, orderService 둘다 MemoryMemberRepository() 가  필요한데 하나의 메소드로 나타내서 new 해서 안만들어도 되고 중복제거)
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public DiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
    }

}
