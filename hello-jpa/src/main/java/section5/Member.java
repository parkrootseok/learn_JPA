package section5;

import javax.persistence.*;
import java.lang.ref.PhantomReference;

@Entity
public class Member {

    @Id
    private Long id;

    @Column(name = "USERNAME")
    private String name;

    @ManyToOne  // Many(N):One(1) - Team:Member 관계를 의미
    @JoinColumn(name = "TEAM_ID")
    private Team team; // 객체를 참조하는 방식으로 변환


    public Member() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
