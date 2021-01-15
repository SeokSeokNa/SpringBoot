package spring.woseok.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.woseok.AppConfig;
import spring.woseok.member.MemberService;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();
        //1. 조회 : 호출할 때 마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();

        //2. 조회 : 호출할 때 마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();

        //참조값이 다른 것을 확인(요청이 올때마다 계속 객체를 생성하게 된다는 뜻.. ,안좋아!!)
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        //memberService1 != memberService2
        assertThat(memberService1).isNotSameAs(memberService2);
    }


    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void SingletonService() {
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);

        //싱글톤 패턴을 적용해서 하나에 객체를 같이 공유하는게 검증됨
        assertThat(singletonService1).isSameAs(singletonService2);
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springCOntainer() {
        //스프링에서 등록한 스프링 컨테이너 객체 생성
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        //1. 조회 : 호출할 때 마다 객체를 생성(스프링 컨테이너에 등록된 같은 Bean객체)
        MemberService memberService1 = ac.getBean("memberService",MemberService.class);

        //2. 조회 : 호출할 때 마다 객체를 생성(스프링 컨테이너에 등록된 같은 Bean객체)
        MemberService memberService2 = ac.getBean("memberService",MemberService.class);

        //참조값이 다른 것을 확인(요청이 올때마다 계속 객체를 생성하게 된다는 뜻.. ,안좋아!!)
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        //memberService1 == memberService2
        // 싱글톤 컨테이너는 각각의 Bean 객체들을 최초에 한번만 생성한다
        // 그 뒤로는 호출할때 같은 Bean 객체를 공유하게 되는거다
        // 스프링은 싱글톤 패턴이 기본적으로 적용되어 있다!
        assertThat(memberService1).isSameAs(memberService2);
    }
}
