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
//DIP : 프로그래머는 추상화에만 의존해야지 구체화까지 의존하지 말아야한다. (그래야 여기 AppConfig 같은 곳에서 쉽게 갈아 끼울수 있음 , 클래스 외부에서 넣어준다는 느낌)
//OCP : 확장에는 열려있으나 변경에는 닫혀있어야 한다. ( 예를들어 새로운 할인정책 구현체를 만들면 원래 할인정책 구현체를 사용하던 모든 코드를 바꿔야한다..)
//      (그러면 힘드니 애초에 구현체에 의존하지 않고 , 추상클래스에만 의존해서 코드변경없이 그대로 갈 수 있게 !!)
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
        return new RateDiscountPolicy();
    } //DIP , OCP 를 지키며 새로운 할인정책을 적용

}
