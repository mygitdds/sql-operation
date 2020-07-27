package com.dds.dome.sql.operation.core.param.condition;

import lombok.Data;
import org.springframework.core.Ordered;

/**
 * condition
 * 类作用：条件的实体类
 * @author dds-Swallow_Birds_000001
 * @date 2020/7/25
 */
@Data
public class Condition implements Ordered {
    private int tableNum;
    //字段
    private String field;
    //values
    private String value;
    //逻辑符号-不是末尾条件的情况下，该字段有值
    private String logicalSymbol;
    //排序
    private int sort;

    @Override
    public int getOrder() {
        return sort;
    }
}
