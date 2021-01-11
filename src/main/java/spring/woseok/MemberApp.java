package spring.woseok;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.woseok.member.Grade;
import spring.woseok.member.Member;
import spring.woseok.member.MemberService;
import spring.woseok.member.MemberServiceImpl;
import spring.woseok.order.OrderService;

import java.util.Optional;
////
public class MemberApp {
    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
//        OrderService orderService = appConfig.orderService();

        //  스프링 컨테이너 생성 !!!
        // 스프링 컨테이너를 생성하면 컨테이너 안에 스프링 bean 저장소가 생성된다
        // 스프링 Bean 저장소는 {bean이름 : bean객체} 모양으로 {key : value} 형태로 이루어진다.
        // 스프링 컨테이너 생성시 넣어준 매개변수(AppConfig.class) 를 보고 스프링 beam 저장소를 구성한다.
        // 스프링 컨테이너가 Bean 저장소를 구성할때 AppConfig.class 를 참고해서 의존관계(DI) 를 주입한다.(객체끼리 필요한 정보들이 다 주입됨)
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member memberA = new Member(1L, "memberA", Grade.BASIC);

        memberService.join(memberA);
        Optional<Member> findMember = memberService.findMember(1l);

        System.out.println("memberA = " + memberA.getName());
        System.out.println("findMember = " + findMember.get().getName());

    }
}
