package com.dds.dome.sql.operation.core.param.data.limit;

import lombok.Data;

/**
 * limitModel
 * 类作用：分页
 * @author dds
 * @date 2020/7/25
 */
@Data
public class limitModel {
    private int limit;
    private int offset;
}
