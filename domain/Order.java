package likelion.springbootsunny.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
/*
* java.time 패키지에서 LocalDateTime 클래스를 가져오는 import 문입니다.
* LocalDateTime 클래스는 날짜와 시간 정보를 포함하는 불변(immutable)의 클래스로, 연도, 월, 일, 시, 분, 초 등의 정보를 다룰 수 있습니다.
* 이를 통해 날짜와 시간에 관련된 작업을 수행할 수 있습니다.
* */
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
/*
* jakarta.persistence.CascadeType.ALL: JPA(Java Persistence API)에서 제공하는 CascadeType 열거형 상수 중 하나인 ALL을 가져옵니다.
* CascadeType.ALL은 엔티티 간의 관계에서 한 엔티티의 변경이 다른 연관된 엔티티에도 전파되도록 지정합니다.
* 예를 들어, 부모 엔티티가 삭제되면 연관된 자식 엔티티도 모두 삭제됩니다.
* */
import static jakarta.persistence.CascadeType.ALL;
/*
* jakarta.persistence.FetchType.LAZY: JPA에서 제공하는 FetchType 열거형 상수 중 하나인 LAZY를 가져옵니다.
* FetchType.LAZY는 엔티티 간의 관계에서 연관된 엔티티를 지연로딩으로 가져오도록 지정합니다.
* 지연로딩은 연관된 엔티티가 실제로 사용될 때까지 로딩을 늦추는 방식으로, 성능 개선을 위해 사용됩니다.
* */
import static jakarta.persistence.FetchType.LAZY;
/*lombok.AccessLevel.PROTECTED: Lombok 라이브러리에서 제공하는 AccessLevel 열거형 상수 중 하나인 PROTECTED를 가져옵니다.
AccessLevel.PROTECTED는 Lombok을 사용하여 생성된 생성자, 메서드, 필드의 접근 제어 수준을 protected로 설정합니다.
protected 접근 제어 수준은 동일한 패키지 내에서 접근 가능하며, 상속 관계에 있는 클래스에서도 접근할 수 있습니다.
* */
import static lombok.AccessLevel.PROTECTED;

    @Entity
/*
@Table(name = "orders")은 JPA에서 엔티티와 매핑되는 데이터베이스 테이블의 이름을 지정하는 애너테이션입니다.
@Table 애너테이션은 엔티티 클래스가 특정 데이터베이스 테이블과 매핑될 때 사용됩니다.
name 속성을 사용하여 매핑할 테이블의 이름을 지정할 수 있습니다.
*/
    @Table(name = "orders") // 이거 안하면 에러
    @Getter
    @NoArgsConstructor(access = PROTECTED)

    public class Order {
        @Id
        @GeneratedValue
        /*private Long id: 주문 식별자를 나타내는 필드입니다.*/
        private Long id;

        /*
         @ManyToOne(fetch = LAZY), @JoinColumn(name = "member_id"): Member 엔티티와의 다대일 관계를 설정합니다.
         fetch = LAZY는 회원 엔티티를 지연로딩으로 가져오도록 지정하고, name = "member_id"는 외래 키 컬럼의 이름을 member_id로 설정합니다.
        * */
        @ManyToOne(fetch = LAZY)
        @JoinColumn(name = "member_id")
        private Member member;

        /*
        * @OneToOne(fetch = LAZY, cascade = ALL), @JoinColumn(name = "delivery_id"): Delivery 엔티티와의 일대일 관계를 설정합니다.
        * fetch = LAZY는 배송 엔티티를 지연로딩으로 가져오도록 지정하고, cascade = ALL은 모든 변경 작업이 배송 엔티티에도 적용되도록 지정합니다.
        * name = "delivery_id"는 외래 키 컬럼의 이름을 delivery_id로 설정합니다.
        * */
        @OneToOne(fetch = LAZY, cascade = ALL)
        @JoinColumn(name = "delivery_id")
        private Delivery delivery;

        /*
        * @OneToMany(mappedBy = "order", cascade = ALL): OrderItem 엔티티와의 일대다 관계를 설정합니다.
        * mappedBy = "order"는 OrderItem 엔티티의 order 필드를 매핑하여 양방향 관계를 형성합니다.
        * cascade = ALL은 모든 변경 작업이 주문 상품 엔티티에도 적용되도록 지정합니다.
        * */
        @OneToMany(mappedBy = "order", cascade = ALL)
        private List<OrderItem> orderItemList = new ArrayList<>();

        /*
        * private LocalDateTime orderDate: 주문 날짜를 나타내는 필드입니다.
        * */
        private LocalDateTime orderDate;

        /*
        * @Enumerated(EnumType.STRING): 열거형 필드인 orderStatus의 매핑 방식을 설정합니다.
        * EnumType.STRING은 열거형 값을 데이터베이스에 문자열 형태로 저장하도록 지정합니다.
        * */
        @Enumerated(EnumType.STRING)
        private OrderStatus orderStatus;

        // 연관관계 편의 메서드
        /*
        * public void setMember(Member member): 회원을 설정하는 연관관계 편의 메서드입니다.
        * 회원을 설정한 후, 회원 객체의 주문 목록에 현재 주문 객체를 추가합니다.
        * */
        public void setMember(Member member) {
            this.member = member;
            member.getOrderList().add(this);
        }

        /*
        * public static Order createOrder(Member member, OrderItem... orderItems): 주문을 생성하는 정적 메서드입니다.
        *  매개변수로 회원 정보와 주문 상품들을 전달받습니다. 다음 동작을 수행합니다:
        * 새로운 Order 객체를 생성합니다.
        * setMember 메서드를 사용하여 주문 객체에 회원을 설정합니다.
        * orderDate 필드에 현재 시간을 저장합니다.
        * orderStatus 필드를 OrderStatus.ORDERED로 설정합니다.
        * Delivery.createDelivery 메서드를 사용하여 주문 객체와 회원의 주소 정보를 기반으로 배송 객체를 생성하고, 주문 객체의 delivery 필드에 할당합니다.
        * 반복문을 통해 주문 상품들을 주문 객체의 orderItemList에 추가하고, 주문 상품 객체의 setOrder 메서드를 사용하여 주문 객체를 설정합니다.
        * 생성된 주문 객체를 반환합니다.
        * */
        public static Order createOrder(Member member, OrderItem... orderItems) {
            Order order = new Order();
            order.setMember(member);
            order.orderDate = LocalDateTime.now();
            order.orderStatus = OrderStatus.ORDERED;
            order.delivery = Delivery.createDelivery(order, member.getAddress().getCity(),
                    member.getAddress().getState(),
                    member.getAddress().getStreet(),
                    member.getAddress().getZipcode());
            for (OrderItem orderItem : orderItems) {
                order.orderItemList.add(orderItem);
                orderItem.setOrder(order);
            }
            return order;
        }

        /*
        * public void cancel(): 주문을 취소하는 메서드입니다. 다음 동작을 수행합니다:
        * 배송 객체의 배송 상태(deliveryStatus)가 Delivery.DeliveryStatus.DONE인 경우에는 예외를 발생시킵니다.
        * 주문 객체의 orderStatus를 OrderStatus.CANCELED로 설정합니다.
        * 반복문을 통해 주문 상품들을 순회하면서 cancel 메서드를 호출하여 각 주문 상품을 취소 처리합니다.
        * */
        public void cancel() {
            if (delivery.getDeliveryStatus() == Delivery.DeliveryStatus.DONE) {
                throw new IllegalStateException("배송 완료했다 양아치야");
            }
            this.orderStatus = OrderStatus.CANCELED;
            for (OrderItem orderItem : orderItemList) {
                orderItem.cancel();
            }
        }

        /*
        * public int getTotalPrice(): 주문의 총 가격을 계산하는 메서드입니다. 다음 동작을 수행합니다:
        * totalPrice 변수를 0으로 초기화합니다.
        * 반복문을 통해 주문 상품들을 순회하면서 각 주문 상품의 총 가격을 구하여 totalPrice에 누적합니다.
        * 최종적으로 계산된 총 가격을 반환합니다.
        * */
        public int getTotalPrice() {
            int totalPrice = 0;
            for (OrderItem orderItem : orderItemList) {
                totalPrice += orderItem.getTotalPrice();
            }
            return totalPrice;
        }
    }

