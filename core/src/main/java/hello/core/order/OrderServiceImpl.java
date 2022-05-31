package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    /**
     * DIP(Dependency Inversion Principle) Violation
     * 구현체가 아니라 추상체(인터페이스)에 의존해야한다.
     *
     * OCP(Open-Closed Principle) Violation
     * 확장에는 열려있고 수정/변경에는 닫혀있어야 한다.
     */
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    /**
     * DIP를 지키기 위해 인터페이스에만 의존
     * -> NullPointException 발생
     */

    /**
     * 생성자 주입
     * 불변, 필수 - 객체를 생성할 때 반드시 주입되어야하고, 변하지 않으며, final로 외부에서 접근할 수 없게 설정 가능
     */
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    /**
     * 수정자 주입
     * 선택, 변경 - 외부에서 자바빈 프로퍼티 규약 수정자 메서드(setter)로 접근이 가능함
     */
//    private MemberRepository memberRepository;
//    private DiscountPolicy discountPolicy;
//
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }
//
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        this.discountPolicy = discountPolicy;
//    }

    /**
     * 필드 주입
     * 외부에서 변경이 불가능해서 테스트하기 힘들다
     * DI프레임워크가 없으면 아무것도 못함
     */
//    @Autowired private MemberRepository memberRepository;
//    @Autowired private DiscountPolicy discountPolicy;

    /**
     * 일반메서드 주입
     * 한번에 여러 필드를 주입받을 수 있다.
     */
//    private MemberRepository memberRepository;
//    private DiscountPolicy discountPolicy;
//
//    @Autowired
//    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // for Test
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
