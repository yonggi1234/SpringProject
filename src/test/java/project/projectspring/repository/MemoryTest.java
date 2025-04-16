package project.projectspring.repository;

//import org.junit.jupiter.api.Assertions;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import project.projectspring.domain.Member;

import java.util.List;
import java.util.Optional;

public class MemoryTest {
    MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    // 각 테스트 실행 순서는 보장 안되기에 개별 테스트 끝나면 메모리 초기화
    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("firstname");

        memberRepository.save(member);
        Member rs = memberRepository.findById(member.getId()).get();
        //그냥 콘솔에 띄울 경우
        //System.out.println("rs == " + rs + " is " + (rs == member));
//        Assertions.assertEquals(member, rs);
        Assertions.assertThat(member).isEqualTo(rs);
    }

    @Test
    public void findByName() {
        Member m1 = new Member();
        m1.setName("firstname");
        memberRepository.save(m1);

        Member m2 = new Member();
        m2.setName("secondname");
        memberRepository.save(m2);

        Member rs = memberRepository.findByName("firstname").get();
        Assertions.assertThat(rs).isEqualTo(m1);
    }

    @Test
    public void findById() {
        Member m1 = new Member();
        m1.setName("firstname");
        memberRepository.save(m1);

        Member m2 = new Member();
        m2.setName("secondname");
        memberRepository.save(m2);

        List<Member> rs = memberRepository.findAll();
        Assertions.assertThat(rs.size()).isEqualTo(2);

    }
}
