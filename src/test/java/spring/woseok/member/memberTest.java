package spring.woseok.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spring.woseok.AppConfig;

public class memberTest {
    MemberService memberService;

    //이 테스트 클래스안에 모든 테스트 메소드를 실행하기 전에 이 메소드를 한번씩 거치게됨
    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }
    @Test
    void join() {
        //given
        Member member = new Member(1L,"memberA",Grade.VIP);


        //when
        memberService.join(member);
        Member findMember = memberService.findMember(1L).get();

        //then
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}
