package jpabook.jpashop.api;

import java.util.List;
import java.util.stream.Collectors;
import jpabook.jpashop.domain.order.Order;
import jpabook.jpashop.domain.orderitem.OrderItem;
import jpabook.jpashop.dto.order.GetOrderDto;
import jpabook.jpashop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderApiController {

    private final OrderRepository orderRepository;

     /**
      * V1. 엔티티 직접 노출
      * - Hibernate5Module 모듈 등록, LAZY=null 처리
      * - 양방향 관계 문제 발생 -> @JsonIgnore
      */
    @GetMapping("/api/v1/orders")
    public List<Order> ordersV1() {

        List<Order> all = orderRepository.findAll();

        for (Order order : all) {
            order.getMember().getName();
            order.getDelivery().getAddress();

            List<OrderItem> orderItems = order.getOrderItems();
            orderItems.stream().forEach(o -> o.getItem().getName());
        }

        return all;
    }

    /**
     * V2. 엔티티를 조회해서 DTO로 변환(fetch join 사용X)
     * - 트랜잭션 안에서 지연 로딩 필요
     */
    @GetMapping("/api/v2/orders")
    public List<GetOrderDto> ordersV2() {
        List<Order> orders = orderRepository.findAll();
        List<GetOrderDto> result = orders.stream()
                .map(o -> new GetOrderDto(o))
                .collect(Collectors.toList());

        return result;
    }

    /**
     * V3. 엔티티를 조회해서 DTO로 변환(fetch join 사용O)
     * - 페이징 시에는 N 부분을 포기해야함(대신에 batch fetch size? 옵션 주면 N -> 1 쿼리로 변경 가능)
     */
    @GetMapping("/api/v3/orders")
    public List<GetOrderDto> ordersV3() {
        List<Order> oredrs = orderRepository.findAllWithItem();
        List<GetOrderDto> result = oredrs.stream()
                .map(o -> new GetOrderDto(o))
                .collect(Collectors.toList());

        return result;
    }

    /**
     * V3.1 엔티티를 조회해서 DTO로 변환 페이징 고려
     * - ToOne 관계만 우선 모두 페치 조인으로 최적화
     * - 컬렉션 관계는 hibernate.default_batch_fetch_size, @BatchSize로 최적화
     */
    @GetMapping("/api/v3.1/orders")
    public List<GetOrderDto> ordersV3_page(
            @RequestParam(value = "offset", defaultValue = "0") int offset,
            @RequestParam(value = "limit", defaultValue = "100") int limit
    ) {
        List<Order> oredrs = orderRepository.findAllWithMemberDelivery(offset, limit);
        List<GetOrderDto> result = oredrs.stream()
                .map(o -> new GetOrderDto(o))
                .collect(Collectors.toList());

        return result;
    }

}
