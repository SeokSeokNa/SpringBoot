package spring.woseok.beandefinition;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import spring.woseok.AppConfig;

public class BeanDefinitionTest {

    //자바 파일 방식(팩토리 메소드 방식이라고 함 , factoryBean을 통해서 Bean을 등록하는 형태)
    AnnotationConfigApplicationContext ac =  new AnnotationConfigApplicationContext(AppConfig.class);
    //xml 방식(Bean을 직접 등록하는 방식)
    //GenericXmlApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");
    @Test
    @DisplayName("빈 설정 메타정보 확인")
    void findApplicationBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                System.out.println("beanDefinition = " + beanDefinition +
                        " beanDefinition = " + beanDefinition);
            }
        }
    }
}
