package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepsository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepsository memberRepository;

    @Test
    void join() {
        //given
        Member member = new Member();
        member.setName("spring");
        
        //when
        Long saveId = memberService.join(member);
        
        //then
        Member findMember = memberService.findOne(saveId).get();
        assertEquals(member.getName(), findMember.getName());
    }

    @Test
    void 중복회원예약(){
        //given
        Member member1 = new Member();
        member1.setName("spring");
        //when
        Member member2 = new Member();
        member2.setName("spring");

        //then
        memberService.join(member1);
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> memberService.join(member2));
        assertEquals("이미 존재하는 회원입니다.", e.getMessage());

//        try {
//            memberService.join(member2);
//            fail();
//        }catch (IllegalArgumentException e){
//            assertEquals("이미 존재하는 회원입니다.", e.getMessage());
//        }


    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}