package spring.woseok.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class memberTest {
///asdasd
    @Test
    void join() {
        MemberService memberService = new MemberServiceImpl();

        //given
        Member member = new Member(1L,"memberA",Grade.VIP);


        //when
        memberService.join(member);
        Member findMember = memberService.findMember(1L).get();

        //then
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}
