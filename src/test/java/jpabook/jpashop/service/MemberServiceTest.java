package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

//JPA가 실제까지 DB에 도는 것 보기 위해서, 메모리모드로 DB 까지 엮어서 테스트.
//Spring이랑 integration해서 테스트하기 위해 Runwith SpringRunner,
//스프링부트 테스트 - 스프링 부트 띄운 상태로 Spring Test. 이거 없으면 AUTOWIRED 실패
//@Transactional - 걸고 테스트 돌림. 테스트 끝나면 롤백. 데이터 변경이 있으니, transactional로 롤백이 가능하게.

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    EntityManager em;

    @Test
    public void 회원가입() throws Exception {
        //given
        Member member = new Member();
        member.setName("kim");

        //when
       Long saveId = memberService.join(member);

        //then
        //memberRepository에서 찾아온 멤버 == 저장한 멤버가 같아야 함. @Transactional: 같은 transaction 내에서 id 값이 똑같으면 같은 영속성 컨텍스트에서 똑같은 애가 관리됨
        assertEquals(member, memberRepository.findOne(saveId));
    }
    
    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        //when
        memberService.join(member1);
          memberService.join(member2);


        //then
        fail("예외가 발생해야 한다.");

    }

}