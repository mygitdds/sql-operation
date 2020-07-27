package com.dds.dome.sql.operation.core;
import com.dds.dome.sql.operation.core.param.condition.Condition;
import com.dds.dome.sql.operation.core.param.data.group.Group;
import com.dds.dome.sql.operation.core.param.data.limit.limitModel;
import com.dds.dome.sql.operation.core.param.data.orderby.Sort;
import com.dds.dome.sql.operation.core.param.row.RowoPeration;
import lombok.Data;

import java.util.List;

/**
 * tet
 * 类作用：sql各种属性的集合类
 * @author dds-Swallow_Birds_000001
 * @date 2020/7/25
 */
@Data
public class SqlAttribute {
    //执行sql类型
    private String operationType;
    //表类型数据
    private RowoPeration rowoPeration;
    //逻辑运行数据
    private List<Condition> conditionList;
    //排序属性
    private List<Sort> sortList;
    //分组字段属性
    private List<Group> group;
    //分页
    private limitModel limitModel;
}
