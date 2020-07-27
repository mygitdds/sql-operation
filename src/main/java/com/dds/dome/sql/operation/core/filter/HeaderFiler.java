package com.dds.dome.sql.operation.core.filter;

import com.dds.dome.sql.operation.builder.SqlImplement;
import com.dds.dome.sql.operation.core.SqlAttribute;
import com.dds.dome.sql.operation.core.selector.header.HeaderSelector;
import com.dds.dome.sql.operation.core.sql.OperationHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * HeaderFiler
 * 类作用：处理sql头部的执行
 * @author dds-Swallow_Birds_000001
 * @date 2020/7/26
 */
@Component
public class HeaderFiler implements GenerateSqlFilter {
    @Autowired
    private HeaderSelector headerSelector;
    @Autowired
    private GenerateSqlService generateSqlService;

    @PostConstruct
    public void init(){
        generateSqlService.register(this);
    }
    @Override
    public void run(SqlAttribute sqlAttribute, SqlImplement sqlImplement) {
        sqlImplement.setOperationHeader(new OperationHeader());
        headerSelector.implement(sqlAttribute,sqlImplement.getOperationHeader());
    }
}
