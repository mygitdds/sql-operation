package com.dds.dome.sql.operation.core.param.row;

import lombok.Data;

import java.util.List;

/**
 * RowoPeration
 * 类作用：控制操作表的类型--查询，删除，新增
 * @author dds
 * @date 2020/7/25
 */
@Data
public class RowoPeration {
    //表字段
    private List<Row> rows;
    //操作类型 左连接-又连接等-非链接查询
    private String operationType;

}
