package helloJPA;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        // 트랜잭션 시작(반드시 트랜잭션 안에서 작업해야 한다.)
        tx.begin();

        /* 정석 코드 */
        try {
            // 멤버 생성
            //Member member = new Member();

            // 데이터 삽입
            //member.setId(2L);
            //member.setName("seok");

            // 멤버 찾기
            //Member findMember = em.find(Member.class, 1L);

            // JPQL
            List<Member> result = em
                    .createQuery("select m from Member as m", Member.class)
                    .setFirstResult(1)
                    .setMaxResults(8)
                    .getResultList();

            for (Member member : result) {
                System.out.println("member.name = " + member.getName());
            }

            // 찾은 멤버 확인
            //System.out.println("findMember_id = " + findMember.getId());
            //System.out.println("findMember_name = " + findMember.getName());

            // 멤버 수정(다시 저장 필요 X)
            //findMember.setName("parkrootseok");

            // 멤버 삭제
            //em.remove(findMember);

            // 테이블에 생성한 멤버 추가
            //em.persist(member);

            tx.commit(); // 변경된 사항 DB에 적용

        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close(); // 엔티티 매니저 종료(필수)
        }

        emf.close();
    }
}
