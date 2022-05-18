package hello.membermanagement.service;

import hello.membermanagement.domain.Member;
import hello.membermanagement.repository.MemberRepository;
import hello.membermanagement.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//@Service    // Spring Bean Registration by component scan
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    // Dependency Injection
//    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;   // final 변수를 생성자로 초기화
    }
    // TODO: DI 정리

    // 회원가입
    public Long join(Member member) {
        // 같은 이름이 있는 중복 회원 X
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {   // 값이 존재하는지 확인하는 Optional의 메서드
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    // 전체회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    // id로 조회
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
