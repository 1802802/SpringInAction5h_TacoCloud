package spring.in.action.fifth.tacocloud;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Taco {

    @Id
    //根据持久层引擎，自动选择主键生成策略
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date createdAt;

    @NotNull
    @Size(min=5, message="Name must be at least 5 characters long")
    private String name;

    @NotNull
    //多对多关系，每个Taco可以有多个Ingredient，而每个Ingredient也可以是多个不同Taco的组成部分
    @ManyToMany(targetEntity = Ingredient.class)
    @Size(min=1, message="You must choose at least 1 ingredient")
    private List<Ingredient> ingredients;

    @PrePersist
    //在持久化之前自动填充实体属性
    void createdAt() {
        this.createdAt = new Date();
    }
}
