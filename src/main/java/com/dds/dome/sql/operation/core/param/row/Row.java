package com.dds.dome.sql.operation.core.param.row;

import lombok.Data;
import org.springframework.core.Ordered;

import java.util.List;

/**
 * Row
 * 类作用：sql结构中的表名
 * @author dds
 * @date 2020/7/25
 */
@Data
public class Row {
    private String rowName;
    //该表需要返回的字段
    private List<String> field;
}
