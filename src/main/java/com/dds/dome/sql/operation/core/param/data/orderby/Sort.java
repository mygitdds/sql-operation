package com.dds.dome.sql.operation.core.param.data.orderby;

import lombok.Data;
import org.springframework.core.Ordered;

/**
 * Sort
 * 类作用：排序字段类
 * @author dds-Swallow_Birds_000001
 * @date 2020/7/25
 */
@Data
public class Sort implements Ordered {
    private int tableNum;
    //排序字段
    private String field;
    //排序类型
    private String sortType;
    private int sort;

    @Override
    public int getOrder() {
        return sort;
    }
}
