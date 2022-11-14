package section7.domain;

import section7.Config.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member extends BaseEntity {

    @Id @GeneratedValue @JoinColumn(name = "member_id")
    private Long id;
    private String name;
    private String city;
    private String street;
    private String zipCode;

    @OneToMany(mappedBy = "member")
    List<Orders> order = new ArrayList<>();

}
