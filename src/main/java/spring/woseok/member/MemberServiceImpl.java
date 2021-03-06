package spring.woseok.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MemberServiceImpl implements MemberService {
    
    private final MemberRepository memberRepository;

    //    @Autowired 는 타입 매칭을 시도하고, 이때 여러 빈이 있으면 필드 이름, 파라미터 이름으로 빈 이름을 추가 매칭한다.
    @Autowired //의존관계 자동주입!!!(@Component와 셋트가 되는 느낌 ?) . @Component 사용시 의존관계 주입 방법이다
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Optional<Member> findMember(Long memberId) {
        return memberRepository.findByid(memberId);
    }


    // 테스트 용도(스프링이 같은객체 쓰는지)
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
