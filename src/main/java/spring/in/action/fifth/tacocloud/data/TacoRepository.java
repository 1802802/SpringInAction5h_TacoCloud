package spring.in.action.fifth.tacocloud.data;

import spring.in.action.fifth.tacocloud.Taco;

public interface TacoRepository {

    Taco save(Taco design);
}
