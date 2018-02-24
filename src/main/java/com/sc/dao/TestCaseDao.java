package com.sc.dao;

import com.sc.bean.TestCase;
import com.sc.util.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class TestCaseDao {
    public static List<TestCase> selectAll() throws SQLException {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "select * from testcase";
        List<TestCase> list = queryRunner.query(sql,new BeanListHandler<TestCase>(TestCase.class));
        return list;
    }
}
