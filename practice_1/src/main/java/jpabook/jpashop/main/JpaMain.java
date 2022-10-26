package jpabook.jpashop.main;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;

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


        /* 정석 코드 */
        try {
            // 객체 지향적이지 않은 코드
            Order order = em.find(Order.class, 1L);
            Long memberid = order.getId();
            Member member = em.find(Member.class, memberid);

            // 객체 지향적인 코드
            Member findmember = order.getMember();

            tx.commit();

        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close(); // 엔티티 매니저 종료(필수)
        }

        emf.close();
    }
}
