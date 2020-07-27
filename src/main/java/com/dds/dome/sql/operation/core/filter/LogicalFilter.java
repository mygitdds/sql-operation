package com.dds.dome.sql.operation.core.filter;

import com.dds.dome.sql.operation.builder.SqlImplement;
import com.dds.dome.sql.operation.common.Epiphany;
import com.dds.dome.sql.operation.core.SqlAttribute;
import com.dds.dome.sql.operation.core.param.condition.Condition;
import com.dds.dome.sql.operation.core.sql.Logical;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * LogicalFilter
 * 类作用：逻辑部分的处理----逻辑处理目前只支持一种
 * @author dds-Swallow_Birds_000001
 * @date 2020/7/26
 */
@Component
public class LogicalFilter implements GenerateSqlFilter {
    @Autowired
    private GenerateSqlService generateSqlService;

    @PostConstruct
    public void init(){
        generateSqlService.register(this);
    }
    @Override
    public void run(SqlAttribute sqlAttribute, SqlImplement sqlImplement) {
        List<Condition> conditionList = sqlAttribute.getConditionList();
        if(CollectionUtils.isEmpty(conditionList)){
            return;
        }
        //排序
        conditionList = conditionList.stream().sorted(Comparator.comparingInt(Condition ::getOrder)).collect(Collectors.toList());
        StringBuilder splic = new StringBuilder("");
        splic.append("where ");
        for(Condition condition :conditionList){
            String as = Epiphany.getAs(condition.getTableNum());
            if(!StringUtils.isEmpty(as)){
                splic.append(as+".");
            }
            splic.append(condition.getField() +" = " ).append(isNumber(condition.getValue())?condition.getValue()+" " :"'"+condition.getValue()+"'");
            if(!StringUtils.isEmpty(condition.getLogicalSymbol())){
                splic.append(condition.getLogicalSymbol()+" ");
            }
        }
        Logical logical = new Logical();
        logical.setLogicalProcess(splic.toString());
        sqlImplement.setLogicalProcess(logical);
    }

    private boolean isNumber(String values){
        for (int i = 0; i < values.length(); i++){
            if (!Character.isDigit(values.charAt(i))){
                return false;
            }
        }
        return true;
    }
}
