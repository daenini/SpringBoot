package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

//@Repository
public class MemoryMemberRepository implements MemberRepository{
    private  static Map<Long, Member> store = new HashMap<>();
    //sava할때 저장하는 Map
    private  static  long sequence = 0L;
    // save 하고  store의 ID값을 생성해줌
/*
 Optional<T> 클래스
    - java8에서는 Optional<T>클래스를 사용해 NPE(null point Exception)를 발지할 수 있도록 도와준다. null이 올 수 있는 값을
    감싸는 Wrapper 클래스로, 참조하더라도 NPE가 발생하지 않도록 도와준다.
    Optional 클래스는 아래와 같은 value에 값을 저장하기 때문에 값이 null이더라도 바로 NPE가 발생하지 않는다.
 */
    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); //store.get(id)가 null일 수도 있고 아닐수도 있기 때문에
    }

    @Override
    public Optional<Member> findByName(String name) {
       return store.values().stream()
                .filter(member -> member.getName().equals(name)) 
                .findAny(); 
       /*
       store의 values값을 하나하나 filter 해서 member의 이름과 파라미터 name이 값으면
       findAny(해당 스트림에서 첫번째 요소를 참조하는 객체를 반환)해준다.
        */
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
