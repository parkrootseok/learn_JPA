package jpabook.jpashop.domain;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Embeddable
@Getter // 변경 불가여야 하므로 Getter 어노테이션만 입력
public class Address {

    private String city;
    private String street;
    private String zipcode;

    protected Address() {}

    // 값 타입은 변경 불가능하게 설계. 즉, 생성할 때 모든 값을 초기화하여 변경할 수 없도록 설정
    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }

}
