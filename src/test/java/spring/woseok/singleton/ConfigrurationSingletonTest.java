package spring.woseok.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.woseok.AppConfig;
import spring.woseok.member.MemberRepository;
import spring.woseok.member.MemberServiceImpl;
import spring.woseok.order.OrderServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;

public class ConfigrurationSingletonTest {
    //  과연 이순서로 호출될지 ...
    //call AppConfig.memberService
    //call AppConfig.memberRepository
    //call AppConfig.orderService
    //call AppConfig.memberRepository
    //call AppConfig.memberRepository
    // 이상하게 각각 빈객체 하나씩만 호출된다 ..?
    @Test
    void configurationTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        //3개의 객체 모드 같은객체라는걸 알수있음 ( 어떻게 그럴수있나..? 매번  new 해서 생성하는데..)
        System.out.println("memberSerivce -> memberRepository = " + memberRepository1);
        System.out.println("orderSerivce -> memberRepository = " + memberRepository2);
        System.out.println("memberRepository = " + memberRepository);

        assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);

        // AppConfig를 상속받아 바이트코드를 조작해서 생성된 임의의 클래스로 인해 싱글톤이 보장된다 !! 그래서 하나씩만 호출되는것!!
        // 단ㅡ AppConfig에 @Configuration 어노테이션을 적어놔야 스프링이 이 임의의 클래스를 생성할 수 있다!!...
        // 밑에 테스트를 봐보자~
    }

    
    @Test
    @DisplayName("CGLIB를 이용한 임의의 AppConfig 빈 객체 찍어보기")
    void configrationDeep() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);

        System.out.println("bean.getClass() = " + bean.getClass());

        /* @Configuration이 있으면 내가 만든 클래스가 아니라
          스프링이 내가 만든 "AppConfig" 클래스를 상속받아서 "CGLIB" 라는 바이트코드를 조작할 수 있는 라이브러리를 사용해서
          임의의 다른 클래스를 만들고 그 클래스를 스프링 빈으로 등록한것이 출력된다

          이 임의의 클래스가 모든 Bean 객체에 대해 싱글톤을 보장 해준다
          안에 로직은 복잡하게 되어있지만 대충 이런식으로 되어있다고한다.
          if(memoryMemberRepository가 이미 스프링 컨테이너에 등록되어있으면?) {
            return 스프링 컨테이너에서 찾아서 반환;
          } else {
            기존로직(AppConfig에 있는 memberRepository() 메소드) 을 호출해서  memoryMemberRepository를 생성하고 스프링 컨테이너에 등록후
            return 반환;
         }
       */
    }

}
