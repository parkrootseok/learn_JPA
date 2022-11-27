package practice.jpashop.domain;

import practice.jpashop.config.BaseEntity;

import javax.persistence.*;

@Entity
public class Delivery extends BaseEntity {

    @Id @GeneratedValue
    @JoinColumn(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Orders order;

    @Embedded
    private  Address address;
    private String status;
}
