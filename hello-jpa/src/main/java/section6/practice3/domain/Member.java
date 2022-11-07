package section6.practice3.domain;

import section5.practice_2.domain.Order;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member {

    @Id @GeneratedValue @JoinColumn(name = "member_id")
    private Long id;
    private String name;
    private String city;
    private String street;
    private String zipCode;

    @OneToMany(mappedBy = "member")
    List<Orders> order = new ArrayList<>();

}
