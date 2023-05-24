package likelion.springbootsunny.repository;

import likelion.springbootsunny.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ItemRepository extends JpaRepository<Item, Long> {
}
