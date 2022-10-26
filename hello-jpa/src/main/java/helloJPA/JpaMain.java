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
            // 멤버 생성(비영속)
            //Member member = new Member(150L, "A");
            //Member flushMember = new Member(151L, "FLUSH");

            // RoleType 추가.
            //Member member = new Member();
            //member.setId(1L);
            //member.setUsername("A");
            //member.setRoleType(RoleType.USER);

            // 영속성 컨텍스트 이점 - 동일성 보장
            //Member A = em.find(Member.class, 150L);
            //Member B = em.find(Member.class, 150L);
            //System.out.println("result = " + (A == B));

            // 영속성 컨텍스트 이점 - 변경 감지(찾은 멤버의 데이터를 변경하는 것만으로 자동으로 DB에 Update Query 전달)
            //Member findMember = em.find(Member.class, 150L);
            //findMember.setUsername("change");

            // findMember 객체를 영속 -> 준영속 상태로 변경 (즉, 변경 감지 기능 사용 불가)
            //em.detach(findMember);

            // 멤버 찾기
            //Member findMember = em.find(Member.class, 1L);

            // JPQL (이떄 flush 자동 호출)
            // List<Member> result = em
            //        .createQuery("select m from Member as m", Member.class)
            //        .setFirstResult(1)
            //        .setMaxResults(8)
            //        .getResultList();

            //for (Member member : result) {
            //    System.out.println("member.name = " + member.getName());
            //}

            // 찾은 멤버 확인
            //System.out.println("findMember_id = " + findMember.getId());
            //System.out.println("findMember_name = " + findMember.getName());

            // 멤버 수정(다시 저장 필요 X)
            //findMember.setName("parkrootseok");

            // 멤버 삭제
            //em.remove(findMember);

            // 이 시점에 즉시 DB에 쿼리 전달
            //em.flush();

            // 테이블에 생성한 멤버 추가(비영속 -> 영속)
            // 이 때 바로 DB에 Query 날라가는것이 아니다.
            //em.persist(member);

            // 준영속
            // em.detach(member);

            // 삭제
            // em.remove(member);

            // 영속성 컨텍스트 이점 - 트랜잭션을 지원하는 쓰기 지연
            // 변경된 사항 DB에 적용(즉, 트랜잭션 커밋 후 DB에 쿼리 전달)
            tx.commit();

        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close(); // 엔티티 매니저 종료(필수)
        }

        emf.close();
    }
}
