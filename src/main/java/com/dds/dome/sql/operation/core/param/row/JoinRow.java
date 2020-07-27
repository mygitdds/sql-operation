package com.dds.dome.sql.operation.core.param.row;

import lombok.Data;
import org.springframework.core.Ordered;

import java.util.List;

/**
 * JoinRow
 * 类作用：连接查询
 * @author dds
 * @date 2020/7/25
 */
@Data
public class JoinRow extends Row implements Ordered {
    private int sort;
    //连接字段
    private String joinField;
    //链接的表编号
    private int  linkTableNum;
    //链接字段
    private String linkField;


    @Override
    public int getOrder() {
        return sort;
    }
}
