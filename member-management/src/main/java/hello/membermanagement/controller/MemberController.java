package hello.membermanagement.controller;

import hello.membermanagement.domain.Member;
import hello.membermanagement.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller // Spring Bean Registration by component scan
public class MemberController {
    private final MemberService memberService;

    // Dependency Injection : Constructor Injection *
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

//    // Dependency Injection : Field Injection
//    @Autowired private MemberService memberService;

//    // Dependency Injection : Setter Injection
//    private MemberService memberService;
//
//    @Autowired
//    public void setMemberService(MemberService memberService) {
//        this.memberService = memberService;
//    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";    // 회원가입이 끝나면 홈 화면으로 돌아간다.
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}