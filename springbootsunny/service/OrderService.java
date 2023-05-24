package likelion.springbootsunny.service;

import likelion.springbootsunny.domain.Order;

import java.util.List;

public interface OrderService {
    public Long createOrder(Long memberId, Long itemId, int count);

    public void cancelOrder(Long orderId);

    public List<Order> findOrderList();
}