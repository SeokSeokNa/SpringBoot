package spring.woseok.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();
    }

    @Configuration
    static class LifeCycleConfig{
        //스프링에 의존하지 않고 생명주기를 다루는 메소드를 설정할수있다!!
        // 외부 라이브러리 에도 쓰일수 있는 방법!!
        // destroyMethod의 경우 추론기능이 있는데  보통 외부 라이브러리의 종료 메소드 이름이 close , shutdown 인데
        // 이 경우 destroyMethod 에 따로 명시해주지 않아도 종료 메소드를 알아서 추론해서 적용시킨다!!
        // ex) @Bean(initMethod = "init" , destroyMethod = "")


        //@Bean(initMethod = "init" , destroyMethod = "close") // 직접 생명주기 메소드를 적용한 방식
        
        @Bean  //@PostConstruct , @PreDestroy 를 적용한 방식
               // 굉장히 편하지만 외부라이브러리 에는 사용을 못하는 단점이 있음 ...
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
}
