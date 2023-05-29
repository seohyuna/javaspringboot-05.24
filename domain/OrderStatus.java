package likelion.springbootsunny.domain;

/*
* OrderStatus라는 열거형(enum)을 정의하고 있습니다. OrderStatus는 주문의 상태를 나타내는 상수들을 포함합니다.
* */
public enum OrderStatus {
    /*
    * ORDERED: 주문이 완료된 상태를 나타냅니다. 이 상태는 주문이 생성되고 아직 취소되지 않은 상태를 의미합니다.
    * CANCELED: 주문이 취소된 상태를 나타냅니다. 이 상태는 주문이 이전에 완료되었으나, 취소되었음을 나타냅니다.
    * */
    ORDERED, CANCELED
}