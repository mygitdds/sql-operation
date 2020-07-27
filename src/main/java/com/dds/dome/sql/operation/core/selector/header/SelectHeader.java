package com.dds.dome.sql.operation.core.selector.header;

import com.dds.dome.sql.operation.common.Constant;
import com.dds.dome.sql.operation.common.Epiphany;
import com.dds.dome.sql.operation.core.SqlAttribute;
import com.dds.dome.sql.operation.core.param.result.ResultOperation;
import com.dds.dome.sql.operation.core.param.row.JoinRow;
import com.dds.dome.sql.operation.core.param.row.Row;
import com.dds.dome.sql.operation.core.param.row.RowoPeration;
import com.dds.dome.sql.operation.core.selector.selectType.SelectSelector;
import com.dds.dome.sql.operation.core.sql.OperationHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * SeleteHeader
 * 类作用：查询sql的头部选择器
 * @author dds-Swallow_Birds_000001
 * @date 2020/7/25
 */
@Component("select")
public class SelectHeader implements GetSqlHeaderChoice {
    @Autowired
    private SelectSelector selectSelector;

    @Override
    public void splicHeader(SqlAttribute sqlAttribute, OperationHeader operationHeader) {
        //拿到尾部体
        RowoPeration rowoPeration= sqlAttribute.getRowoPeration();
        //拿到返回字段
        processingReturnedResults(sqlAttribute.getRowoPeration(),operationHeader);
        if(sqlAttribute.getRowoPeration().getOperationType().equals(Constant.NO_JOIN)){
            Row row = sqlAttribute.getRowoPeration().getRows().get(0);
            operationHeader.setTail("from "+row.getRowName());
            return;
        }
        //处理尾部
        selectSelector.implement(rowoPeration,operationHeader);

    }

    //返回没遇需要处理的结果集
    private void processingReturnedResults(RowoPeration rowoPeration, OperationHeader operationHeader) {
        //需要判断是否是多张表的链接查询
        OperationHeader operationHeaderNew = operationHeader;
        //判断是否不需要链表操作
        if (rowoPeration.getOperationType().equals(Constant.NO_JOIN)) {
            operationHeader.setReturnField(nonLinkSplic(rowoPeration.getRows()));
        } else {
            //是连表操作-把rows转换成jojnRow
            List<JoinRow> joinRows = getJoinRow(rowoPeration.getRows());
            operationHeader.setReturnField(splicReturn(joinRows));
        }
    }


    /**
     * 把rows强转成JoinRow
     * @param rows
     * @return
     */
    private List<JoinRow> getJoinRow(List<Row> rows) {
        List<JoinRow> joinRows = new ArrayList<>(rows.size());
        for (Row row : rows) {
            joinRows.add((JoinRow) row);
        }
        return joinRows;
    }

    /**
     * 非链接查询的参数拼接
     * @param rows
     * @return
     */
    private String nonLinkSplic(List<Row> rows) {
        if(rows.size() ==1){
            return " * ";
        }else {
            StringBuilder splic = new StringBuilder("");
            for(int i= 0;i<rows.size();i++){
                splic.append(rows.get(i));
                if(i< rows.size() -1){
                    splic.append(",");
                }

            }
            return splic.toString();
        }
    }

    /**
     * 拼接返回的结果
     *
     * @return
     */
    private String splicReturn(List<JoinRow> joinRows) {
        for (JoinRow joinRow : joinRows) {
            List<String> fields = joinRow.getField();
            if (!CollectionUtils.isEmpty(fields)) {
                StringBuilder splic = new StringBuilder("");
                //拿到表的别名
                String as = Epiphany.getAs(joinRow.getSort());
                for (int i= 0;i<joinRows.size();i++) {
                    JoinRow field =joinRows.get(i);
                    splic.append(as + "." + field + " " + field);
                    if(i< joinRows.size() -1){
                        splic.append(",");
                    }
                }
                return splic.toString();
            }
        }
        return null;
    }


}
