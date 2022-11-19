package practice1.jpashop.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Orders")
public class Order {

    @Id @GeneratedValue @Column(name = "ORDER_ID")
    private Long Id;

    // 이것을 이용해 멤버 객체를 얻는 것은 객체 지향적이지 못함.
    @Column(name = "MEMBER_ID")
    private Long memberId;

    // 아래 처럼 바로 멤버 객체를 얻을 수 있어야 객체 지향적이다.
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    public Member getMember() {
        return member;
    }

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public void setMember(Member member) {
        this.member = member;
    }

    public Order() {}

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
