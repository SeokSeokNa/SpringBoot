package spring.woseok;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

//컴포넌트 스캔 방식 써보기
@Configuration
//@ComponentScan
@ComponentScan(
//        basePackages = "spring.woseok.member" ,   빈 등록할때 특정 패키지 안에 있는것만 등록할 수 있는 방법(해당 패키지의 하위 패키지포함)
//        basePackageClasses = AutoAppConfig.class, 특정 클래스가 포함되어있는 패키지 안에 있는것만 등록할 수 있는방법
//        아무것도 지정하지 않으면 현재 @ComponentScan를 쓰고 있는 클래스의 해당 패키지안을 모두 뒤져서 등록 시킨다.
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION , classes = Configuration.class)
) // 스프링빈이 자동등록될때 등록하지 않을것들을 지정할 수 있다 !! , "@Configuration 이 들어간건 등록 안하겠다 !!"
  // AppConfig 클래스나 테스트할때 @Configruration을 사용한것들도 같이 컨테이너에  등록이 되기 때문에 테스트하기 위해 배제하려고 이렇게 했다 ..
public class AutoAppConfig {
    // 스프링 빈 저장소에 저장될때 빈 이름이 클래스이름에서 첫글자를 소문자로 해서 등록된다
    // ex) MemberSerivceImpl 클래스는  -> memberSerivceImpl 를 가진 이름으로 스프링 빈 저장소에 등록된다.
    // 직접 빈 이름을 지정하고 싶다면 @Component("이름") 으로 지정할 수도 있다.
}
