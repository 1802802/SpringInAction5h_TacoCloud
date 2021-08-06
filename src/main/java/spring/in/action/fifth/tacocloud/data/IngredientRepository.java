package spring.in.action.fifth.tacocloud.data;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import spring.in.action.fifth.tacocloud.Ingredient;

import java.util.Optional;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {

    @Override
    @Query(value = "from Ingredient where id = ?1")
    Optional<Ingredient> findById(String s);
}
