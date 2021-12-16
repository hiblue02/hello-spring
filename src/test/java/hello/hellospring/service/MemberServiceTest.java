package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    void afterEach(){
        memberRepository.clearStore();
    }

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