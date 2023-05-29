package likelion.springbootsunny.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
* import static jakarta.persistence.FetchType.LAZY;는 FetchType 열거형의 LAZY 상수를 정적으로 임포트하는 구문입니다.
* FetchType은 JPA에서 엔티티와 관련된 연관 관계의 로딩 방식을 지정하는 열거형입니다.
* LAZY는 지연 로딩을 나타내며, 연관된 엔티티를 실제로 필요한 시점에 가져오는 방식입니다.
* 이는 연관된 엔티티가 사용되지 않는 경우 데이터베이스에서 가져오는 비용을 줄일 수 있습니다.
* */
import static jakarta.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

    @Entity
    @NoArgsConstructor(access = PROTECTED)
    @Getter
    public class OrderItem {
        @Id
        @GeneratedValue
        private Long id;

        /*
        * @ManyToOne(fetch = LAZY), @JoinColumn(name = "order_id"): Order 엔티티와의 다대일 관계를 설정합니다.
        * fetch = LAZY는 주문 엔티티를 지연로딩으로 가져오도록 지정하고, name = "order_id"는 외래 키 컬럼의 이름을 order_id로 설정합니다.
        * @ManyToOne(fetch = LAZY), @JoinColumn(name = "item_id"): Item 엔티티와의 다대일 관계를 설정합니다.
        * fetch = LAZY는 상품 엔티티를 지연로딩으로 가져오도록 지정하고, name = "item_id"는 외래 키 컬럼의 이름을 item_id로 설정합니다.
        * */
        @ManyToOne(fetch = LAZY)
        @JoinColumn(name = "order_id")
        private Order order;

        @ManyToOne(fetch = LAZY)
        @JoinColumn(name = "item_id")
        private Item item;

        /*
        * private Integer price: 주문 상품의 가격을 나타내는 필드입니다.
        * private Integer count: 주문 상품의 수량을 나타내는 필드입니다.
        * */
        private Integer price;
        private Integer count;

        /**
         * 스태틱 팩토리 메서드
         */
        /*OrderItem 클래스에 대한 정적 메서드 createOrderItem을 나타냅니다. 이 메서드는 주문 상품을 생성하는 역할을 합니다. */
        public static OrderItem createOrderItem(Item item, int orderPrice, int orderCount) {
            /*OrderItem orderItem = new OrderItem();: 새로운 OrderItem 객체를 생성합니다.*/
            OrderItem orderItem = new OrderItem();
            /*orderItem.setItem(item);: 주문 상품의 상품 객체를 설정합니다. item 매개변수로 전달받은 상품을 orderItem 객체에 설정합니다.*/
            orderItem.setItem(item);
            /*orderItem.price = orderPrice;: 주문 상품의 가격을 설정합니다. orderPrice 매개변수로 전달받은 값을 orderItem 객체의 price 필드에 할당합니다.*/
            orderItem.price = orderPrice;
            /*orderItem.count = orderCount;: 주문 상품의 수량을 설정합니다. orderCount 매개변수로 전달받은 값을 orderItem 객체의 count 필드에 할당합니다.*/
            orderItem.count = orderCount;
            // 연관관계 편의 메서드
            /*item.removeStock(orderCount);: 상품 객체의 removeStock 메서드를 호출하여 주문 수량만큼 상품의 재고를 감소시킵니다.
            생성된 주문 상품 객체 orderItem을 반환합니다.*/
            item.removeStock(orderCount);
            return orderItem;
        }

        /*
        * public void setOrder(Order order): 주문 객체를 설정하는 메서드입니다.
        * order 매개변수로 전달받은 주문 객체를 현재 OrderItem 객체에 설정하고, 해당 주문 객체의 getOrderItemList() 메서드를 통해 현재 주문 상품을 주문 목록에 추가합니다.
        * */
        public void setOrder(Order order) {
            this.order = order;
            order.getOrderItemList().add(this);
        }

        /*
        * public void setItem(Item item): 상품 객체를 설정하는 메서드입니다.
        * item 매개변수로 전달받은 상품 객체를 현재 OrderItem 객체에 설정하고, 해당 상품 객체의 getOrderItem() 메서드를 통해 현재 주문 상품을 상품의 주문 상품 목록에 추가합니다.
        * */
        public void setItem(Item item) {
            this.item = item;
            item.getOrderItem().add(this);
        }

        /**
         * 비즈니스 로직
         */
        /*
        * public int getTotalPrice(): 주문 상품의 총 가격을 계산하여 반환하는 메서드입니다.
        * 현재 OrderItem 객체의 가격(getPrice())과 수량(getCount())을 곱하여 총 가격을 계산합니다.
        * */
        public int getTotalPrice() {
            return this.getPrice() * this.getCount();
        }

        /*
        * public void cancel(): 주문을 취소하는 메서드입니다.
        * 현재 주문 상품에 연결된 상품 객체(getItem())의 addStock() 메서드를 호출하여 주문 수량만큼 상품의 재고를 증가시킵니다.
        * */
        public void cancel() {
            this.getItem().addStock(count);
        }
    }

