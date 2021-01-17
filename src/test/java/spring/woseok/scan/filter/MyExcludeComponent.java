package spring.woseok.scan.filter;

import java.lang.annotation.*;

//어노테이션 만들어보기
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyExcludeComponent {
}
