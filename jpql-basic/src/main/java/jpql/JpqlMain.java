package jpql;

import javax.persistence.*;
import java.util.List;

public class JpqlMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Team team1 = new Team();
            team1.setName("team1");
            em.persist(team1);

            Team team2 = new Team();
            team2.setName("team2");
            em.persist(team2);

            for (int i = 0; i < 100; i++) {
                Member member = new Member();
                member.setUsername("member" + i);
                member.setAge(i);
                if (i < 30) {
                    member.setTeam(team1);
                } else if (i < 60) {
                    member.setTeam(team2);
                }
                em.persist(member);
            }


            TypedQuery<Member> query1 = em.createQuery("select m from Member as m", Member.class);
            TypedQuery<String> query2 = em.createQuery("select m.username from Member as m", String.class);
            Query query3 = em.createQuery("select m.username, m.age from Member as m");

            // 결과가 1개 이상일 때, 없으면 빈 리스트 반환
            List<Member> resultList = query1.getResultList();
            for (Member result : resultList) {
                System.out.println("getResultList = " + result);
            }

            // 결과가 "정확히" 1개일 떄, 없거나 둘 이상이면 Exception
            Member result = em.createQuery("select m from Member m where m.id=10L", Member.class)
                    .getSingleResult();
            System.out.println("getSingleResult = " + result);

            // 파라미터 바인딩
            Member singleResult = em.createQuery("select m from Member as m where m.username=:username", Member.class)
                    .setParameter("username", "member1")
                    .getSingleResult();
            System.out.println("singleResult = " + singleResult);

            // 엔티티: 프로젝션 영속성컨텍스트 관리
            em.flush();
            em.clear();
            List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();
            Member findMember = members.get(0);
            findMember.setAge(20);

            // 엔티티: 조인쿼리
            List<Team> teamList = em.createQuery("select t from Member as m join m.team t", Team.class)
                    .getResultList();
            for (Team team : teamList) {
                System.out.println("team = " + team);
            }

            // 임베디드타입
            List<Address> addressList = em.createQuery("select o.address from Order as o", Address.class)
                    .getResultList();
            for (Address address : addressList) {
                System.out.println("address = " + address);
            }

            // 스칼라타입: DISTINCT
            List<Object[]> scalaList = em.createQuery("select distinct m.username, m.age from Member as m").getResultList();
//            Object[] scala = (Object[]) scalaList.get(0);
            Object[] scala = scalaList.get(0);
            System.out.println("scala.username = " + scala[0]);
            System.out.println("scala.age = " + scala[1]);

            // 스칼라타입: "new" operator with DTO
            List<MemberDTO> dtoList = em.createQuery("select new jpql.MemberDTO(m.username, m.age) from Member as m", MemberDTO.class)
                    .getResultList();
            MemberDTO memberDTO = dtoList.get(0);
            System.out.println("memberDTO.username = " + memberDTO.getUsername());
            System.out.println("memberDTO.age = " + memberDTO.getAge());

            // 페이징
            List<Member> orderList = em.createQuery("select m from Member as m order by m.age desc", Member.class)
                    .setFirstResult(1)
                    .setMaxResults(10)
                    .getResultList();
            System.out.println("orderList.size = " + orderList.size());
            for (Member member : orderList) {
                System.out.println("member = " + member);
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

        emf.close();
    }
}
