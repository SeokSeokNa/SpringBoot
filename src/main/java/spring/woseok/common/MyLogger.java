package spring.woseok.common;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

// 로그를 출력하기 위한 빈
@Component
@Scope(value = "request") //value 빼고 request만 적어도됨
//request 스코프는 고객의 요청 ~ 반환까지가 생존범위
public class MyLogger {
    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message) {
        System.out.println("[" + uuid + "]" +"[" + requestURL +"]" +"[" + message +"]");

    }

    @PostConstruct
    public void init() {
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "] request scope bean create" + this  );

    }

    @PreDestroy
    public void close() {
        System.out.println("[" + uuid + "] request scope bean close" + this  );

    }
}
