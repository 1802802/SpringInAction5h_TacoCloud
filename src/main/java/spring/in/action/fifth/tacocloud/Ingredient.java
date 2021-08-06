package spring.in.action.fifth.tacocloud;
//force = true将这些final成员变量全部置为null
//因为无法配置h2数据库，并且工程使用也都是mysql，故使用mysql进行数据存储。但是mysql的主键是字母排序的，无法按插入顺序存，所以无法过测试用例。
//于是选择自己新增一个pid当自增主键保证存储顺序为插入数据，但是这样会导致IngredientRepository无法根据id解析，因为其默认用pid解析
//这种情况下就需要自己重写findById方法，最后借助@Query实现了功能

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long pid;

    @Column(unique = true)
    private final String id;
    private final String name;
    private final Type type;

    public enum Type {
        WRAP,           //卷饼类型
        PROTEIN,        //蛋白质类型（可理解为肉类型）
        VEGGIES,        //蔬菜类型
        CHEESE,         //芝士类型
        SAUCE           //酱汁类型
    }
}
