package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import jpabook.jpashop.domain.member.Member;
import jpabook.jpashop.domain.order.Order;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Order order) {
        em.persist(order);
    }

    public Order find(Long orderId) {
        return em.find(Order.class, orderId);
    }

    public List<Order> findAll() {
        return em.createQuery("select o from Order o", Order.class).getResultList();
    }

    /**
     * JPA Criteria (가독성이 좋지 않기에 사용을 권장하지는 않음)
     * Querydsl 사용 권장
     */
    public List<Order> findAllByCriteria(OrderSearch orderSearch) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Order> cq = cb.createQuery(Order.class);
        Root<Order> o = cq.from(Order.class);
        Join<Order, Member> m = o.join("member", JoinType.INNER); //회원과 조인

        List<Predicate> criteria = new ArrayList<>();

        // 주문 상태 검색
        if (orderSearch.getOrderStatus() != null) {
            Predicate status = cb.equal(o.get("status"),
                    orderSearch.getOrderStatus());
            criteria.add(status);
        }
        // 회원 이름 검색
        if (StringUtils.hasText(orderSearch.getMemberName())) {
            Predicate name =
                    cb.like(m.<String>get("name"), "%" +
                            orderSearch.getMemberName() + "%");
            criteria.add(name);

        }

        cq.where(cb.and(criteria.toArray(new Predicate[criteria.size()])));
        TypedQuery<Order> query = em.createQuery(cq).setMaxResults(1000); //최대 1000건
        return query.getResultList();

    }

    public List<Order> findAllWithMemberDelivery() {
        return em.createQuery("select o from Order o " +
                                        "join fetch o.member m " +
                                        "join fetch o.delivery d", Order.class)
                .getResultList();
    }

    public List<Order> findAllWithMemberDelivery(int offset, int limit) {

        return em.createQuery(
                        "select o from Order o "
                                + "join fetch o.member m "
                                + "join fetch o.delivery d ", Order.class)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();

    }

    //  1:N 조회일 경우 중복 조회를 막기 위해 distinct 키워드를 사용한다.
    public List<Order> findAllWithItem() {

        return em.createQuery(
                        "select distinct o from Order o "
                                + "join fetch o.member m "
                                + "join fetch o.delivery d "
                                + "join fetch o.orderItems oi "
                                + "join fetch oi.item i", Order.class)
                .getResultList();

    }
}
