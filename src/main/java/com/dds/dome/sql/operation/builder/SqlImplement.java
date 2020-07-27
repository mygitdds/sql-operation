package com.dds.dome.sql.operation.builder;

import com.dds.dome.sql.operation.core.sql.DataProcess;
import com.dds.dome.sql.operation.core.sql.Logical;
import com.dds.dome.sql.operation.core.sql.OperationHeader;
import lombok.Data;
import org.springframework.util.StringUtils;

/**
 * SqlImplement
 * 类作用：该类获取执行的sql
 *
 * @author dds
 * @date 2020/7/25
 */
@Data
public class SqlImplement {
    //执行头部logicalProcess
    private OperationHeader operationHeader;
    //逻辑部分
    private Logical logicalProcess;
    //数据处理部分
    private DataProcess dataProcess;

    public String getSql() {
        StringBuilder splic = new StringBuilder("");
        splic.append(operationHeader.getHeader()+" ").
                append(logicalProcess !=null?logicalProcess.getLogicalProcess() : "").append(dataProcess !=null?dataProcess.getDataProcess(): "");
        return splic.toString();
    }

}
