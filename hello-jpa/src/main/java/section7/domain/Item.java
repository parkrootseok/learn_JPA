package section7.domain;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) // 조인 전략
public class Item {

    @Id @GeneratedValue
    private Long id;

    private String name;
    private int price;

}
