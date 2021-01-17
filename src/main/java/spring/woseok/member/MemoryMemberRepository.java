package spring.woseok.member;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component  // 스프링 빈 저장소에 저장될때 빈 이름이 클래스이름에서 첫글자를 소문자로 해서 등록된다
            // ex) MemoryMemberRepository 클래스는  -> memoryMemberRepository 를 가진 이름으로 스프링 빈 저장소에 등록된다.
            // 직접 빈 이름을 지정하고 싶다면 @Component("이름") 으로 지정할 수도 있다.
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();


    @Override
    public void save(Member member) {
        store.put(member.getId(), member);

    }

    @Override
    public Optional<Member> findByid(Long memberId) {
        return store.values().stream()
                .filter(member -> member.getId().equals(memberId))
                .findAny();

    }
}
