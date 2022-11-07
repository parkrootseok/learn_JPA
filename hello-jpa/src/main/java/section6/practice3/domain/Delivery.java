package section6.practice3.domain;

import javax.persistence.*;

@Entity
public class Delivery {

    @Id @GeneratedValue
    @JoinColumn(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery")
    private Orders order;

    private String city;
    private String street;
    private String zipcode;
    private String status;
}
