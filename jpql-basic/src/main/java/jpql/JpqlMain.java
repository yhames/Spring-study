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

            Team team3 = new Team();
            team3.setName("team3");
            em.persist(team3);

            for (int i = 0; i < 100; i++) {
                Member member = new Member();
                member.setAge(i);
                if (i < 30) {
                    member.setUsername("member" + i);
                    member.setTeam(team1);
                    member.setType(MemberType.ADMIN);
                } else if (i < 60) {
                    member.setUsername("member" + i);
                    member.setTeam(team2);
                    member.setType(MemberType.USER);
                } else {
                    member.setUsername(null);
                    member.setTeam(team3);
                    member.setType(MemberType.ADMIN);
                }
                em.persist(member);
            }


            TypedQuery<Member> query1 = em.createQuery("select m from Member as m", Member.class);
            TypedQuery<String> query2 = em.createQuery("select m.username from Member as m", String.class);
            Query query3 = em.createQuery("select m.username, m.age from Member as m");

            // 결과가 1개 이상일 때, 없으면 빈 리스트 반환
            List<Member> resultList = query1.getResultList();
            System.out.println("resultList.size = " + resultList.size());

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
            findMember.setUsername("team1");
            findMember.setAge(20);

            // 엔티티: 조인쿼리
            List<Team> teamList = em.createQuery("select t from Member as m join m.team t", Team.class)
                    .getResultList();
            System.out.println("teamList.size = " + teamList.size());

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

            // Inner Join, inner operator can be omitted
            List<Member> innerJoinList = em.createQuery("select m from Member as m inner join m.team t where t.name=:teamName", Member.class)
                    .setParameter("teamName", team1.getName())
                    .getResultList();
            System.out.println("innerJoinList.size = " + innerJoinList.size());

            // Outer Join, outer operator can be omitted
            List<Member> outerJoinList = em.createQuery("select m from Member as m left outer join m.team t", Member.class)
                    .getResultList();
            System.out.println("outerJoinList.size = " + outerJoinList.size());

            // Theta Join
            List<Member> thetaJoinList = em.createQuery("select m from Member m, Team t where m.username = t.name", Member.class)
                    .getResultList();
            for (Member member : thetaJoinList) {
                System.out.println("member = " + member);
            }

            // 외부조인 필터링 with "ON" statement
            List<Object[]> filterJoinList = em.createQuery("select m, t from Member m left join m.team t on t.name = 'team2'", Object[].class)
                    .getResultList();
            System.out.println("filterJoinList.size = " + filterJoinList.size());
            for (Object[] objects : filterJoinList) {
                System.out.println(objects[0] + " = " + objects[1]);
            }

            // 연관관계 없는 엔티티 외부조인
            List<Object[]> filterJoinList2 = em.createQuery("select m, t from Member m left join Team t on m.username=t.name", Object[].class)
                    .getResultList();
            System.out.println("filterJoinList2.size = " + filterJoinList2.size());
            for (Object[] objects : filterJoinList2) {
                System.out.println(objects[0] + " = " + objects[1]);
            }

            // Enumerate Type 표현
            List<Object[]> typeList = em.createQuery("select m.username, 'HELLO', TRUE from Member m " +
                            "where m.type = jpql.MemberType.ADMIN", Object[].class) // Enumerate 패키지명을 써야함
                    .getResultList();
            System.out.println("typeList.size = " + typeList.size());
//            for (Object[] objects : typeList) {
//                System.out.println("objects = " + objects[0] + " " + objects[1] + " " + objects[2]);
//            }

            // Enumerate Type 표현 - 파라미터 바인딩
            List<Object[]> typeList2 = em.createQuery("select m.username, 'HELLO', TRUE from Member m " +
                            "where m.type =:userType", Object[].class)
                    .setParameter("userType", MemberType.USER)  // 파라미터 바인딩 가능
                    .getResultList();
            System.out.println("typeList2.size = " + typeList2.size());

            // 조건식 - CASE
            String query =
                    "select " +
                            "case when m.age <= 10 then '학생요금' " +
                            "     when m.age >= 60 then '경로요금' " +
                            "     else '일반요금' " +
                            "end " +
                            "from Member m";
            List<String> caseList = em.createQuery(query, String.class).getResultList();
            for (String s : caseList) {
                System.out.println("s = " + s);
            }

            // 조건식 - coalesce
            List<String> coalesceList = em.createQuery("select coalesce(m.username, '이름 없는 회원') " +
                            "as username " +
                            "from Member m", String.class)
                    .getResultList();
            for (String s : coalesceList) {
                System.out.println("s = " + s);
            }

            // 조건식 - nullif
            List<String> nullList = em.createQuery("select nullif(m.username, 'member23') " +
                            "from Member m", String.class)
                    .getResultList();
            System.out.println("nullLst = " + nullList.get(22));
            System.out.println("nullLst = " + nullList.get(23));
            System.out.println("nullLst = " + nullList.get(24));

            // JPQL basic function
            List<Integer> resultList1 = em.createQuery("select size(t.members) from Team t", Integer.class)
                    .getResultList();   // 5.4.14.Final -> 5.4.31.Final
            for (Integer integer : resultList1) {
                System.out.println("integer = " + integer);
            }

            // JPQL defined function
            // group_concat : List to String
//            List<String> resultList2 = em.createQuery("select function('group_concat', m.username) from Member m", String.class)
//                    .getResultList(); // JPA 표준 Query
            List<String> resultList2 = em.createQuery("select group_concat(m.username) from Member m", String.class)
                    .getResultList();   // Hibernate Query
            for (String s : resultList2) {
                int len = s.length();
                System.out.println("s = " + s.substring(0, 25) + "..." + s.substring(len-25, len));
            }

            // fetch join : Many to one
            em.flush();
            em.clear();
            List<Member> fetchList = em.createQuery("select m from Member m join fetch m.team", Member.class).getResultList();
            for (Member member : fetchList) {
                System.out.println("member = " + member.getUsername() + ", " + member.getTeam().getName());
            }


            // fetch join : One to Many with distinct
            em.flush();
            em.clear();
            List<Team> fetchList2 = em.createQuery("select distinct t from Team t join fetch t.members", Team.class).getResultList();
            for (Team team : fetchList2) {
                System.out.println("team = " + team.getName() + ", " + team.getMembers().size());
//                for(Member member : team.getMembers()) {
//                    System.out.println("     -> member = " + member);
//                }
            }

            /**
             * 1. 즉시로딩 N+1 문제 발생 -> 지연로딩 사용
             * 2. 지연로딩에서 객체그래프 탐색시 N+1문제 발생 -> fetch join 으로 해결 가능
             * 3. fetch join 불가능(성능저하, N*N) -> @Batchsize 설정하여 해결
             */
            // fetch join : paging X -> @Batchsize
            em.flush();
            em.clear();
            List<Team> fetchList3 = em.createQuery("select t from Team t", Team.class)
                    .setFirstResult(0)
                    .setMaxResults(10)
                    .getResultList();
            for (Team team : fetchList3) {
                System.out.println("team = " + team.getName() + ", members.size = " + team.getMembers().size());
                for (Member member : team.getMembers()) {
                    System.out.println("     -> member = " + member);
                }
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
