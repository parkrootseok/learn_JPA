package jpabook.jpashop.api;

import jakarta.validation.Valid;
import jpabook.jpashop.domain.member.Member;
import jpabook.jpashop.dto.member.PostMemberReq;
import jpabook.jpashop.dto.member.PostMemberRes;
import jpabook.jpashop.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController // @Controller + @ResponseBody 를 합친 어노테이션
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    /**
     * 등록 V1: 요청 값으로 Member 엔티티를 직접 받는다.
     * 문제점
     * - 엔티티에 프레젠테이션 계층을 위한 로직이 추가된다.
     * - 엔티티에 API 검증을 위한 로직이 들어간다. (@NotEmpty 등등)
     * - 실무에서는 회원 엔티티를 위한 API가 다양하게 만들어지는데, 한 엔티티에 각각의 API를
     위한 모든 요청 요구사항을 담기는 어렵다.
     * - 엔티티가 변경되면 API 스펙이 변한다.
     * 결론
     * - API 요청 스펙에 맞추어 별도의 DTO를 파라미터로 받는다.
     */
    @PostMapping("/api/v1/members")
    public PostMemberRes joinMemberV1(@RequestBody @Valid Member member) {
        Long id = memberService.join(member);
        return new PostMemberRes(id);
    }
    // @RequestBody : JSON 타입으로 온 데이터를 매핑하기 위한 어노테이션
    // Member 객체를 이용하면 유연성이 떨어지기 때문에 DTO 객체를 추가 생성하여 해결하자

    /**
     * 등록 V2: 요청 값으로 Member 엔티티 대신에 별도의 DTO를 받는다.
     */
    @PostMapping("/api/v2/members")
    public PostMemberRes joinMemberV2(@RequestBody @Valid PostMemberReq postMemberReq) {

        Member member = new Member();
        member.setName(postMemberReq.getName());

        Long id = memberService.join(member);
        return new PostMemberRes(id);

    }
    // 엔티티의 스펙은 노출하면 안됨!!
    // 엔티티와 API 스펙을 분리 가능

}
