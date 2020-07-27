package com.dds.dome.sql.operation.core.selector.header;

import com.dds.dome.sql.operation.core.SqlAttribute;
import com.dds.dome.sql.operation.core.param.row.RowoPeration;
import com.dds.dome.sql.operation.core.sql.OperationHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * HeaderSelector
 * 类作用：头部选择控制器，控制是查询-修改-删除等
 * @author dds-Swallow_Birds_000001
 * @date 2020/7/26
 */
@Component
public class HeaderSelector {
    private final Map<String, GetSqlHeaderChoice> selectMap = new ConcurrentHashMap<>();
    @Autowired
    public void HeaderSelector(Map<String, GetSqlHeaderChoice> selectMap) {
        this.selectMap.clear();
        selectMap.forEach((k, v)-> this.selectMap.put(k, v));
    }

    public void implement(SqlAttribute sqlAttribute, OperationHeader operationHeader){
        //根据tagType来判定走了哪里
        selectMap.get(sqlAttribute.getOperationType()).splicHeader(sqlAttribute,operationHeader);
    }
}
