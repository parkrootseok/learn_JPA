package section5.practice_2.main;

import section5.practice_2.domain.Order;
import section5.practice_2.domain.OrderItem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            Order order = new Order();

            /* 단방향 연관 관계 */
            // em.persist(order);

            // OrderItem orderItem = new OrderItem();
            // orderItem.setOrder(order);

            // em.persist(orderItem);

            /* 양방향 연관 관계 */
            order.addOrderItem(new OrderItem());

        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close(); // 엔티티 매니저 종료(필수)
        }

        emf.close();
    }
}
