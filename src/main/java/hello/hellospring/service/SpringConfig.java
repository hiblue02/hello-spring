package hello.hellospring.service;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.JpaMemberRepository;
import hello.hellospring.repository.MemberRepsository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final MemberRepsository memberRepsository;

    @Autowired
    public SpringConfig(MemberRepsository memberRepsository) {
        this.memberRepsository = memberRepsository;
    }
    //    private final EntityManager em;
//    private final DataSource dataSource;

//    public SpringConfig(EntityManager em, DataSource dataSource) {
//        this.em = em;
//        this.dataSource = dataSource;
//    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepsository);
    }

//    @Bean
//    public TimeTraceAop timeTraceAop(){
//        return new TimeTraceAop();
//    }
//    @Bean
//    public MemberService memberService() {
//        return new MemberService(memberRepsository());
//    }

//    @Bean
//    public MemberRepsository memberRepsository(){
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource);
//        return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository(em);
//    }
}
