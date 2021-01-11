package spring.woseok.beanfind;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.woseok.AppConfig;

public class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("스프링 컨테이너 안에 스프링Bean 저장소안에 들어있는 모든 Bean 출력")
    //Junit5 부터 pulic 안써도 됨
    // Bean저장소 안에는 스프링 자체에서 확장하기위해 기본으로 필요한 Bean 객체도 같이 들어있음(내가 등록하지 않은 ..)
    void findAllBean() {
        // 스프링 컨테이너에 등록되어있는 모든 Bean 이름을 조회함
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("name = " + beanDefinitionName + "   object = " + bean);
        }
    }

    @Test
    @DisplayName("내가 등록한 Bean 만 출력하기")
    //Junit5 부터 pulic 안써도 됨
    // Bean저장소 안에는 스프링 자체에서 확장하기위해 기본으로 필요한 Bean 객체도 같이 들어있음(내가 등록하지 않은 ..)
    void findApplicationBean() {
        // 스프링 컨테이너에 등록되어있는 모든 Bean 이름을 조회함
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            //getBeanDefinition(Bean이름) 는 해당 Bean이름을 가진 Bean의 메타데이터 정보이다.
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);


            //"BeanDefinition.ROLE_APPLICATION" 는 내가 등록한 Bean들만 가지고 있는 값? 같은거다.
            // Role ROLE_APPLICATION : 내가 직접 등록한 애플리케이션 빈
            // Role ROLE_INFRANSTRUCTURE : 스프링이 내부에서 사용하는 빈
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("name = " + beanDefinitionName + "   object = " + bean);
            }
        }
    }
}
