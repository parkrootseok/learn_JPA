package section7.domain;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) // 조인 전략
@DiscriminatorColumn(name = "DTYPE") // 부모 테이블에 상속받은 클래스에 대한 정보 추가
public class Item {

    @Id @GeneratedValue
    private Long id;

    private String name;
    private int price;

}
