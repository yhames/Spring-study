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

            Member member1 = new Member();
            member1.setUsername("member1");
            member1.setAge(10);
            member1.setTeam(team1);

            em.persist(member1);

            TypedQuery<Member> query1 = em.createQuery("select m from Member as m", Member.class);
            TypedQuery<String> query2 = em.createQuery("select m.username from Member as m", String.class);
            Query query3 = em.createQuery("select m.username, m.age from Member as m");

            // 결과가 1개 이상일 때, 없으면 빈 리스트 반환
            List<Member> resultList = query1.getResultList();
            for (Member result : resultList) {
                System.out.println("getResultList = " + result.getUsername());
            }

            // 결과가 "정확히" 1개일 떄, 없거나 둘 이상이면 Exception
            Member result = em.createQuery(("select m from Member m where m.id=" + member1.getId()), Member.class)
                    .getSingleResult();
            System.out.println("getSingleResult = " + result.getUsername());

            // 파라미터 바인딩
            Member singleResult = em.createQuery("select m from Member as m where m.username=:username", Member.class)
                    .setParameter("username", "member1")
                    .getSingleResult();
            System.out.println("singleResult = " + singleResult.getUsername());

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
                System.out.println("team.name = " + team.getName());
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
