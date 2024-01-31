package hellojpa;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.time.LocalDateTime;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = new Member();
            member.setName("member1");
            member.setAge(18);
            member.setAddress(new Address("seoul", "street", "1234"));

            Team team = new Team();
            team.setName("team1");
            em.persist(team);

            member.setTeam(team);

            em.persist(member);

            em.flush();
            em.clear();

            // Paging
            List<Member> members = em.createQuery("select m from Member m order by m.age desc", Member.class).setFirstResult(0).setMaxResults(10)
                    .getResultList();
            for (Member member1 : members) {
                System.out.println("member1 = " + member1);
                System.out.println("member1.getTeam() = " + member1.getTeam());
            }

            // Inner Join
            em.clear();
            List<Member> membersWithTeam = em.createQuery("select m from Member m join m.team t", Member.class).getResultList();
            System.out.println("membersWithTeam.get(0).getTeam() = " + membersWithTeam.get(0).getTeam());

            // Case 식
            List<String> resultList = em.createQuery("select " +
                    "case when m.age <= 10 then '학생요금' " +
                    "when m.age >= 60 then '경로요금' " +
                    "else '일반요금' " +
                    "end " +
                    "from Member m", String.class).getResultList();
            for (String s : resultList) {
                System.out.println("s = " + s);
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
