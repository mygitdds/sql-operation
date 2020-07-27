package com.dds.dome.sql.operation.core.selector.header;

import com.dds.dome.sql.operation.core.SqlAttribute;
import com.dds.dome.sql.operation.core.sql.OperationHeader;

/**
 * GetSqlHeaderChoice
 *作用：sql头部选择器
 * @author dds
 * @date 2020/7/25
 */
public interface GetSqlHeaderChoice {
    void splicHeader(SqlAttribute sqlAttribute, OperationHeader operationHeader);

}
