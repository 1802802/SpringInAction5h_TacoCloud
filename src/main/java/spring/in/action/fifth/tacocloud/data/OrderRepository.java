package spring.in.action.fifth.tacocloud.data;

import spring.in.action.fifth.tacocloud.Order;

public interface OrderRepository {

    Order save(Order order);
}
