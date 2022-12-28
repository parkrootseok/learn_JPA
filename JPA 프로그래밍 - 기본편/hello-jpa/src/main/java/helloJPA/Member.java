package helloJPA;

import javax.persistence.*;
import java.util.Date;

@Entity // 객체 등록 어노테이션
public class Member {

    @Id
    // @GeneratedValue(strategy = ) // 자동 할당 설정
    private Long id;

    // @Column : 컬럼 매핑
    @Column(name = "name")
    private String username;

    private int age;

    // @Enumerated : enum 타입 매핑
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    // @Temporal : 날짜 타입 매핑
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    // @Lob : BLOB, CLOB 매핑
    @Lob
    private String description;

    // @Transient : 특정 필드를 컬럼에 매핑하지 않음(매핑 무시)
    @Transient
    private int tmp;

    public Member() {}  // JPA 객체는 반드시 기본 생성자 필요

    public Member(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTmp() {
        return tmp;
    }

    public void setTmp(int tmp) {
        this.tmp = tmp;
    }
}
