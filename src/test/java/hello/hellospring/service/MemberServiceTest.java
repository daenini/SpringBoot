package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberServiceTest {
    MemberService memberService;
    MemoryMemberRepository memberRepository =  new MemoryMemberRepository();

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }
    // test에서는 메소드를 한글로 적는 경우도 있음
    @Test
    void 회원가입() {
        //given 뭔가를 주어질때
        Member member = new Member();
        member.setName("hello");
        //when 그걸 실행했을때
        Long saveId = memberService.join(member);
        //then 결과
       Member findMember= memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }
    @Test
    public  void  중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");
        
        Member member2 = new Member();
        member2.setName("spring");
        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다."); //exception 날라오는 message가 같다면
        /*
        try {
            memberService.join(member2);
            fail("예외가 발생해야 합니다.");
        }catch (IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
        */
        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}