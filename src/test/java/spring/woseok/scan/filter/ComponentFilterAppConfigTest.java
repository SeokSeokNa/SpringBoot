package spring.woseok.scan.filter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.ComponentScan.Filter;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class ComponentFilterAppConfigTest {

    @Test
    void filterScan() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
        BeanA beanA = ac.getBean("beanA", BeanA.class);
        assertThat(beanA).isNotNull();

        assertThrows(
                NoSuchBeanDefinitionException.class ,
                () ->ac.getBean("beanB", BeanB.class)
        );
    }
    @Configuration
    @ComponentScan(
            includeFilters = @Filter(type = FilterType.ANNOTATION , classes = MyIncludeComponent.class), // 포함할것(@MyIncludeComponent를 가진애들)
            excludeFilters = @Filter(classes = MyExcludeComponent.class)) // 제외할것(@MyExcludeComponent를 가진애들)
            //"type = FilterType.ANNOTATION" 는 기본 타입이라 생략해도 무방
    static class ComponentFilterAppConfig{}
}
