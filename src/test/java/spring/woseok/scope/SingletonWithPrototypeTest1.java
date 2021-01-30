package spring.woseok.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

import static org.assertj.core.api.Assertions.*;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        assertThat(prototypeBean2.getCount()).isEqualTo(1);

    }

    @Test
    void singletonClientUsePrototype() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class,PrototypeBean.class);
        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);

       
        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(1);
    }

    @Scope("singleton")
    static class ClientBean {
        // 이미 스프링이 올라올때부터 프로토타입 빈을 클라이언트 빈에게 자동 주입하고 관리를 끝냄
        // 그래서 클라이언트 빈.logic을 사용하면 계속 같은 프로토타입을 보고 카운트를 증가시킨다.
        // 프로토타입빈 을 사용하는 이유는 계속해서 새로운 객체가 필요할 경우에 사용하는데
        // 이건 싱글톤빈을 사용하듯이 사용되므로 조심해야함 ..
//        private final PrototypeBean prototypeBean;
//
//        @Autowired  // 생성자가 하나이므로 생략 가능
//        public ClientBean(PrototypeBean prototypeBean) {
//            this.prototypeBean = prototypeBean;
//        }

//       이건 싱글톤 빈과 프로토타입 을 같이 쓸경우 생기는 문제점을 해결하는 방안
        @Autowired
//        private ObjectProvider<PrototypeBean> prototypeBeanProvider;
        private Provider<PrototypeBean> prototypeBeanProvider;
        public int logic() {
//            prototypeBean.addCount();
//            return prototypeBean.getCount();

            //스프링 컨테이너에게 프로토타입 빈을 요청하는 코드 ( 스프링 컨테이너는 이걸 보고 매번 새로운 프로토타입 빈을 생성해서 던져줌)
            //이런걸 Dependency Lookup(DL) 이라고 한다
            // DL: 의존관계를 주입받는게(DI) 아니라  의존관계룰 탐색하는것
            // 내가 스프링 컨테이너에게 직접 getBean으로 찾는게 아니라 애를 통해서 대신 찾아주라고 할때 쓰는것
            // 내가 직접 호출 아니고 ObjectProvider가 스프링 컨테이너에게 호출해서 받아서 내게 주는것
//            PrototypeBean prototypeBean = prototypeBeanProvider.getObject();

            PrototypeBean prototypeBean = prototypeBeanProvider.get();
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }

    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init" + this);
        }
        
        // 호출 안됌
        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destory");
        }
    }
}
