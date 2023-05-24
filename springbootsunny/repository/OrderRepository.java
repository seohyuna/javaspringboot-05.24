package likelion.springbootsunny.repository;

import likelion.springbootsunny.domain.Order;
import likelion.springbootsunny.domain.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends JpaRepository<Order, Long> {
}