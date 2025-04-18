package project.projectspring.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import project.projectspring.repository.MemberRepository;
import project.projectspring.repository.MemoryMemberRepository;
import project.projectspring.service.MemberService;

@Configuration
public class AppConfig {
    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }
}
