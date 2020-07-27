package com.dds.dome.sql.operation.core.param.result;

import lombok.Data;


/**
 * Result
 * 类作用：控制查询返回的结果与update操作修改的内容
 * @author dds-Swallow_Birds_000001
 * @date 2020/7/25
 */
@Data
public class ResultOperation {
    //字段
    private String field;
    //values
    private String value;
}
