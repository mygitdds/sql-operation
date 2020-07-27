package com.dds.dome.sql.operation.implement;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

/**
 * ImplementSql
 * 类作用：执行查询的sql--该方式可以做选择器模式
 * @author dds-Swallow_Birds_000001
 * @date 2020/7/26
 */
@Service
public class ImplementSql {
    @Autowired
    DruidDataSourceAutoConfigure druidDataSource;
    /**
     * 执行查询sql
     * @param sql
     * @return
     */
    public String excuterQuery(String sql) {
        try {
            DataSource dataSource = druidDataSource.dataSource();
            Connection connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            return resultSetToJsonObject(rs);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    /**
     * 把ResultSet转换成map
     * @param rs
     * @return
     * @throws SQLException
     */
    private String resultSetToJsonObject(ResultSet rs) throws SQLException {
        JSONArray array = new JSONArray();
        // 获取列数
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        // 遍历ResultSet中的每条数据
        while (rs.next()) {
            // 遍历每一列
            JSONObject jsonObj = new JSONObject();
            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnLabel(i);
                String value = rs.getString(columnName);
                jsonObj.put(columnName, value);
            }
            array.add(jsonObj);
        }
        return array.toJSONString();
    }

}
