package com.dds.dome.sql.operation.core.selector.selectType;
import com.dds.dome.sql.operation.common.Epiphany;
import com.dds.dome.sql.operation.core.param.row.JoinRow;
import com.dds.dome.sql.operation.core.param.row.Row;
import com.dds.dome.sql.operation.core.param.row.RowoPeration;
import com.dds.dome.sql.operation.core.sql.OperationHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * LeftJoin
 * 类作用：左连接查询尾部封装
 * @author dds
 * @date 2020/7/26
 */
@Component("left_join")
public class LeftJoin implements SelectExecutionTypeChoice {
    @Override
    public void run(RowoPeration rowoPeration, OperationHeader operationHeader) {
        List<Row> rows = rowoPeration.getRows();
        //做左连接的拼接
        List<JoinRow> joinRows = getJoinRow(rows);
        //根据order排序
        joinRows =joinRows.stream().sorted(Comparator.comparingInt(JoinRow::getSort).reversed()).collect(Collectors.toList());
        operationHeader.setTail(splicingLogic(joinRows));
    }

    //把rows强转成JoinRow
    private List<JoinRow> getJoinRow(List<Row> rows){
        List<JoinRow> joinRows = new ArrayList<>(rows.size());
        for(Row row:rows){
            joinRows.add((JoinRow)row);
        }
        return joinRows;
    }
    //拼接成左连接的尾部
    private String splicingLogic(List<JoinRow> joinRows){
        int size = joinRows.size();
        StringBuilder logic = new StringBuilder();
        logic.append("from ");
        for(int i =0;i<size;i++){
            JoinRow joinRow = joinRows.get(i);
            String as1 = Epiphany.getAs(i+1);
            logic.append(joinRow.getRowName()+" as "+ as1);
            JoinRow before = joinRows.get(joinRow.getLinkTableNum());
            if(before ==null){
                logic.append(" left join");
                continue;
            }
            String as2 = Epiphany.getAs(joinRow.getLinkTableNum());
            logic.append(joinRow.getRowName()+" as "+as1 +" on "+as2+"."+joinRow.getLinkField()+" = "+as1+"."+joinRow.getJoinField());
            if(i<size-1){
                logic.append(" left join");
            }
        }
        return logic.toString();
    }

}
