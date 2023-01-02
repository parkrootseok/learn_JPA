package jpabook.jpashop.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpabook.jpashop.config.status.OrderStatus;
import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.domain.member.Member;
import jpabook.jpashop.domain.order.Order;
import jpabook.jpashop.exception.NotEnoughStockException;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.repository.OrderRepository;
import org.junit.Test;
import org.junit.jupiter.engine.discovery.predicates.IsTestMethod;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;

    @Test
    public void 상품주문() throws Exception {
        //given
        Member member = createMember();
        Item item = createBook();

        Long orderId = orderService.order(member.getId(), item.getId(), 2);

        //when
        Order getOrder = orderRepository.findOrder(orderId);

        //then
        assertEquals(OrderStatus.ORDER, getOrder.getStatus());
        assertEquals(1, getOrder.getOrderItems().size());
        assertEquals(2 * 10000,getOrder.getTotalPrice());
        assertEquals(8, item.getStockQuantity());
    }

    @Test(expected = NotEnoughStockException.class)
    public void 상품주문_재고수량초과() throws Exception {
        //given
        Member member = createMember();
        Item item = createBook();
        int orderCount = item.getStockQuantity() + 1;

        //when
        orderService.order(member.getId(), item.getId(), orderCount);

        //then
        fail("재고수량 오류 발생");

    }

    @Test
    public void 주문_취소() throws Exception {
        //given
        Member member = createMember();
        Item item = createBook();

        Long orderId = orderService.order(member.getId(), item.getId(), 2);

        //when
        Order getOrder = orderRepository.findOrder(orderId);
        getOrder.cancel();

        //then
        assertEquals(OrderStatus.CANCEL, getOrder.getStatus());
    }

    private Member createMember() {
        Member member = new Member();
        member.setName("root");
        member.setAddress(new Address("서울시", "구로구", "경인로 15나길 12"));
        em.persist(member);

        return member;
    }

    private Book createBook() {

        Book book = new Book();
        book.setName("JPA");
        book.setStockQuantity(10);
        book.setPrice(10000);
        em.persist(book);

        return book;
    }

}