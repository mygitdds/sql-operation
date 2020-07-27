package com.dds.dome.sql.operation.core.selector.selectType;

import com.dds.dome.sql.operation.core.param.row.RowoPeration;
import com.dds.dome.sql.operation.core.sql.OperationHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * selectSelector
 * 类作用：查询选择器
 * @author dds-Swallow_Birds_000001
 * @date 2020/7/26
 */
@Component
public class SelectSelector {
    private final Map<String, SelectExecutionTypeChoice> selectMap = new ConcurrentHashMap<>();
    @Autowired
    public SelectSelector(Map<String, SelectExecutionTypeChoice> selectMap) {
        this.selectMap.clear();
        selectMap.forEach((k, v)-> this.selectMap.put(k, v));
    }
    public void implement(RowoPeration rowoPeration, OperationHeader operationHeader){
        //根据tagType来判定走了哪里
        selectMap.get(rowoPeration.getOperationType()).run(rowoPeration,operationHeader);
    }
}
