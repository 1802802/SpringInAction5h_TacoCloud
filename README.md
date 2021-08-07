# Spring实战（第5版） 练习项目
项目简介：跟随Spring实战第5版，使用SpringBoot自己搭建的TacoCloud项目。

项目特点：

1. 从第3章开始就使用工程通用的MySql数据库搭建持久层，而非书本的H2；
2. 对书本中的诸多问题进行填坑记录，给予其他同学一定地填坑帮助；
3. 其他基本和书本一致， 没啥其他特点了（笑）。

项目规划：
1. 边学习边搭建，但考虑到作者对自己历史代码的改动“极不负责任”，所以在完成这本书的核心内容学习之前，不会对代码做过多个人风格的调整；
2. 保证每章学习完成后，项目能保证之前章节的流程正常跑通，并通过当前章节作者所书写的UT；

项目吐槽（自由发挥空间）： 
1. 这本书行文很烂，实践和书本表达脱节严重，大量代码在GitHub中改了，但是书上只字未提。如果是选择像我这样自己跟着书本从头搭建项目，会吃不少苦头，基本每章都需要仔细地看哪些类在GitHub中改过，然后更新代码；
2. 使用了工程环境不可能使用的H2数据库和很少使用的thymeleaf，和实际工作脱节较为严重，只存在一定的参考作用。

## 填坑记录（填坑历史可查代码提交记录）
当前进展：第5章功能自测完成，UT验证完成。

基础原则1：每章的代码跟着看完+打完->对照GitHub复查缺失或直接CV大法->开始调试，前面两步骤如果没做好会让人浪费很多时间在无意义的调试上。
### 第1章 Spring起步
1. 通常都是使用IDEA进行Java编码，所以需要先搞定IDEA的破解或注册；
2. 1.2.2节的pom.xml先较于作者GitHub代码存在缺失，建议去参考补充下；
3. 作者的各个html都不加图片src，导致直接在idea中打开无法加载图；
4. 自动编译功能：打开idea的自动编译->项目启动后随便修改什么再点构建（Ctrl+F9也OK）
### 第2章 开发Web应用
1. 作者的各种html直接复制也会标红报错，莫慌，正常现象，不影响用；
2. 作者会偷偷改各个html，务必对照，不要问我怎么知道的；
3. 秉持基础原则1；
### 第3章 使用数据
1. 如果使用最新版本的SpringBoot，H2数据库在IDEA中配置会很困难。这里是一个很大的分歧点，要么竭尽所能去配置好H2数据库，要么和我一样使用更工程性质的MySql（这里务必提个醒，使用MySql的话，前方的荆棘会更多，但也会更爽）
2. 作者会偷偷把Taco类的ingredients改成List<Ingredient>，此乃第3章第一大坑，务必同步加入IngredientByIdConverter类，代码参考GitHub；
3. 作者会偷偷改各个html，务必对照，秉持基础原则1；
4. JDBC阶段，使用MySql的话，Ingredient库在存储时id会按照字母排序，导致无法过作者的UT，此时可加入pid作为自增主键保证该表存储顺序为插入数据，以过UT；
5. JPA阶段，JPA的改造建议跟在这里做了，Repository相关接口的调整也都跟着在这章做了，后面作者都会用JPA，JDBC的东西别留恋，能删就删了，尤其那俩sql文件；
6. JPA阶段，JPA如果要存储在本地MySql需要做一些配置，可以参考我的application.properties，或者自己百度；
7. JPA阶段，JPA+MySql同样存在存储时id会按照字母排序导致无法过作者UT的问题，此时可以在Ingredient实体类中新增pid主键，并同步手动改写IngredientRepository中的findById方法保证从id查Ingredient而不是从pid查。
### 第4章 保护Spring
1. 最后会选用基于JPA的方式，其他基于的方式该干掉干掉，之后会有启动影响，前面建议第3章把JPA的坑踩完也是这个原因；
2. 作者会偷偷把DesignTacoController中taco()的模型名改成design，Order类中大量字段改名，最后又偷偷把所有html全改了，务必秉持基础原则1；
3. 这章作者的UT每次启动都会塞数据，所以想过作者的UT就务必配置spring.jpa.hibernate.ddl-auto=create，保证每次的数据都是新的，尽量不要自己玩花的。玩花的可以先试一试再删掉，因为作者的UT卡的非常死。
### 第五章 使用配置属性
1. pom中新增spring-boot-configuration-processor，否则会报springboot配置注解处理器没有找到；
2. 元数据json和props中会报重新运行SPRING BOOT CONFIGURATION ANNOTATION PROCESSOR来更新生成的元数据，可管可不管，想管上StackOverFlow查；
3. 作者加了个orderList.html，秉持原则1补上；
4. 作者新增了discount相关的一系列控制器、配置项和html，对第5章而言没什么实际作用，但是属于后面章节的铺垫，建议秉持原则1在这节都加上；
5. application.properties和application.yml可以同时配置，如果习惯前者可以把适合在yml中配的迁移过去。