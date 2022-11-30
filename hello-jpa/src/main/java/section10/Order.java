package section10;

import practice.jpashop.config.BaseEntity;
import section7.domain.Member;
import section8.domain.Delivery;
import section8.domain.OrderItem;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Orders")
public class Order extends BaseEntity {

    @Id @GeneratedValue
    @JoinColumn(name = "order_id")
    private Long id;
    private int orderAmount;

    @Embedded
    private Address address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(int orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
