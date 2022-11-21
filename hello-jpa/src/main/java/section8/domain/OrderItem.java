package section8.domain;

import section5.practice_2.domain.Order;
import section6.practice3.domain.Item;

import javax.persistence.*;

@Entity
public class OrderItem {

    @Id
    @GeneratedValue
    @JoinColumn(name = "orderitem_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice;
    private int count;
}
