package spring.woseok.autowired;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;
import spring.woseok.member.Member;

import java.util.Optional;

public class AutoWiredTest {

    @Test
    void AutowiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);

    }

    static class TestBean {

        @Autowired(required = false)  // " 자동주입 안하겠다 "  ,true 면 자동주입(Autowired 의 디폴트는 required = true
        public void setNoBean1(Member noBean1) {
            //required = false  즉 , 의존관계 자동주입을 안하면 메소드 자체가 실행되지 않음
            System.out.println("noBean1 = " + noBean1);
        }

        @Autowired
        public void setNoBean2(@Nullable Member noBean2) {
            // 호출은 하지만 null로 표시되게
            System.out.println("noBean2 = " + noBean2);
        }

        @Autowired
        public void setNoBean3(Optional<Member> noBean3) {
            // Optional 객체를 반환함
            System.out.println("noBean3 = " + noBean3);
        }
    }

}
