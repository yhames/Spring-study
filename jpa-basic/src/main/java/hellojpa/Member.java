package hellojpa;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
//@SequenceGenerator(name = "member_seq_generator",
//        sequenceName = "member_seq",    // 매핑할 데이터베이스 시퀀스 이름
//        initialValue = 1, allocationSize = 50)
//@TableGenerator(name = "member_seq_generator",
//        table = "my_sequences",
//        pkColumnValue = "member_seq", allocationSize = 1)
public class Member extends BaseEntity {

//    @Id
//    private Long id;
//    private String name;
//    private int age;
//
//    public Member() {
//    }
//
//    public Member(Long id, String name) {
//        this.id = id;
//        this.name = name;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }


//    @Id
//    private Long id;
//
//    @Column(name = "name", nullable = false)
//    private String username;
//
//    private Integer age;
//
//    @Enumerated(EnumType.STRING)
//    private RoleType roleType;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date createDate;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date lastModifiedDate;
//
////    // 최신 하이버네이트 지원
////    private LocalDate localDate;
////    private LocalDateTime localDateTime;
//
//    @Lob    // 문자는 CLOB, 나머지는 BLOB
//    private String description;
//
////    @Transient  // 필드매핑 안함
////    private int tmp;
//
//    public Member() {
//    }

//
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, // AUTO, IDENTITY, SEQUENCE, TABLE
//            generator = "member_seq_generator")
//    private Long id;
//
//    @Column(name = "name", nullable = false)
//    private String username;
//
//    public Member() {
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }


    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

//    @Column(name = "TEAM_ID")
//    private Long teamId;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;


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

    public Team getTeam() {
        return team;
    }

//    public void setTeam(Team team) {
//        this.team = team;
//    }

    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }
}
