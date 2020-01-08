/**
 * 公司：DLUT
 * 文件名：SysDAO
 * 作者：haibing
 * 时间：2020/1/8 15:53
 * 描述：
 */

package com.hundsun.xone.ssm.dao;

import com.hundsun.xone.ssm.entity.SysTime;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class SysDAO extends BaseDAO {

    /**
     * Oracle获取数据库时间函数, 暂不可用
     * @return
     */
    public SysTime getSysTimeOr(){
        String sql = "select to_char(sysdate, 'yyyymmdd') as curr_date, to_char(sysdate, 'hh24miss') as curr_time, "
                + "to_char(current_timestamp, 'hh24missff3') as curr_milltime from dual";

        return this.readOnlyJdbcTemplate.queryForObject(sql, new RowMapper<SysTime>() {
            @Override
            public SysTime mapRow(ResultSet resultSet, int i) throws SQLException {
                SysTime sysTime = new SysTime();
                sysTime.setCurrDate(resultSet.getInt(1));
                sysTime.setCurrTime(resultSet.getInt(2));
                sysTime.setMillsTime(resultSet.getInt(3));

                return sysTime;
            }
        });
    }

    /**
     * Mysql数据库系统获取时间函数
     * @return
     */
    public SysTime getSysTimeMy(){
        String sql = "select date_format(now(), '%Y%m%d') as curr_date, date_format(now(), '%H%i%s') as curr_time," +
                "unix_timestamp(now()) as curr_milltime from dual;";

        return this.readOnlyJdbcTemplate.queryForObject(sql, new RowMapper<SysTime>() {
            @Override
            public SysTime mapRow(ResultSet resultSet, int i) throws SQLException {
                SysTime sysTime = new SysTime();

                sysTime.setCurrDate(resultSet.getInt(1));
                sysTime.setCurrTime(resultSet.getInt(2));
                sysTime.setMillsTime(resultSet.getInt(3));

                return sysTime;
            }
        });
    }
}
