package jpabook.jpashop.domain.orderitem;

import jakarta.persistence.*;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.domain.order.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "orderitem")
public class OrderItem {

    @Id @GeneratedValue
    private Long id;


    private Item item;

    @ManyToOne @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice;
    private int count;

}
