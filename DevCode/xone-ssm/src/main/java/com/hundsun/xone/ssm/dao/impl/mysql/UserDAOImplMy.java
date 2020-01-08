/**
 * 公司：DLUT
 * 文件名：UserDAOImplMy
 * 作者：haibing
 * 时间：2020/1/8 10:46
 * 描述：
 */

package com.hundsun.xone.ssm.dao.impl.mysql;

import com.hundsun.xone.ssm.dao.BaseDAO;
import com.hundsun.xone.ssm.dao.UserDAO;
import com.hundsun.xone.ssm.entity.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 通过实现jdbcTemplate操作数据库，不再需要mapper.xml，配置映射文件
 */
@Repository
public class UserDAOImplMy extends BaseDAO implements UserDAO {


    @Override
    public User selectUserByUserId(String userId) {
        // from 前要加空格
        String sql = "select user_id,user_name,user_type,user_status,user_pwd,last_update_date,last_update_time,remark,login_flag" +
                " from ssm_user where user_id = ?";
        return this.readOnlyJdbcTemplate.queryForObject(sql, new Object[]{userId}, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                User user = new User();
                user.setUserId(resultSet.getString(1));
                user.setUserName(resultSet.getString(2));
                user.setUserType(resultSet.getString(3).charAt(0));
                user.setUserStatus(resultSet.getString(4).charAt(0));
                user.setUserPwd(resultSet.getString(5));
                user.setLastUpdateDate(resultSet.getInt(6));
                user.setLastUpdateTime(resultSet.getInt(7));
                user.setRemark(resultSet.getString(8));
                user.setLoginFlag(resultSet.getString(9).charAt(0));

                return user;
            }
        });
    }

    @Override
    public User selectUserByUserName(String username) {
        String sql = "select user_id,user_name,user_type,user_status,user_pwd,last_update_date,last_update_time,remark,login_flag" +
                " from ssm_user where user_name = ?";
        return this.readOnlyJdbcTemplate.queryForObject(sql, new Object[]{username}, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                User user = new User();
                user.setUserId(resultSet.getString(1));
                user.setUserName(resultSet.getString(2));
                user.setUserType(resultSet.getString(3).charAt(0));
                user.setUserStatus(resultSet.getString(4).charAt(0));
                user.setUserPwd(resultSet.getString(5));
                user.setLastUpdateDate(resultSet.getInt(6));
                user.setLastUpdateTime(resultSet.getInt(7));
                user.setRemark(resultSet.getString(8));
                user.setLoginFlag(resultSet.getString(9).charAt(0));

                return user;
            }
        });
    }
}
