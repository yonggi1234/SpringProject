package project.projectspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.projectspring.domain.Member;
import project.projectspring.repository.MemberRepository;
import project.projectspring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    
    //생성자 주입
    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Long join(Member member) {

        duplicateMember(member); //중복 방지

        memberRepository.save(member);
        return member.getId();
    }

    private void duplicateMember(Member member) {
        Optional<Member> rs = memberRepository.findByName(member.getName());
        rs.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
        //Optional 반환하는걸 지향하는 코드
//        memberRepository.findByName(member.getName())
//                        .ifPresent(m -> {
//                            throw new IllegalStateException();
//                        });
    }

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }
    public Optional<Member> findOne(Long id){
        return memberRepository.findById(id);
    }
}
