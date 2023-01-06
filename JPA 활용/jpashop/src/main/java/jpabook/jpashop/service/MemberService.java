package jpabook.jpashop.service;

import jpabook.jpashop.domain.member.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) // 읽기 전용으로 설정 시 조회만 하는 쿼리 성능 향상
@RequiredArgsConstructor    // 의존관계 주입
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원 가입
     */
    @Transactional // 조회 기능만 하지 않는 API는 트랜잭션을 따로 선언
    public Long join(Member member) {

        // 중복 회원 검증
        validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();

    }

    private void validateDuplicateMember(Member member) {

        List<Member> findMembers = memberRepository.findByName(member.getName());

        if (!findMembers.isEmpty()) {
            throw new IllegalArgumentException("이미 존재하는 회원입니다.");
        }

    }

    /**
     * 회원 조회
     */
    public Member findMember(Long memberId) {
        return memberRepository.find(memberId);
    }

    /**
     * 회원 전체조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    @Transactional
    public void update(Long memberId, String name) {
        Member member = memberRepository.find(memberId);
        member.setName(name);
    }
}
