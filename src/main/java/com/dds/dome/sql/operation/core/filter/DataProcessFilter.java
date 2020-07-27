package com.dds.dome.sql.operation.core.filter;

import com.dds.dome.sql.operation.builder.SqlImplement;
import com.dds.dome.sql.operation.common.Constant;
import com.dds.dome.sql.operation.common.Epiphany;
import com.dds.dome.sql.operation.core.SqlAttribute;
import com.dds.dome.sql.operation.core.param.data.group.Group;
import com.dds.dome.sql.operation.core.param.data.limit.limitModel;
import com.dds.dome.sql.operation.core.param.data.orderby.Sort;
import com.dds.dome.sql.operation.core.sql.DataProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * DataProcessFilter
 * 类作用：做查询语句后续分组等sql拼接
 * @author dds-Swallow_Birds_000001
 * @date 2020/7/26
 */
@Component
public class DataProcessFilter implements GenerateSqlFilter {

    @Autowired
    private GenerateSqlService generateSqlService;

    @PostConstruct
    public void init(){
        generateSqlService.register(this);
    }

    @Override
    public void run(SqlAttribute sqlAttribute, SqlImplement sqlImplement) {
        //除了查询以外是不需要该部分的
        if(!sqlAttribute.getOperationType().equals(Constant.SELECT)){
            return;
        }
        DataProcess DataProcess = new DataProcess();
        DataProcess.setOrderBy(orderByHandle(sqlAttribute));
        DataProcess.setLimit(limitHandle(sqlAttribute));
        DataProcess.setGroupBy(groupByHandle(sqlAttribute));
        sqlImplement.setDataProcess(DataProcess);
    }

    /**
     * 处理排序
     * @param sqlAttribute
     * @return
     */
    private String orderByHandle(SqlAttribute sqlAttribute){
        List<Sort> sortList = sqlAttribute.getSortList();
        StringBuilder splic = new StringBuilder("");
        if(!CollectionUtils.isEmpty(sortList)){
            splic.append(" order by ");
            for (int i=0;i<sortList.size();i++){
                Sort sort = sortList.get(i);
                String as = Epiphany.getAs(sort.getTableNum());
                if(!StringUtils.isEmpty(as)){
                    splic.append(as+".");
                }
                splic.append(sort.getField() +" ").append(sort.getSortType() !=null?sort.getSortType():"");

                if(i<sortList.size()-1){
                    splic.append(",");
                }
            }
            return splic.toString();
        }
        return null;
    }

    /**
     * 分组
     * @param sqlAttribute
     * @return
     */
    private String groupByHandle(SqlAttribute sqlAttribute){
        List<Group> groupList = sqlAttribute.getGroup();
        if(!CollectionUtils.isEmpty(groupList)){
            StringBuilder splic = new StringBuilder("");
            splic.append(" group by ");
            for (int i=0;i<groupList.size();i++){
                Group group = groupList.get(i);
                String as = Epiphany.getAs(group.getTableNum());
                if(!StringUtils.isEmpty(as)){
                    splic.append(as+".");
                }
                splic.append(group.getGroupfield());
                if(i<groupList.size()-1){
                    splic.append(", ");
                }
            }
            return splic.toString();
        }
        return null;
    }
    private String limitHandle(SqlAttribute sqlAttribute){
        limitModel limitModel = sqlAttribute.getLimitModel();
        if(limitModel !=null){
            StringBuilder splic = new StringBuilder("");
            splic.append("limit ");
            splic.append(limitModel.getOffset()+","+limitModel.getLimit());
            return splic.toString();
        }
        return null;
    }

}
