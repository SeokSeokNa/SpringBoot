package spring.woseok.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    @DisplayName("공유객체 테스트 해보기")
    void statefulServiceSingleton() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean("statefulService", StatefulService.class);
        StatefulService statefulService2 = ac.getBean("statefulService", StatefulService.class);

//        //A사용자 10000원 주문
//        statefulService1.order("userA" , 10000);
//        //B사용자 20000원 주문
//        statefulService2.order("userB" , 20000);
        //A사용자 10000원 주문
        int userAPrice = statefulService1.order("userA", 10000);
        //B사용자 20000원 주문
        int userBPrice = statefulService2.order("userB" , 20000);

        //사용자 A 의 주문 금액조회 해보기
        int price = userAPrice;


        // A사용자의 주문금액은  10000원인데 20000원이 나옴 ...
        // 공유객체에 공유될만한 필드(여기서는 price) 를 가지고 있으면 이런문제가 생김
        //System.out.println("price = " + price);
        //Assertions.assertThat(statefulService1.getPrice()).isEqualTo(10000);

        //공유 필드 를 두지 않게 변경한 후 해결 !!
        // 스프링 빈 객체는 항상 무상태(stateless , 공유필드없게) 설계해야 한다.
        System.out.println("price = " + price);
        Assertions.assertThat(userAPrice).isEqualTo(10000);
    }

    static class TestConfig{

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}