package spring.in.action.fifth.tacocloud.data;

import org.springframework.data.repository.CrudRepository;
import spring.in.action.fifth.tacocloud.Taco;

public interface TacoRepository extends CrudRepository<Taco, Long> {

}
