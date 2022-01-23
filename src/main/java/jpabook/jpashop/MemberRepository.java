package jpabook.jpashop;

import jpabook.jpashop.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberRepository {
    //@PersistenceContext 어노테이셔이 있으면, 스프링 컨테이너(boot-starter-jpa)가 EntityManager를 자동 주입해줌.
    @PersistenceContext
    private EntityManager em;

    public Long save(Member member) {
        em.persist(member);
        return member.getId();
        //member를 반환하면 되지 왜 getId를?
        //command와 query 구분하기
        //저장(command성) return값 거의 안만듬. 대신 Id 있으면 값 다시 조회할 수 있으니까. Id 정도 조회하는 것만 설계함.

    }

    public Member find(Long id) {
        return em.find(Member.class, id);
    }

}
