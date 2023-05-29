package likelion.springbootsunny.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@NoArgsConstructor
/*
* Member 클래스를 정의합니다. 이 클래스는 회원을 나타내는 엔티티로 사용될 것입니다.
* */
public class Member {
    @Id @GeneratedValue
    private Long id;

    /*
    * String 타입의 name 필드를 선언합니다. 이 필드는 회원의 이름을 나타냅니다.
    * */
    private String name;

    /*
    * @OneToMany 애너테이션은 일대다 관계를 나타내는 매핑을 지정합니다. mappedBy 속성은 관계를 맺고 있는 반대쪽 엔티티의 필드를 지정합니다.
    * 이 경우, "member"라는 필드를 사용하여 Member 엔티티와 Order 엔티티의 관계를 매핑합니다.
    * List<Order> 타입의 orderList 필드를 선언합니다. 이 필드는 Order 엔티티와의 일대다 관계를 나타내는데 사용될 것입니다.
    * 초기화는 빈 ArrayList로 이루어집니다.
    * */
    @OneToMany(mappedBy = "member")
    private List<Order> orderList = new ArrayList<>();

    /*
    * @Embedded 애너테이션은 Address 클래스를 임베디드(내장) 타입으로 사용한다는 것을 나타냅니다. Address 필드가 엔티티의 일부로 저장될 것임을 의미합니다.
    * Address 타입의 address 필드를 선언합니다. 이 필드는 회원의 주소를 나타냅니다.
    * */
    @Embedded
    private Address address;

    /*
    * public static 접근 제어자와 Member 객체를 생성하는 createMember 정적 메서드를 선언합니다.
    * 이 메서드는 주어진 이름과 주소를 사용하여 회원을 생성합니다.
    * Member member = new Member();
    * Member 객체를 생성합니다.
    * member.name = name;
    * member 객체의 name 필드에 주어진 name 값을 설정합니다.
    * member.address = address;
    * member 객체의 address 필드에 주어진 address 값을 설정합니다.
    * return member;
    * 생성된 member 객체를 반환합니다.
    * */
    public static Member createMember(String name, Address address) {
        Member member = new Member();
        member.name = name;
        member.address = address;
        return member;
    }
}