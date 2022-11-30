package section10;

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
        try {

            Member member = new Member();
            member.setUserName("member1");
            member.setAge(10);
            em.persist(member);

            /* 1. Query 타입 조회 */
            List results1 = em.createQuery("select m.userName, m.age from Member m").getResultList();

            Object o = results1.get(0);
            Object[] result = (Object[]) o;

            /* 2. Object[] 타입 조회 */
            List<Object[]> results2 = em.createQuery("select m.userName, m.age from Member m").getResultList();
            result = results2.get(0);

            /* 3. new 명령어 조회*/
            List<MemberDTO> result3 = em.createQuery("select new section10.MemberDTO(m.userName, m.age) from Member m", MemberDTO.class).getResultList();
            MemberDTO memberDTO = result3.get(0);

            tx.commit();
        } catch (Exception e) {
         tx.rollback();
         e.printStackTrace();
        }
    }
}
