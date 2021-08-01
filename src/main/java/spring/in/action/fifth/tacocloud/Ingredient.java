package spring.in.action.fifth.tacocloud;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Ingredient {

    private final String id;
    private final String name;
    private final Type type;

    public enum Type{
        WRAP,           //卷饼类型
        PROTEIN,        //蛋白质类型（可理解为肉类型）
        VEGGIES,        //蔬菜类型
        CHEESE,         //芝士类型
        SAUCE           //酱汁类型
    }
}
