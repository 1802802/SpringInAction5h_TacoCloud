package spring.in.action.fifth.tacocloud.data;

import org.springframework.data.repository.CrudRepository;
import spring.in.action.fifth.tacocloud.User;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);
}
