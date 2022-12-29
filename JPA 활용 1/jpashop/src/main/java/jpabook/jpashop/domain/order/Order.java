package jpabook.jpashop.domain.order;

import jakarta.persistence.*;
import jpabook.jpashop.config.status.OrderStatus;
import jpabook.jpashop.domain.delivery.Delivery;
import jpabook.jpashop.domain.member.Member;
import jpabook.jpashop.domain.orderitem.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "order")
public class Order {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;

    @OneToOne @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private Date orderDate;
    private OrderStatus status;
}
