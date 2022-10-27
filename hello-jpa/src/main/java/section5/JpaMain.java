package section5;

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

            // 팀 저장
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            // 회원 저장 - 객체 지향적이지 않다.
            Member member = new Member();
            member.setName("member1");
            member.setTeam(team); // Team 객체를 저장해야 할 것 같은데...
            em.persist(member);

            em.flush();
            em.clear();

            /* 양방향 연관 관게를 이용해 찾은 멤버가 속한 팀의 멤버 구하기 */
            Member findMember = em.find(Member.class, member.getId());
            List<Member> members = findMember.getTeam().getMembers();

            for (Member m : members) {
                System.out.println("m = " + m.getName());
            }

            /* 양방향 연관 관계를 이용할 때 반드시 주인 객체에서 수정을 해야한다 */
            /* 주인 객체는 반드시 양방향 관계를 만들기위해 만든 단방햔 관계가아닌 기존의 단방향 관계를 지닌 클래스이다 */
            // team.getMembers().add(member); 잘못된 데이터 수정 - Team 객체를 관리하는 것은 Member 클래스
            member.setTeam(team); // 옳은 방법 - 주인인 Member 클래스에서 데이터 수정이 이루어져야 한다.

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close(); // 엔티티 매니저 종료(필수)
        }
        emf.close();
    }
}
