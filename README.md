# sql-operation

#测试入口SqlOperationApplicationTests
整体介绍
1：通过Row表，Condition逻辑条件，Group分组，Sort排序，limitModel分页，四个对象去填充sql的创建必要条件
2：通过SqlImplementBuilder去构造SqlImplement（可执行的sql）
2.1：建造者通过GenerateSqlService责任链去执行，构造sql头部，逻辑部分，数据处理部分
2.1.1通过选择器构造头部-headerSelector
3：通过ImplementSql操作数据库拿到结果。

