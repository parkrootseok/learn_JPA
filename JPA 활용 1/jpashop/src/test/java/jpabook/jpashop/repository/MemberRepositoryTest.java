package jpabook.jpashop.repository;

import jakarta.transaction.Transactional;
import jpabook.jpashop.dto.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional
    @Rollback(value = false)
    public void testMember() {

        Member member = new Member();
        member.setUserName("memberA");
        Long saveId = memberRepository.save(member);

        Member findMember = memberRepository.find(saveId);

        Assertions.assertThat(findMember.getUserName()).isEqualTo(member.getUserName());
        Assertions.assertThat(findMember).isEqualTo(member);

    }

}