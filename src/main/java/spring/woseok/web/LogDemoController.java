package spring.woseok.web;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import spring.woseok.common.MyLogger;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final  LogDemoService logDemoService;
//    private final MyLogger myLogger;
//    "request scope는 사용자 요청이들어오고 나갈떄까지가 생존범위 이므로
//    스프링이 올라올떄 의존관계 주입을 받으면 오류가 난다
//    그래서 대신 빈 객체를 조회해주는(DL) ObjectProvider를 주입 받아서 요청 시점에
//    provider가 스프링에게 요청하여 스프링이 그제서야 생성해서 넘겨주는 방식으로 사용함
private final ObjectProvider<MyLogger> myLoggerProvider;

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) {
        String requestURL = request.getRequestURL().toString();
        MyLogger myLogger = myLoggerProvider.getObject();
        myLogger.setRequestURL(requestURL);
       
        myLogger.log("controller test");
        logDemoService.logic("testId");
        return "ok";

    }

}
