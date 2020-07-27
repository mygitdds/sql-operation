package com.dds.dome.sql.operation.builder;

import com.dds.dome.sql.operation.core.SqlAttribute;

/**
 * SqlImplementBuilder
 * 类作用：对象构造接口，约束行为
 * @author dds
 * @date 2020/7/25
 */
public interface SqlImplementBuilder {
    //构造sql执行体
    SqlImplement structuralMorphology(SqlAttribute sqlAttribute);

}
