package spring.in.action.fifth.tacocloud.data;

import spring.in.action.fifth.tacocloud.Ingredient;

public interface IngredientRepository {

    Iterable<Ingredient> findAll();

    Ingredient findById(String id);

    Ingredient save(Ingredient ingredient);
}
