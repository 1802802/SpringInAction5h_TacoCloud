package spring.in.action.fifth.tacocloud.data;

import org.springframework.data.repository.CrudRepository;
import spring.in.action.fifth.tacocloud.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {

}
