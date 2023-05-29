package likelion.springbootsunny.domain;
/*
*Jakarta Persistence API (JPA)에서 사용되는 주석입니다.
*이 주석은 엔티티 클래스 내에서 내장(embeddable) 타입을 나타내는 클래스임을 나타냅니다.
*Embeddable 클래스는 JPA 엔티티 클래스에 포함될 수 있는 재사용 가능한 구성 요소입니다.
*이 클래스는 데이터베이스 테이블의 특정 열 집합을 표현하고, 해당 열들을 엔티티 클래스의 일부로 취급할 수 있도록 합니다.
**/
import jakarta.persistence.Embeddable;
/*
*lombok.AllArgsConstructor은 Lombok 라이브러리에서 제공하는 주석입니다.
*이 주석을 사용하면 모든 필드를 가진 생성자를 자동으로 생성할 수 있습니다.
*@AllArgsConstructor 주석을 사용하면 클래스에 대한 모든 필드에 대한 생성자가 자동으로 추가됩니다.
*이 생성자는 인스턴스 변수의 값을 매개변수로 받아 해당 변수들을 초기화하는 역할을 합니다.
**/
import lombok.AllArgsConstructor;
/*
*lombok.Data는 Lombok 라이브러리에서 제공하는 주석입니다.
*이 주석을 사용하면 클래스의 기본적인 메서드들을 자동으로 생성할 수 있습니다.
*@Data 주석을 사용하면 Lombok은 다음과 같은 메서드를 자동으로 생성합니다:
*Getter: 클래스의 모든 필드에 대한 Getter 메서드를 생성합니다.
*Setter: 클래스의 모든 필드에 대한 Setter 메서드를 생성합니다.
*equals, hashCode: 객체의 동등성 비교를 위한 equals()와 해시 코드 생성을 위한 hashCode() 메서드를 생성합니다.
*toString: 객체의 문자열 표현을 생성하는 toString() 메서드를 생성합니다.
**/
import lombok.Data;
/*
*lombok.NoArgsConstructor는 Lombok 라이브러리에서 제공하는 주석입니다.
*이 주석을 사용하면 파라미터가 없는 기본 생성자를 자동으로 생성할 수 있습니다.
*@NoArgsConstructor 주석을 사용하면 Lombok은 해당 클래스에 대한 파라미터가 없는 기본 생성자를 자동으로 생성합니다.
*이 생성자는 인스턴스를 만들 때 아무런 인자를 전달하지 않고도 객체를 초기화할 수 있도록 합니다.
**/
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor

/*
* Address라는 클래스를 정의하는 예시입니다.
*이 클래스는 주소를 나타내는 데이터를 저장하기 위해 사용됩니다.
* 각 필드는 주소의 도시, 주(혹은 시/도), 거리, 우편번호를 나타냅니다.
* */
public class Address {
    private String city;
    private String state;
    private String street;
    private String zipcode;
}