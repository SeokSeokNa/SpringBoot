package spring.woseok.member;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
