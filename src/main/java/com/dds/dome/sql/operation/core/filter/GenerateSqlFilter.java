package com.dds.dome.sql.operation.core.filter;

import com.dds.dome.sql.operation.builder.SqlImplement;
import com.dds.dome.sql.operation.core.SqlAttribute;
import com.dds.dome.sql.operation.core.sql.OperationHeader;

/**
 * GenerateSqlFilter
 *作用。责任链的接口
 * @author dds-Swallow_Birds_000001
 * @date 2020/7/26
 */
public interface GenerateSqlFilter {
    void run(SqlAttribute sqlAttribute, SqlImplement sqlImplement);
}
