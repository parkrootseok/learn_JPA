package section5;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        // 트랜잭션 시작(반드시 트랜잭션 안에서 작업해야 한다.)
        tx.begin();

        /* 정석 코드 */
        try {

            // 팀 저장
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            // 회원 저장 - 객체 지향적이지 않다.
            Member member = new Member();
            member.setName("member1");
            member.setTeam(team); // Team 객체를 저장해야 할 것 같은데...
            em.persist(member);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close(); // 엔티티 매니저 종료(필수)
        }
        emf.close();
    }
}
