package tacos.web.api;

import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.*;

import tacos.Taco;

//这里的匿名类不能改成lambda，因为改成lambda表达式就失去了PagedResources<Resource<Taco>>的类型提供，HATEOAS无法自动发现这个bean
//所以这里换成了内部类来实现匿名类
//@Bean会截获所有tacoProcessor方法，将其转换为直接调用TacoResourceProcessor的实例，换句话说就是使用Spring中统一管理的Bean
@Configuration
public class SpringDataRestConfiguration {

  @Bean
  public ResourceProcessor<PagedResources<Resource<Taco>>> tacoProcessor(EntityLinks links) {
    return new TacoResourceProcessor(links);
  }

  @Data
  static class TacoResourceProcessor implements ResourceProcessor<PagedResources<Resource<Taco>>> {
    private final EntityLinks links;

    @Override
    public PagedResources<Resource<Taco>> process(PagedResources<Resource<Taco>> resource) {
      resource.add(links.linkFor(Taco.class)
                      .slash("recent")
                      .withRel("recents"));
      return resource;
    }
  }
}
