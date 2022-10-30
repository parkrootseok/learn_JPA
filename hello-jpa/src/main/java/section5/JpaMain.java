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

            /* 양방향 연관 관계를 이용할 때 반드시 양쪽에 값을 설정 */
            // team.getMembers().add(member);
            // member.setTeam(team);

            /* 양방향 연관 관계 편의 메소드를 이용하여 쉽게 하자 */
            // 메소드가 의미를 지닐 땐 메소드 명을 명확하게 적자!!
            // 1. 멤버를 기준으로 팀을 추가 2. 팀을 기준으로 멤버를 추가할지 선택
            // 기준이 되는 클래스에 다음과 같이 연관 관계 메소드를 생성하자!!
            member.changeTeam(team);


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close(); // 엔티티 매니저 종료(필수)
        }
        emf.close();
    }
}
