package likelion.springbootsunny.domain;
/*
* jakarta.persistence.Entity 패키지에서 Entity 클래스를 임포트합니다. 이 클래스는 JPA 엔티티를 정의하기 위해 사용됩니다.
* */
import jakarta.persistence.Entity;
/*
* jakarta.persistence.GeneratedValue 패키지에서 GeneratedValue 클래스를 임포트합니다.
* 이 클래스는 JPA 엔티티의 기본 키를 자동으로 생성하기 위한 애너테이션을 제공합니다.
* */
import jakarta.persistence.GeneratedValue;
/*
* jakarta.persistence.Id 패키지에서 Id 클래스를 임포트합니다. 이 클래스는 JPA 엔티티의 기본 키(primary key)를 지정하기 위한 애너테이션을 제공합니다.
* */
import jakarta.persistence.Id;
/*
* lombok.Getter 패키지에서 Getter 클래스를 임포트합니다. 이 클래스는 자동으로 Getter 메서드를 생성하기 위해 사용됩니다.
* */
import lombok.Getter;
/*
* jakarta.persistence.OneToMany 패키지에서 OneToMany 클래스를 임포트합니다. 이 클래스는 JPA 엔티티 간의 일대다 관계를 매핑하기 위해 사용됩니다.
* */
import jakarta.persistence.OneToMany;
/*
* lombok.NoArgsConstructor 패키지에서 NoArgsConstructor 클래스를 임포트합니다. 이 클래스는 인자 없는 생성자를 자동으로 생성하기 위해 사용됩니다.
* */
import lombok.NoArgsConstructor;
/*
* lombok 패키지에서 모든 클래스를 임포트합니다. * 기호는 모든 클래스를 임포트한다는 의미입니다.
* */
import lombok.*;
/*
* org.hibernate.annotations.Comment 패키지에서 Comment 클래스를 임포트합니다. 이 클래스는 주석을 추가하기 위해 사용됩니다.
* */
import org.hibernate.annotations.Comment;
/*
* java.util.ArrayList 패키지에서 ArrayList 클래스를 임포트합니다. 이 클래스는 동적으로 크기가 조정되는 배열을 구현한 리스트입니다.
* */
import java.util.ArrayList;
/*
* java.util.List 패키지에서 List 인터페이스를 임포트합니다. 이 인터페이스는 순서가 있는 요소들의 컬렉션을 나타냅니다.
* */
import java.util.List;


/*
 *@Entity 애너테이션은 해당 클래스가 JPA 엔티티임을 나타냅니다. 이 애너테이션이 붙은 클래스는 데이터베이스 테이블과 매핑되는 엔티티로 사용됩니다.
 * @GeneratedValue 애너테이션은 JPA에서 엔티티의 기본 키(primary key)를 자동으로 생성하는 전략을 지정하기 위해 사용됩니다.
 * @Id 애너테이션은 JPA에서 엔티티의 기본 키(primary key) 필드임을 나타냅니다.
 * @Getter 애너테이션은 해당 클래스의 필드에 대한 Getter 메서드를 자동으로 생성합니다. 이를 통해 필드 값에 접근할 수 있습니다.
 * @NoArgsConstructor 애너테이션은 인자 없는 생성자를 자동으로 생성합니다. 이를 통해 객체를 인스턴스화할 때 인자를 전달하지 않고 생성자를 호출할 수 있습니다.
 * @Comment 애너테이션은 엔티티나 테이블에 주석을 추가할 수 있습니다.
 * */

@Entity
@Getter
@Setter
@NoArgsConstructor

/*
*Item 클래스를 정의합니다. 이 클래스는 아이템을 나타내는 엔티티로 사용될 것입니다.
* */
public class Item {
    /*
    * @Id 애너테이션은 id 필드가 엔티티의 기본 키(primary key)임을 나타냅니다.
    * @GeneratedValue 애너테이션은 JPA에서 엔티티의 기본 키를 자동으로 생성하는 전략을 지정합니다.
    * Long 타입의 id 필드를 선언합니다. 이 필드는 엔티티의 기본 키로 사용될 것입니다.
    * */
    @Id @GeneratedValue
    private Long id;

    /*
    * @OneToMany 애너테이션은 일대다 관계를 나타내는 매핑을 지정합니다.
    * mappedBy 속성은 관계를 맺고 있는 반대쪽 엔티티의 필드를 지정합니다.
    * 이 경우, "item"이라는 필드를 사용하여 Item 엔티티와 OrderItem 엔티티의 관계를 매핑합니다.
    * List<OrderItem> 타입의 orderItem 필드를 선언합니다.
    * 이 필드는 OrderItem 엔티티와의 일대다 관계를 나타내는데 사용될 것입니다. 초기화는 빈 ArrayList로 이루어집니다.
    * */
    @OneToMany(mappedBy = "item")
    private List<OrderItem> orderItem = new ArrayList<>();

    /*
    * private String brand;
    * String 타입의 brand 필드를 선언합니다. 이 필드는 아이템의 브랜드를 나타냅니다.
    * private String name;
    * String 타입의 name 필드를 선언합니다. 이 필드는 아이템의 이름을 나타냅니다.
    * private Integer price;
    * Integer 타입의 price 필드를 선언합니다. 이 필드는 아이템의 가격을 나타냅니다.
    * private Integer stock;
    * Integer 타입의 stock 필드를 선언합니다. 이 필드는 아이템의 재고 수량을 나타냅니다.
    * */
    private String brand;
    private String name;
    private Integer price;
    private Integer stock;

    /**
     * 비즈니스 로직
     */
    /*
    * @Comment 애너테이션은 엔티티나 테이블에 주석을 추가할 수 있습니다. 이 경우 "재고 추가"라는 주석이 addStock 메서드에 추가되었습니다.
    * public 접근 제어자와 void 반환 타입을 가지는 addStock 메서드를 선언합니다.
    * 이 메서드는 재고를 추가하는 비즈니스 로직을 수행합니다. int 타입의 quantity 매개변수를 받아들입니다.
    * 현재 객체의 stock 필드에 quantity 값을 더하여 재고를 추가합니다.
    * */
    @Comment("재고 추가")
    public void addStock(int quantity) {
        this.stock += quantity;
    }

    /*
    * @Comment 애너테이션은 엔티티나 테이블에 주석을 추가할 수 있습니다. 이 경우 "재고 감소"라는 주석이 removeStock 메서드에 추가되었습니다.
    * public 접근 제어자와 void 반환 타입을 가지는 removeStock 메서드를 선언합니다.
    * 이 메서드는 재고를 감소하는 비즈니스 로직을 수행합니다. int 타입의 stockQuantity 매개변수를 받아들입니다.
    * this.stock 필드에서 stockQuantity 값을 뺀 결과를 restStock 변수에 저장합니다. 이는 재고를 감소시킨 후 남은 재고를 나타냅니다.
    * restStock 값이 0보다 작은지 확인하는 조건문입니다. 만약 남은 재고가 0보다 작다면, 재고가 부족한 상태이므로 예외를 발생시킵니다.
    * IllegalStateException 예외를 생성하고 메시지로 "need more stock"를 설정하여 예외를 발생시킵니다. 이는 재고가 부족한 상태를 나타냅니다.
    * 객체의 stock 필드를 restStock 값으로 업데이트하여 재고를 감소시킵니다.
    * */
    @Comment("재고 감소")
    public void removeStock(int stockQuantity) {
        int restStock = this.stock - stockQuantity;
        if (restStock < 0) {
            throw new IllegalStateException("need more stock");
        }
        this.stock = restStock;
    }
}