package com.dds.dome.sql.operation.core.selector.selectType;

import com.dds.dome.sql.operation.core.SqlAttribute;
import com.dds.dome.sql.operation.core.param.row.RowoPeration;
import com.dds.dome.sql.operation.core.sql.OperationHeader;

/**
 * SelectExecutionTypeChoice
 *作用：查询类型封装的选择器接口
 * @author dds-Swallow_Birds_000001
 * @date 2020/7/26
 */
public interface SelectExecutionTypeChoice {
    void run(RowoPeration rowoPeration, OperationHeader operationHeader);
}
