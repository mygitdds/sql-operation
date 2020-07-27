package com.dds.dome.sql.operation.core.sql;

import lombok.Data;
import org.springframework.util.StringUtils;

/**
 * DataProcess
 * 类作用：数据后续处理。排序，分组等
 * @author dds-Swallow_Birds_000001
 * @date 2020/7/25
 */
@Data
public class DataProcess {
    //数据处理部分
    private String orderBy;

    private String groupBy;

    private String limit;

    public String getDataProcess(){
        StringBuilder splic = new StringBuilder("");
        if(!StringUtils.isEmpty(groupBy)){
            splic.append(getGroupBy()+" ");
        }
        if(!StringUtils.isEmpty(orderBy)){
            splic.append(orderBy+" ");
        }
        if (!StringUtils.isEmpty(limit)){
            splic.append(limit);
        }
       return splic.toString();
    }
}
