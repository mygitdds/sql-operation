package com.dds.dome.sql.operation.builder;
import com.dds.dome.sql.operation.core.SqlAttribute;
import com.dds.dome.sql.operation.core.filter.GenerateSqlService;
import com.dds.dome.sql.operation.implement.ImplementSql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ConcreteBuilder
 * 类作用：建造可执行的sql
 * @author dds-Swallow_Birds_000001
 * @date 2020/7/26
 */
@Service
public class ConcreteBuilder implements SqlImplementBuilder{
    @Autowired
    private GenerateSqlService generateSqlService;

    @Override
    public SqlImplement structuralMorphology(SqlAttribute sqlAttribute) {
        SqlImplement sqlImplement = new SqlImplement();
        generateSqlService.runFilter(sqlAttribute,sqlImplement);
        return sqlImplement;
    }
}
