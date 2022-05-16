package hello.membermanagement.repository;

import hello.membermanagement.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> fildById(Long id);
    Optional<Member> findByName(String Name);
    List<Member> findAll();
}
