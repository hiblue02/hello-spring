package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepsository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {

    private final MemberRepsository memberRepository;

    @Autowired
    public MemberService(MemberRepsository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    @SneakyThrows
    public long join(Member member){
            validateDuplicateMember(member);
            memberRepository.save(member);
            return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalArgumentException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     * @return
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
