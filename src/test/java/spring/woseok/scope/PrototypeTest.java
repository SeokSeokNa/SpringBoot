package spring.woseok.scope;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.assertThat;

public class PrototypeTest {

    //프로토타입 Bean은 스프링 컨테이너가 생성되고 나서 Bean을 조회할때 생성이된다 !
    // 프로토타입 Bean은 싱글톤 Bean 과 다르게 클라이언트가 요청할때마다 새로운 객체를 생성한다 !
    // 프로토 타입 Bean은 스프링 컨테이너가 생성과 DI(의존관계 주입) 만 해주고 더이상 관리하지 않게된다 , 즉 클라이언트 한테 넘겨버리고 스프링 컨테이너는 가지고 있지않음
    // 그렇기 때문에 close() 메소드를 실행해도 Destory() 가 되지 않는다!
    // 종료 메소드가 필요할 경우 클라이언트가 직접 호출해서 써야함
    @Test
    void prototypeBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        System.out.println("find prototypeBean1");
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        System.out.println("find prototypeBean2");
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        System.out.println("prototypeBean2 = " + prototypeBean1);
        System.out.println("prototypeBean2 = " + prototypeBean2);
        assertThat(prototypeBean1).isNotSameAs(prototypeBean2);

        ac.close();
    }

    @Scope("prototype")
    static class PrototypeBean {
        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init");
        }

        @PreDestroy
        public void destory() {
            System.out.println("PrototypeBean.destory");
        }
    }
}
