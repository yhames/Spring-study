package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
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

            Member member1 = new Member();
            member1.setUsername("A");

            Member member2 = new Member();
            member2.setUsername("B");

            Member member3 = new Member();
            member3.setUsername("C");

            System.out.println("============");

            em.persist(member1);    // 1, 51
            em.persist(member2);    // Memory
            em.persist(member3);    // Memory

            System.out.println("member1 = " + member1.getId());
            System.out.println("member2 = " + member2.getId());
            System.out.println("member3 = " + member3.getId());

            System.out.println("============");

            tx.commit();
//            em.remove(member);  // 삭제, 영속에서 삭제
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
