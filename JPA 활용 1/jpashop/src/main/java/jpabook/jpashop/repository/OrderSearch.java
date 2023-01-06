package jpabook.jpashop.repository;

import jpabook.jpashop.config.status.OrderStatus;
import lombok.Data;

@Data
public class OrderSearch {

    private String memberName;
    private OrderStatus orderStatus;


}
