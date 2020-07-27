package com.dds.dome.sql.operation.core.sql;

import lombok.Data;
import org.springframework.util.StringUtils;

/**
 * OperationHeader
 * 类作用：存放头部信息
 * @author dds-Swallow_Birds_000001
 * @date 2020/7/25
 */
@Data
public class OperationHeader {
    //头部，固定的名称
    private final String header = "select ";
    //查询出的字段
    private String returnField;
    //尾部
    private String tail;

    public String getHeader(){
        StringBuilder splic = new StringBuilder("");
        splic.append(header).append(returnField);
        if(!StringUtils.isEmpty(tail)){
            splic.append(tail);
        }
        return splic.toString();
    }
}
