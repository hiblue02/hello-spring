package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repsository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
        repsository.clearStore();
    }


    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repsository.save(member);
        Member result = repsository.findById(member.getId()).get();
        assertEquals(member, result);

    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repsository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repsository.save(member2);

        Member result = repsository.findByName("spring1").get();

        assertEquals(member1, result);
    }
    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repsository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repsository.save(member2);

        List<Member> result = repsository.findAll();

        assertEquals(result.size(), 2);
    }
}