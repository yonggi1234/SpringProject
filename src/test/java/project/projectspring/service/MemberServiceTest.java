package project.projectspring.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project.projectspring.domain.Member;
import project.projectspring.repository.MemberRepository;


@SpringBootTest
class MemberServiceTest {

    private final MemberRepository  memberRepository;
    private final MemberService memberService;

    @Autowired
    public MemberServiceTest(MemberRepository memberRepository, MemberService memberService){
        this.memberRepository = memberRepository;
        this.memberService = memberService;
    };

    @AfterEach
    public void afterEach(){ memberRepository.clearStore();}

    @Test
    void join() {
        //given
        Member m1 = new Member();
        m1.setName("abc");
        //when
        Long rs = memberService.join(m1);

        //then
        Member find_Id = memberService.findOne(rs).get();
        Assertions.assertThat(rs).isEqualTo(find_Id.getId());

    }

    @Test
    void Duplicate(){
        //given
        Member m1 = new Member();
        m1.setName("abc");
        memberRepository.save(m1);

        //when
        try {
            Member m2 = new Member();
            m2.setName("abc");
            memberRepository.save(m2);
        }
        //then
        catch (IllegalStateException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
    }

}