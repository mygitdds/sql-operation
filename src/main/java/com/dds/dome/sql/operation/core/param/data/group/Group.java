package com.dds.dome.sql.operation.core.param.data.group;

import lombok.Data;

import java.util.List;

/**
 * Group
 * 类作用：分组的字段
 * @author dds
 * @date 2020/7/25
 */
@Data
public class Group {
    //分组的字段集合
    private String groupfield;
    //表编号
    private int tableNum;
}
