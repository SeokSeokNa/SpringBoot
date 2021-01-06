package spring.woseok.member;

import java.util.Optional;

public interface MemberRepository {

    void save(Member member);

    Optional<Member> findByid(Long memberId);
}
