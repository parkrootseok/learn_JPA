package section9;

import section8.domain.Team;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Member {

    @Id
    @GeneratedValue
    @Column("MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String name;

    // Period
    // private LocalDateTime startDate;
    // private LocalDateTime endDate;
    @Embedded
    private Period workPeriod;

    // Address
    // private String city;
    // private String street;
    // private String zipcode;
    @Embedded
    private Address homeAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "WORK_CITY")),
            @AttributeOverride(name = "street", column = @Column(name = "WORK_STREET")),
            @AttributeOverride(name = "zipcode", column = @Column(name = "WORK_ZIPCODE"))
    })
    private Address workAddress;


}
