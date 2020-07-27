package com.dds.dome.sql.operation;

import com.dds.dome.sql.operation.builder.SqlImplement;
import com.dds.dome.sql.operation.builder.SqlImplementBuilder;
import com.dds.dome.sql.operation.common.Constant;
import com.dds.dome.sql.operation.core.SqlAttribute;
import com.dds.dome.sql.operation.core.param.condition.Condition;
import com.dds.dome.sql.operation.core.param.data.group.Group;
import com.dds.dome.sql.operation.core.param.data.limit.limitModel;
import com.dds.dome.sql.operation.core.param.data.orderby.Sort;
import com.dds.dome.sql.operation.core.param.row.Row;
import com.dds.dome.sql.operation.core.param.row.RowoPeration;
import com.dds.dome.sql.operation.implement.ImplementSql;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Log4j2
class SqlOperationApplicationTests {

    @Autowired
    private SqlImplementBuilder sqlImplementBuilder;

    @Autowired
    private ImplementSql implementSql;
    @Test
    void testSingleTable() {
        SqlAttribute sqlAttribute = new SqlAttribute();
        sqlAttribute.setOperationType(Constant.SELECT);
        //创建表数据
        RowoPeration rowoPeration = new RowoPeration();
        rowoPeration.setOperationType(Constant.NO_JOIN);
        List<Row> rows = new ArrayList<>(1);
        //设置单表
        Row row = new Row();
        row.setRowName("student_subject");
        rows.add(row);
        rowoPeration.setRows(rows);
        sqlAttribute.setRowoPeration(rowoPeration);
        SqlImplement sqlImplement = sqlImplementBuilder.structuralMorphology(sqlAttribute);
        log.info("执行的sql" + sqlImplement.getSql());
        log.info("执行返回结果" + implementSql.excuterQuery(sqlImplement.getSql()));
        //带有逻辑的运算
        testInclusionLogic();
        //带有分组的sql执行
        testGroup();
        //带有排序的sql执行
        testOrderBy();
    }

    /**
     * 测试包含逻辑运算的查询
     */
    void testInclusionLogic() {
        SqlAttribute sqlAttribute = new SqlAttribute();
        sqlAttribute.setOperationType(Constant.SELECT);
        //创建表数据
        RowoPeration rowoPeration = new RowoPeration();
        rowoPeration.setOperationType(Constant.NO_JOIN);
        List<Row> rows = new ArrayList<>(1);
        //设置单表
        Row row = new Row();
        row.setRowName("student_subject");
        rows.add(row);
        rowoPeration.setRows(rows);
        sqlAttribute.setRowoPeration(rowoPeration);
        //设置条件运算条件
        List<Condition> conditionList = new ArrayList<>();
        Condition condition = new Condition();
        condition.setField("subject_name");
        condition.setValue("数学");
        condition.setLogicalSymbol("and");
        condition.setSort(1);
        conditionList.add(condition);
        Condition condition1 = new Condition();
        condition1.setField("subject_achievement");
        condition1.setValue("80");
        condition1.setSort(2);
        conditionList.add(condition1);
        sqlAttribute.setConditionList(conditionList);
        SqlImplement sqlImplement1 = sqlImplementBuilder.structuralMorphology(sqlAttribute);
        log.info("执行的sql" + sqlImplement1.getSql());
        log.info("执行单表包含条件运算返回的结果" + implementSql.excuterQuery(sqlImplement1.getSql()));
    }

    /**
     * 包含分组
     */
    void testGroup() {
        SqlAttribute sqlAttribute = new SqlAttribute();
        sqlAttribute.setOperationType(Constant.SELECT);
        //创建表数据
        RowoPeration rowoPeration = new RowoPeration();
        rowoPeration.setOperationType(Constant.NO_JOIN);
        List<Row> rows = new ArrayList<>(1);
        //设置单表
        Row row = new Row();
        row.setRowName("student_subject");
        rows.add(row);
        rowoPeration.setRows(rows);
        sqlAttribute.setRowoPeration(rowoPeration);
        //设置条件运算条件
        List<Condition> conditionList = new ArrayList<>();
        Condition condition = new Condition();
        condition.setField("subject_name");
        condition.setValue("数学");
        condition.setLogicalSymbol("and");
        condition.setSort(1);
        conditionList.add(condition);
        Condition condition1 = new Condition();
        condition1.setField("subject_achievement");
        condition1.setValue("80");
        condition1.setSort(2);
        conditionList.add(condition1);
        sqlAttribute.setConditionList(conditionList);
        //设置分组
        Group group = new Group();
        group.setGroupfield("id");
        Group group1 = new Group();
        group1.setGroupfield("subject_name");
        Group group2 = new Group();
        group2.setGroupfield("subject_achievement");
        List<Group> groupList = new ArrayList<>();
        groupList.add(group);
        groupList.add(group1);
        groupList.add(group2);
        sqlAttribute.setGroup(groupList);
        SqlImplement sqlImplement2 = sqlImplementBuilder.structuralMorphology(sqlAttribute);
        log.info("执行的sql" + sqlImplement2.getSql());
        log.info("执行单表包含条件运算+分组返回的结果" + implementSql.excuterQuery(sqlImplement2.getSql()));
    }

    /**
     * 包含分页 排序
     */

    void testOrderBy() {
        SqlAttribute sqlAttribute = new SqlAttribute();
        sqlAttribute.setOperationType(Constant.SELECT);
        //创建表数据
        RowoPeration rowoPeration = new RowoPeration();
        rowoPeration.setOperationType(Constant.NO_JOIN);
        List<Row> rows = new ArrayList<>(1);
        //设置单表
        Row row = new Row();
        row.setRowName("student_subject");
        rows.add(row);
        rowoPeration.setRows(rows);
        sqlAttribute.setRowoPeration(rowoPeration);
        //设置条件运算条件
        List<Condition> conditionList = new ArrayList<>();
        Condition condition = new Condition();
        condition.setField("subject_name");
        condition.setValue("数学");
        condition.setLogicalSymbol("and");
        condition.setSort(1);
        conditionList.add(condition);
        Condition condition1 = new Condition();
        condition1.setField("subject_achievement");
        condition1.setValue("80");
        condition1.setSort(2);
        conditionList.add(condition1);
        sqlAttribute.setConditionList(conditionList);
        //设置分组
        Group group = new Group();
        group.setGroupfield("id");
        Group group1 = new Group();
        group1.setGroupfield("subject_name");
        Group group2 = new Group();
        group2.setGroupfield("subject_achievement");
        List<Group> groupList = new ArrayList<>();
        groupList.add(group);
        groupList.add(group1);
        groupList.add(group2);
        sqlAttribute.setGroup(groupList);
        //设置排序
        List<Sort> sortList = new ArrayList<>();
        Sort sort = new Sort();
        sort.setField("subject_achievement");
        Sort sort1 = new Sort();
        sort1.setField("id");
        sortList.add(sort);
        sortList.add(sort1);
        sqlAttribute.setSortList(sortList);
        limitModel limitModel = new limitModel();
        limitModel.setLimit(10);
        limitModel.setOffset(0);
        sqlAttribute.setLimitModel(limitModel);
        SqlImplement sqlImplement4 = sqlImplementBuilder.structuralMorphology(sqlAttribute);
        log.info("执行的sql" + sqlImplement4.getSql());
        log.info("执行单表包含条件运算+分组+排序+分页返回的结果" + implementSql.excuterQuery(sqlImplement4.getSql()));
    }
}
