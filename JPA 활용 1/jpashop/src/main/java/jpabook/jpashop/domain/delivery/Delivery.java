package jpabook.jpashop.domain.delivery;

import jakarta.persistence.*;
import jpabook.jpashop.config.status.DeliveryStatus;
import jpabook.jpashop.domain.Address;
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
@Table(name = "delivery")
public class Delivery {

    @Id @GeneratedValue
    private Long id;


    private Order order;
    private Address address;
    private DeliveryStatus status;

}
