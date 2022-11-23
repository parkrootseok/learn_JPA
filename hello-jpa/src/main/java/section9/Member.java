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
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    // Address
    private String city;
    private String street;
    private String zipcode;

}
