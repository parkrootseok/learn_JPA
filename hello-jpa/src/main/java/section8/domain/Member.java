package section8.domain;

import javax.persistence.*;

@Entity
public class Member {

    @Id
    private Long id;

    @Column(name = "USERNAME")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)  // 지연 로딩 설정
    @JoinColumn(name = "TEAM_ID")
    private Team team;


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

    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);    // Team 객체에 대해 설정하는 코드. 이를 이용해 양방향 모두를 설정할 수 있다.
    }
}
