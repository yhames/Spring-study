package hello.membermanagement.repository;

import hello.membermanagement.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id); // Optional for null check
    Optional<Member> findByName(String Name);
    List<Member> findAll();
}
