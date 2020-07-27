package com.dds.dome.sql.operation.core.filter;
import com.dds.dome.sql.operation.builder.SqlImplement;
import com.dds.dome.sql.operation.core.SqlAttribute;
import com.dds.dome.sql.operation.core.sql.OperationHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * GenerateSqlService
 * 类作用：filter的执行控制器
 * @author dds-Swallow_Birds_000001
 * @date 2020/7/26
 */
@Component
public class GenerateSqlService {

    private final List<GenerateSqlFilter> filters = new ArrayList<>();

    public void register(GenerateSqlFilter filter){
        filters.add(filter);
    }

    public void runFilter(SqlAttribute sqlAttribute, SqlImplement sqlImplement){
        for(GenerateSqlFilter filter : filters){
            filter.run(sqlAttribute,sqlImplement);
        }
    }

}
