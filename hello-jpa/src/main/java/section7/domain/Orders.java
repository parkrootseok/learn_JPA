package section7.domain;

import section7.Config.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Orders extends BaseEntity {

    @Id @GeneratedValue
    @JoinColumn(name = "order_id")
    private Long id;

    @ManyToOne @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private String orderDate;
    private String status;

}
