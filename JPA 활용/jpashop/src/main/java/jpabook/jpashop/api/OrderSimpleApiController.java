package jpabook.jpashop.api;

import jpabook.jpashop.domain.order.Order;
import jpabook.jpashop.dto.order.GetOrderDto;
import jpabook.jpashop.repository.OrderQueryRepository;
import jpabook.jpashop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

    private final OrderRepository orderRepository;
    private final OrderQueryRepository orderQueryRepository;

    /**
     * V1. 엔티티 직접 노출
     * - Hibernate5Module 모듈 등록, LAZY=null 처리
     * - 양방향 관계 문제 발생 -> @JsonIgnore
     */
    @GetMapping("/api/v1/order")
    public List<Order> ordersV1() {
        List<Order> orders = orderRepository.findAll();

        // member와 adrress는 지연 로딩 사용
        // 즉, 실제 엔티티가 아닌 프록시가 존재
        // jackson 라이브러리는 프록시 객체를 json으로 생성 불가
        // 위 코드는 Hibernate5Module을 스프링 빈으로 등록하여 해결 가능(단, 이는 추천하지 않는 방법)
        // 아래와 같이 강제 초기화를 통해 해결 가능
        for (Order o : orders) {
            // Lazy 강제 초기화
            o.getMember().getName();
            o.getDelivery().getAddress();
        }
        return orders;
    }

    /**
     * V2. 엔티티를 조회해서 DTO로 변환(fetch join 사용X)
     * - 단점: 지연로딩으로 쿼리 N번 호출
     */
    @GetMapping("/api/v2/order")
    public List<GetOrderDto> ordersV2() {
        List<Order> orders = orderRepository.findAll();
        List<GetOrderDto> getOrderDtos = orders.stream()
                .map(o -> new GetOrderDto(o))
                .collect(Collectors.toList());

        return getOrderDtos;
    }

    /**
     * V3. 엔티티를 조회해서 DTO로 변환(fetch join 사용O)
     * - fetch join으로 쿼리 1번 호출
     * 참고: fetch join에 대한 자세한 내용은 JPA 기본편 참고(정말 중요함)
     */
    @GetMapping("/api/v3/order")
    public List<GetOrderDto> ordersV3() {
        List<Order> orders = orderRepository.findAllWithMemberDelivery();
        List<GetOrderDto> getOrderDtos = orders.stream()
                .map(o -> new GetOrderDto(o))
                .collect(Collectors.toList());

        return getOrderDtos;
    }

    /**
     * V4. JPA에서 DTO로 바로 조회
     * - 쿼리 1번 호출
     * - select 절에서 원하는 데이터만 선택해서 조회
     */
    @GetMapping("/api/v4/order")
    public List<GetOrderDto> ordersV4() {
        return orderQueryRepository.findOrderDtos();
    }

}
