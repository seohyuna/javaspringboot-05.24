package likelion.springbootsunny.controller;

import likelion.springbootsunny.domain.Member;
import likelion.springbootsunny.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.List;

/*
 * @Controller 은 사용자의 요청이 진입하는 지점(entry point)입니다.
 * 요청에 따라 어떤 처리를 할지 결정해주며 단, Controller 는 단지 결정만 해주고 실질적인 처리는 서비스(Layered Architecture)에서 담당합니다.
 * 사용자에게 View(또는 서버에서 처리된 데이터를 포함하는 View)를 응답으로 보내줍니다.
 * @RequestMapping 는 URL 와 Controller 의 method 매핑을 설정하는 어노테이션이입니다.
 * URL 외에도 다양한 속성을 지정할 수 있습니다.
 **/
@Controller
@RequestMapping("members")

public class MemberController {
    /*
     *private final 은 자바에서 인스턴스 변수를 선언할 때 사용되는 한정자(modifier)의 조합입니다.
     * private 은 접근 제어자로, 해당 변수에 접근할 수 있는 범위를 제한합니다. private으로 선언된 변수는 동일한 클래스 내에서만 접근할 수 있습니다.
     * final 은 변수에 할당된 값을 변경할 수 없음을 나타냅니다. final로 선언된 변수는 한 번 할당된 후에는 그 값을 변경할 수 없습니다. 이는 변수를 상수로 만들어주는 역할을 합니다.
     * 가지고 있는 특징 1.접근 제어: private 키워드로 인해 해당 변수는 클래스 외부에서 직접 접근할 수 없습니다. 대신, 클래스 내부에서 메서드를 통해 변수에 접근할 수 있습니다.
     *               2.변경 불가능성: final 키워드로 인해 변수에 할당된 값은 변경할 수 없습니다. 한 번 초기화되면 그 값을 수정할 수 없으므로, 변수의 내용이 변하지 않음을 보장합니다.
     *               3.캡슐화: private 키워드로 인해 클래스 외부에서 직접 접근할 수 없으므로, 클래스는 변수에 대한 접근을 제어하고 필요에 따라 값을 검증하거나 조작할 수 있습니다.
     **/
    private final MemberService memberService;

    /*
     * (예시)
     * 이 것은 생성자입니다.
     * @Autowired 라는 어노테이션은 MemberController 객체를 실행해야 할 때 필요한 의존성을 주입해달라고 선언하기 위해 명시하는 어노테이션이며, 생성자 주입 방식을 선언하고 있습니다.
     * MemberController 의 필드를 MemberService 타입으로 선언하였지만, 생성자 paramer 에는 MemberServiceImpl 이 주입되게 함으로써 느슨한 결합(Loosen Coupling)을 구현하였습니다.
     * @참고 : 실제로는 MemberController 생성자의 파라미터에 MemberServiceImpl 이 아니라 MemberService 로 쓰여있어도 스프링이 알아서 구현체 클래스의 인스턴스 (MemberServiceImpl memberserviceimpl)를 넣어주게 됩니다.
     *       즉, public MemberController(MemberService memberService) {this.memberService = memberService;} 와 같이 작성해도 에러가 없고, 이게 사실 정석입니다.
     *       아래처럼 작성해 둔 이유는, 실제로는 아래와 같이 동작한다는 것을 여러분 눈으로 먼저 보길 바랐던 제 마음이었습니다.
     *       지금, MemberController 의 필드가 MemberService 타입의 데이터인데, 생성자로 주입되는 것은 MemberServiceImpl 타입이라는 것을 충분히 음미하시길 바랍니다.
     **/
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    /*
     *@GetMapping 은
     * 스프링 프레임워크에서 사용되는 어노테이션입니다. 이 어노테이션은 HTTP GET 요청을 처리하는 메서드를 지정하는 데 사용됩니다.
     * 스프링은 웹 애플리케이션을 개발할 때 HTTP 요청에 따라 적절한 메서드를 호출하여 처리합니다. @GetMapping은 HTTP GET 요청을 처리하는 메서드를 매핑하는 데 사용되며, 해당 메서드는 클라이언트의 GET 요청에 응답하는 역할을 수행합니다.
     * @GetMapping 어노테이션은 클래스 수준과 메서드 수준에서 사용할 수 있습니다. 클래스 수준에서 사용할 경우, 해당 클래스의 모든 요청 핸들러 메서드에 적용됩니다. 메서드 수준에서 사용할 경우, 특정 메서드에만 적용됩니다.
     * @GetMapping("new")
     * 경로: "/new"
     * 매개변수: Model model
     * 반환 타입: String
     * 기능: "members/createMemberForm" 뷰를 반환하고, "memberForm"이라는 이름으로 빈 Member 객체를 모델에 추가합니다.
     **/
    @GetMapping("new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new Member()) ;
        return "members/createMemberForm";
    }

    /*
     * @PostMapping 은 스프링 프레임워크에서 사용되는 어노테이션으로, HTTP POST 요청을 처리하는 메서드를 지정하는 데 사용됩니다.
     * HTTP POST 요청은 클라이언트에서 서버로 데이터를 전송하는 데 사용되며, 주로 데이터를 생성하거나 수정하기 위해 사용됩니다.
     * @PostMapping 어노테이션은 이러한 POST 요청을 처리하는 메서드를 매핑하는 데 사용되며, 해당 메서드는 클라이언트의 POST 요청에 응답하여 적절한 동작을 수행합니다.
     * @GetMapping 과 마찬가지로 @PostMapping 어노테이션은 클래스 수준과 메서드 수준에서 사용할 수 있습니다. 클래스 수준에서 사용할 경우, 해당 클래스의 모든 요청 핸들러 메서드에 적용됩니다. 메서드 수준에서 사용할 경우, 특정 메서드에만 적용됩니다.
     * 매개변수: Member member
     * 클라이언트로부터 전송된 폼 데이터가 자동으로 Member 객체로 변환되어 전달됩니다.
     * 반환 타입: String
     * 반환하는 문자열은 리다이렉트 대상 경로입니다. 이 경우 "/" 경로로의 리다이렉트가 수행됩니다.
     * 기능: 메서드가 호출되면 전달받은 member 객체를 memberService.save(member)를 사용하여 저장합니다. 이는 회원 데이터를 생성하거나 저장하는 작업을 수행합니다.
     * 그리고 "/" 경로로의 리다이렉트를 반환합니다. 이는 클라이언트를 다른 경로로 이동시키는 역할을 합니다. 이 경우, 회원 생성 후에는 홈 페이지로 리다이렉트됩니다.
     **/
    @PostMapping("new")
    public String create(@RequestBody Member member) {
        memberService.save(member);
        return "redirect:/";
    }

    /*
     *@GetMapping("")
     * 경로: "/"
     * 매개변수: Model model
     * 반환 타입: String
     * 기능: "members/memberList" 뷰를 반환하고, "memberList"라는 이름으로 모든 회원(Member) 객체의 리스트를 모델에 추가합니다.
     **/
    @GetMapping("")
    public String findAll(Model model) {
        List<Member> memberList = memberService.findAll();
        model.addAttribute("memberList", memberList);
        return "members/memberList";
    }
}
