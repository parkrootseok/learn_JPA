package section8.domain;

import section7.Config.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
/* 전략 종류
 * 1. 조인 (InheritanceType.JOINED)
 * 2. 단일 테이블 (InheritanceType.SINGLE_TABLE) <- 반드시 데이터 타입 필요!!
 * 3. 테이블 전략 (InheritanceType.TABLE_PER_CLASS)
 * */
@Inheritance(strategy = InheritanceType.JOINED) // 조인 전략
@DiscriminatorColumn(name = "DTYPE") // 부모 테이블에 상속받은 클래스에 대한 정보 추가
public class Item extends BaseEntity {

    @Id @GeneratedValue
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @OneToMany(mappedBy = "item")
    private List<CategoryItem> categoryItems = new ArrayList<>();

}
