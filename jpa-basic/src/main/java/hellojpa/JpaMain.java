package hellojpa;

import org.hibernate.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
//            // Create
//            Member member = new Member();
//            member.setId(2L);
//            member.setName("HelloB");
//            em.persist(member);

//            // Retrieve
//            Member findMember = em.find(Member.class, 1L);
//            System.out.println("findMember.id = " + findMember.getId());
//            System.out.println("findMember.name = " + findMember.getName());

//            // Delete
//            Member findMember = em.find(Member.class, 1L);
//            em.remove(findMember);

//            // Update
//            Member findMember = em.find(Member.class, 1L);
//            findMember.setName("HelloJPA"); // Query would be created and transmitted when tx.commit()

//            // Total Member retrieve using JPQL
//            List<Member> result = em.createQuery("select m from Member as m ", Member.class)
//                    .setFirstResult(5)
//                    .setMaxResults(8)
//                    .getResultList();
//
//            for (Member member : result) {
//                System.out.println("member.name  = " + member.getName());
//            }


//            // 비영속
//            Member member = new Member();
//            member.setId(101L);
//            member.setName("HelloJPA");
//
//            // 영속
//            System.out.println("=== BEFORE ===");
//            em.persist(member);
////            em.detach(member);  // 준영속, 영속 상태의 엔티티가 영속 컨텍스트에서 분리
//            System.out.println("=== AFTER ===");
//
//            Member findMember = em.find(Member.class, 101L);
//            System.out.println("findMember.id = " + findMember.getId());
//            System.out.println("findMember.name = " + findMember.getName());  // 조회(select) 쿼리 사용하지 않음

//            Member findMember1 = em.find(Member.class, 101L);   // 조희(select) 쿼리 사용, 1차 캐시에 저장
//            Member findMember2 = em.find(Member.class, 101L);   // 조히(select) 쿼리 미사용, 1차 캐시 사용
//            // 1차 캐시로 반복가능 읽기 등급의 트랜잭션 격리 수준을 데이터베이스가 아닌 어플리케이션 차원에서 제공
//            System.out.println("result = " + (findMember1 == findMember2)); // 같은 영속 컨텍스트/트랜잭션 안에서 동일성 보장


//            Member member1 = new Member(150L, "A");
//            Member member2 = new Member(160L, "B");
//
//            em.persist(member1);    // 영속컨텍스트의 쓰지 지연 SQL 저장소에 저장
//            em.persist(member2);    // 영속컨텍스트의 쓰지 지연 SQL 저장소에 저장
////            persistence.xml의 jdbc.batch_size property를 사용하여 배치 혹은 버퍼링 사용할 수 있음
//
//            System.out.println("=================== ");


//            Member member = em.find(Member.class, 150L);
//            member.setName("Z");
//
//            System.out.println("=================== ");


//            Member member = new Member(200L, "member200");
//            em.persist(member);
//
//            em.flush(); // 쓰기지연 SQL 저장소 쿼리 호출
//
//            System.out.println("====================");


//            Member member = em.find(Member.class, 150L);
//            member.setName("AAAAA");
//
//            em.detach(member);  // 수정 내용이 영속되지 않음
////            em.clear();


//            Member member1 = new Member();
//            member1.setUsername("A");
//
//            Member member2 = new Member();
//            member2.setUsername("B");
//
//            Member member3 = new Member();
//            member3.setUsername("C");
//
//            System.out.println("============");
//
//            em.persist(member1);    // 1, 51
//            em.persist(member2);    // Memory
//            em.persist(member3);    // Memory
//
//            System.out.println("member1 = " + member1.getId());
//            System.out.println("member2 = " + member2.getId());
//            System.out.println("member3 = " + member3.getId());
//
//            System.out.println("============");


//            // 저장
//            Team team = new Team();
//            team.setName("TeamA");
//            em.persist(team);
//
//            Member member = new Member();
//            member.setUsername("member1");
////            member.setTeamId(team.getId());
////            member.setTeam(team); // 연관관계 주인의 값을 바꾸면 쿼리가 나간다.
////            team.getMembers().add(member);  // 객체 상태를 위해 양방향 모두 값을 입력하거나,
//            member.changeTeam(team);    // 연관관계 편의 메서드 사용한다.
//
//            em.persist(member);
//
//            // 영속성 컨텍스트 초기화
////            em.flush();
////            em.clear();
//
//            // 조회
//            Member findMember = em.find(Member.class, member.getId());
//
////            Long findTeamId = findMember.getTeamId();
////            Team findTeam = em.find(Team.class, findTeamId);
//
////            Team findTeam = findMember.getTeam();
////            System.out.println("findTeam.getName() = " + findTeam.getName());
//            System.out.println("==========");
//            List<Member> members = findMember.getTeam().getMembers();
//            for (Member m : members) {
//                System.out.println("m.getUsername() = " + m.getUsername());
//            }
//            System.out.println("==========");


//            Movie movie = new Movie();
//            movie.setName("movie1");
//            movie.setDirector("director1");
//            movie.setActor("actor1");
//            movie.setPrice(10000);
//
//            em.persist(movie);
//
//            em.flush();
//            em.clear();
//
//            Movie findMovie = em.find(Movie.class, movie.getId());
//            System.out.println("findMovie = " + findMovie);


//            Member member = new Member();
//            member.setUsername("user1");
//            member.setCreatedBy("park");
//            member.setCreatedDate(LocalDateTime.now());
//
//            em.persist(member);


//            Member member1 = new Member();
//            member1.setUsername("member1");
//
//            Member member2 = new Member();
//            member2.setUsername("member2");
//
//            em.persist(member1);
//            em.persist(member2);
//            em.flush();
//            em.clear();
//
////            Member findMember = em.find(Member.class, member.getId());
//            Member refMember1 = em.getReference(Member.class, member1.getId());
//            Member findMember2 = em.find(Member.class, member2.getId());
//            System.out.println("refMember1.class = " + refMember1.getClass());
//            System.out.println("refMember1.id = " + refMember1.getId());
//            System.out.println("refMember1.name = " + refMember1.getUsername());
//            logic(refMember1, findMember2);
//            System.out.println("=================");
//
//            Member refMember2 = em.getReference(Member.class, member2.getId());
//            System.out.println("findMember2.class = " + findMember2.getClass());
//            System.out.println("refMember2.class = " + refMember2.getClass());
//            logic(refMember2, findMember2);
//            System.out.println("=================");
//
//            Member findMember1 = em.find(Member.class, member1.getId());
//            System.out.println("refMember1.class = " + refMember1.getClass());
//            System.out.println("findMember1.class = " + findMember1.getClass());
//            logic(refMember1, findMember1);
//            System.out.println("=================");


//            Member member1 = new Member();
//            member1.setUsername("member1");
//
//            em.persist(member1);
//            em.flush();
//            em.clear();
//
//            Member refMember1 = em.getReference(Member.class, member1.getId());
//            System.out.println("refMember1.class = " + refMember1.getClass());
////            em.detach(refMember1);
////            System.out.println("refMember1 = " + refMember1.getUsername());
//            System.out.println("is Loaded = " + emf.getPersistenceUnitUtil().isLoaded(refMember1));
////            refMember1.getUsername();
//            Hibernate.initialize(refMember1);
//            System.out.println("is Loaded = " + emf.getPersistenceUnitUtil().isLoaded(refMember1));


//            Team team1 = new Team();
//            team1.setName("team1");
//            em.persist(team1);
//
//            Team team2 = new Team();
//            team2.setName("team2");
//            em.persist(team2);
//
//            Member member1 = new Member();
//            member1.setUsername("member1");
//            member1.setTeam(team1);
//            em.persist(member1);
//
//            Member member2 = new Member();
//            member2.setUsername("member2");
//            member2.setTeam(team2);
//            em.persist(member2);
//
//            em.flush();
//            em.clear();
//
////            Member m = em.find(Member.class, member.getId());
////            System.out.println("m.getTeam().getClass() = " + m.getTeam().getClass());
////            System.out.println("=====================");
////            m.getTeam().getName();
//            System.out.println("=====================");
//
//            List<Member> members = em.createQuery("select m from Member m join fetch m.team  ", Member.class)
//                    .getResultList();




//            Parent parent = new Parent();
//            Child child1 = new Child();
//            Child child2 = new Child();
//
//            parent.addChild(child1);
//            parent.addChild(child2);
//
//            em.persist(parent);
//            em.persist(child1);
//            em.persist(child2);
//
//            em.flush();
//            em.clear();
//
//            Parent findParent = em.find(Parent.class, parent.getId());
////            findParent.getChildList().remove(0);
//
//            em.remove(findParent);


            Address address = new Address("city", "street", "zipcode");

            Member member1 = new Member();
            member1.setUsername("member1");
            member1.setHomeAddress(address);
            em.persist(member1);

            Address copyAddress = new Address(address.getCity(), address.getStreet(), address.getZipcode());

            Member member2 = new Member();
            member2.setUsername("member2");
            member2.setHomeAddress(copyAddress);
            em.persist(member2);

//            member1.getHomeAddress().setCity("newCity");

            tx.commit();
//            em.remove(mem ber);  // 삭제, 영속에서 삭제
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

        emf.close();
    }

//    private static void logic(Member refMember, Member findMember) {
//        System.out.println("refMember == findMember : " + (refMember.getClass() == findMember.getClass()));
//        System.out.println("refMember instance of Member : " + (refMember instanceof Member));
//        System.out.println("findMember instance of Member : " + (findMember instanceof Member));
//    }
}
