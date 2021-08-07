package spring.in.action.fifth.tacocloud.data;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import spring.in.action.fifth.tacocloud.Order;
import spring.in.action.fifth.tacocloud.User;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {

    List<Order> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);
}
