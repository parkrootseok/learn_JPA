package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpabook.jpashop.domain.order.Order;
import org.springframework.stereotype.Repository;

import javax.swing.plaf.PanelUI;

@Repository
public class OrderRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Order order) {
        em.persist(order);
    }

    public Order findOrder(Long orderId) {
        return em.find(Order.class, orderId);
    }

}
