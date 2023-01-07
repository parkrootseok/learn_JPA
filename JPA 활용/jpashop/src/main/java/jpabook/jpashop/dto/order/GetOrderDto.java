package jpabook.jpashop.dto.order;

import jpabook.jpashop.config.status.OrderStatus;
import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.order.Order;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GetOrderDto {

    private Long orderId;
    private String name;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private Address address;

    public GetOrderDto(Order order) {
        this.orderId = order.getId();
        this.name = order.getMember().getName();
        this.orderDate = order.getOrderDate();
        this.orderStatus = order.getStatus();
        this.address = order.getMember().getAddress();
    }

    public GetOrderDto(Long orderId, String name, LocalDateTime
            orderDate, OrderStatus orderStatus, Address address) {
        this.orderId = orderId;
        this.name = name;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.address = address;
    }

}
