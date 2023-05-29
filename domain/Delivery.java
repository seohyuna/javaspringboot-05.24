package likelion.springbootsunny.domain;
/*
 *코드는 JPA를 사용하기 위한 관련 클래스와 인터페이스들을 import하고 있다는 것을 의미합니다.
 *그러나 실제로는 JPA를 사용하는 엔티티 클래스가 정의되어 있지 않으므로 추가적인 코드가 필요합니다.
 * */
import jakarta.persistence.*;
/*
*lombok.Getter는 Lombok 라이브러리에서 제공하는 주석입니다.
*이 주석을 사용하면 자동으로 Getter 메서드를 생성할 수 있습니다.
* @Getter 주석을 필드에 적용하면 Lombok은 해당 필드에 대한 Getter 메서드를 자동으로 생성합니다.
*Getter 메서드는 해당 필드의 값을 반환하는 역할을 합니다.
* */
import lombok.Getter;
/*
*lombok.NoArgsConstructor는 Lombok 라이브러리에서 제공하는 주석입니다.
*이 주석을 사용하면 자동으로 기본 생성자를 생성할 수 있습니다.
* @NoArgsConstructor 주석을 클래스에 적용하면 Lombok은 해당 클래스에 파라미터 없는 기본 생성자를 자동으로 생성합니다.
* */
import lombok.NoArgsConstructor;


import static jakarta.persistence.EnumType.STRING;
import static likelion.springbootsunny.domain.Delivery.DeliveryStatus.ESTABLISHED;
import static lombok.AccessLevel.PROTECTED;

/*
*@Entity는 JPA(Java Persistence API)에서 제공하는 애너테이션 중 하나입니다.
*이 애너테이션은 클래스가 JPA 엔티티임을 나타내는 역할을 합니다.
*JPA 엔티티는 데이터베이스 테이블과 매핑되는 클래스를 의미합니다.
*@Entity 애너테이션을 사용하여 클래스에 지정하면 해당 클래스의 객체는 데이터베이스 테이블의 레코드와 일치하게 됩니다.
*JPA를 사용하여 엔티티를 조작하고 데이터베이스와 상호작용할 수 있게 됩니다.
* */
@Entity
/*
*@NoArgsConstructor(access = PROTECTED)는 Lombok에서 제공하는 주석인 @NoArgsConstructor를 사용하여 생성자를 생성하는데, 생성자의 접근 제한자를 PROTECTED로 지정하는 것을 의미합니다.
*@NoArgsConstructor 주석은 파라미터 없는 기본 생성자를 자동으로 생성하는 역할을 합니다.
*이때 생성자의 접근 제한자는 기본적으로 public이지만, access 속성을 사용하여 다른 접근 제한자로 변경할 수 있습니다.
* access 속성에 지정할 수 있는 값으로는 PUBLIC, PROTECTED, PACKAGE, PRIVATE 등이 있으며, 각각 해당 접근 제한자로 생성자를 생성합니다.
* */
@NoArgsConstructor(access = PROTECTED)
/*
*@Getter 주석을 필드에 적용하면 Lombok은 해당 필드에 대한 Getter 메서드를 자동으로 생성합니다. Getter 메서드는 해당 필드의 값을 반환하는 역할을 합니다.
* @Getter 주석은 코드 중복을 줄이고 필드 값을 쉽게 접근할 수 있도록 도와줍니다.
* Getter 메서드가 필요한 필드에 이 주석을 사용하면 자동으로 Getter 메서드를 생성할 수 있습니다.
* */
@Getter
/*
*Delivery라는 클래스를 정의하고 있습니다.
*JPA 엔티티로 사용되는 클래스로 보입니다.
*이 클래스는 배송 정보를 나타내는 역할을 합니다.*/
public class Delivery {
    /*
   *@Id: 해당 필드를 엔티티의 기본 키(primary key)로 지정합니다.
   *@GeneratedValue: 기본 키의 값을 자동으로 생성하기 위한 전략을 지정합니다. 전략에 따라 자동으로 값이 생성됩니다.
   *id 필드는 Delivery 엔티티의 기본 키로 사용되는 필드입니다. Long 타입으로 선언되어 있습니다.
   * */
   @Id @GeneratedValue
    private Long id;

    /*
    *@OneToOne: 일대일 관계를 나타내는 애너테이션입니다. Delivery 엔티티와 Order 엔티티 사이에 일대일 관계가 설정되어 있는 것으로 보입니다.
    * mappedBy = "delivery": 양방향 관계에서 역방향 매핑을 나타냅니다.
    *"delivery"는 Order 엔티티에서 Delivery 엔티티를 참조하는 필드의 이름입니다.
    *order 필드는 Delivery 엔티티와 Order 엔티티 간의 일대일 관계를 매핑합니다. Order 엔티티를 참조하는 필드로 보입니다.
    * */
    @OneToOne(mappedBy = "delivery")
    private Order order;

    /*
    * @Enumerated: 열거형(Enum) 타입의 필드를 매핑할 때 사용하는 애너테이션입니다.
    * STRING: DeliveryStatus 열거형 타입을 문자열로 매핑합니다.
    * DeliveryStatus는 배송 상태를 나타내는 열거형 타입으로 추측됩니다.
    * */
    @Enumerated(STRING)
    private DeliveryStatus deliveryStatus;

    /*
    * private String city;
    * city 필드는 도시 이름을 나타내는 문자열 필드입니다.
    * private String state;
    * state 필드는 주(도, 국가 등)를 나타내는 문자열 필드입니다.
    * private String street;
    * street 필드는 거리 또는 도로명을 나타내는 문자열 필드입니다.
    * private String zipcode;
    * zipcode 필드는 우편번호를 나타내는 문자열 필드입니다.
    * 위의 코드는 city, state, street, zipcode라는 필드를 가진 클래스로, 주소 정보를 저장할 수 있습니다.
    * 이 클래스의 인스턴스를 생성하고 필드에 값을 설정하면 해당 주소 정보를 나타낼 수 있습니다.
    * */
    private String city;
    private String state;
    private String street;
    private String zipcode;

    /*
    * createDelivery는 Delivery 객체를 생성하고 초기화하여 반환하는 메서드입니다.
    * 이 메서드는 정적으로 선언되어 다른 클래스에서 직접 호출될 수 있습니다.
    * Order order와 주소 관련 정보인 city, state, street, zipcode를 매개변수로 받습니다.
    * 이 정보는 Delivery 객체를 생성하고 초기화하는 데 사용될 것입니다.
    * Delivery delivery = new Delivery();을 통해 Delivery 객체를 생성합니다.
    * delivery.order = order;를 통해 생성한 Delivery 객체의 order 필드에 매개변수로 받은 order를 할당합니다.
    * delivery.deliveryStatus = ESTABLISHED;를 통해 생성한 Delivery 객체의 deliveryStatus 필드를 ESTABLISHED 상태로 설정합니다.
    * ESTABLISHED는 어떤 값인지에 대한 정보가 주어지지 않았으므로, 이 부분은 DeliveryStatus 열거형 타입에 정의된 상태 값일 것으로 추측됩니다.
    * delivery.city = city;, delivery.state = state;, delivery.street = street;, delivery.zipcode = zipcode;를 통해 생성한 Delivery 객체의 주소 관련 필드에 매개변수로 받은 주소 정보를 할당합니다.
    * return delivery;를 통해 생성한 Delivery 객체를 반환합니다.
    * */
    public static Delivery createDelivery(Order order, String city, String state, String street, String zipcode) {
        Delivery delivery = new Delivery();
        delivery.order = order;
        delivery.deliveryStatus = ESTABLISHED;
        delivery.city = city;
        delivery.state = state;
        delivery.street = street;
        delivery.zipcode = zipcode;
        return delivery;
    }
    /*
    * DeliveryStatus라는 열거형(Enum)을 정의하고 있습니다. 이 열거형은 배송 상태를 나타내는 상수들을 포함하고 있습니다.
    * 주요 요소와 설명은 다음과 같습니다:
    * ESTABLISHED: 배송이 확정되었음을 나타내는 상수입니다. 배송이 예약되었고 진행 중이거나 아직 완료되지 않았을 수 있습니다.
    * PROGRESS: 배송이 진행 중인 상태를 나타내는 상수입니다. 주문이 처리되고 상품이 출발하여 배송 중인 상태를 의미할 수 있습니다.
    * DONE: 배송이 완료되었음을 나타내는 상수입니다. 상품이 정상적으로 배송되어 고객에게 도착한 상태를 의미할 수 있습니다.
    * */
    public enum DeliveryStatus {
        ESTABLISHED, PROGRESS, DONE
    }

}