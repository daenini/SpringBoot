package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;
    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    } // 스프링 데잍머 JPA

    /*
        private EntityManager em;
        private DataSource dataSource;
        @Autowired
        public SpringConfig(DataSource dataSource, EntityManager em) {
            this.dataSource = dataSource;;
            this.em = em;
        }
        */
    /*
        private DataSource dataSource;
        @Autowired
        public SpringConfig(DataSource dataSource) {
            this.dataSource = dataSource;
        }
        */
    @Bean
    public MemberService memberService(){
        return  new MemberService(memberRepository);
    }
/*
    @Bean
    public MemberRepository memberRepository(){
        //return new MemoryMemberRepository(); -> DB를 사용하지 않을때
        //return new JdbcMemberRepository(dataSource); -> 순수JDBC
        //return  new JdbcTempletMemberRepository(dataSource); ->스프링 JDBCtemplate
        //return  new JpaMemberRepository(em); ->JPA
    }

 */
}
