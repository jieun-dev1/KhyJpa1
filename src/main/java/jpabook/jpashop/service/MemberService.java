package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;


    @Transactional
    //회원 가입
    public Long join(Member member) {
        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    //WAS 여러개가 돌 때. MEMBER A 가 동시에 INSERT 하면, DUPLICATE MEMBER 를 통과하면 둘다 동시에 호출함. 그러면 문제가 될 수 있다.
    //실무에서는 한번 더 최후 방어를 해야함 (멀티쓰레드 대비) 데이터 베이스에 MEMBER Name을 unique 제약 조건으로 잡아야 함.
    private void validateDuplicateMember(Member member){
        //EXCEPTION
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }

    }

    //회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    //회원 단건 조회
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}
