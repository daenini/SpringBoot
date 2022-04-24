package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach //메소드 한번 끝날때 마다 호출됨
    public  void  afterEach(){
        repository.clearStore();
        /*
        이 메소드가 없으면 테스트를 클래스로 돌릴때 이미 데이터가 저장소인 store에 있어서 오류가 발생한다
        따라서 하나의 메소드가 끝날때 마다 이 메소드를 실행 시켜줘서 store에 있는 데이터를 clear 해준 뒤 다음 메소드를 실행 시켜준다.
         */
    }

    @Test
    public  void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);
        Member result = repository.findById(member.getId()).get();
        // Assertions.assertEquals(member, result); // member 와 result가 같은지 같다면 run할때 오류가 안뜸  ->jupiter.Assertions
        assertThat(member).isEqualTo(result);  //->  assertj.Assertions
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

         Member result = repository.findByName("spring1").get();
         assertThat(result).isEqualTo(member1);
    }

    @Test
    public  void findAll(){
        Member member1 =  new Member();
        member1.setName("spring1");
        repository.save(member1);
        
        Member member2 =  new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }

}
