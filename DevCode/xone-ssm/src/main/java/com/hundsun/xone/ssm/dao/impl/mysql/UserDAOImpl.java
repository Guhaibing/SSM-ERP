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
import com.hundsun.xone.ssm.dao.support.ResultInfo;
import com.hundsun.xone.ssm.entity.SysTime;
import com.hundsun.xone.ssm.entity.User;
import com.hundsun.xone.ssm.util.TimeUtil;
import org.springframework.stereotype.Repository;

/**
 * 通过实现jdbcTemplate操作数据库，不再需要mapper.xml，配置映射文件
 */
@Repository
public class UserDAOImpl extends BaseDAO implements UserDAO {


    @Override
    public User selectUserByUserId(String userId) {
        // from 前要加空格
        String sql = "select user_id,user_name,user_type,user_status,user_pwd,last_update_date,last_update_time,remark,login_flag" +
                " from ssm_user where user_id = ?";
        return this.readOnlyJdbcTemplate.queryForObject(sql, new Object[]{userId}, (resultSet, rowNum) -> {
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
        });
    }

    @Override
    public User selectUserByUserName(String username) {
        String sql = "select user_id,user_name,user_type,user_status,user_pwd,last_update_date,last_update_time,remark,login_flag" +
                " from ssm_user where user_name = ?";
        return this.readOnlyJdbcTemplate.queryForObject(sql, new Object[]{username}, (resultSet, rowNum) -> {
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
        });
    }

    @Override
    public ResultInfo insertUser(User user) {
        String sql = "insert into ssm_user(user_id,user_name,user_type,user_status,user_pwd,last_update_date,last_update_time,remark,login_flag)" +
                " values(ifnull(?,''), ifnull(?,''), ifnull(?,' '), ifnull(?,' '), ifnull(?,''), ifnull(?,0), ifnull(?,0), ifnull(?,''), ifnull(?,''));";

        writeAndReadJdbcTemplate.update(sql, preparedStatement -> {
            preparedStatement.setString(1, user.getUserId());
            preparedStatement.setString(2, user.getUserName());
            preparedStatement.setString(3, String.valueOf(user.getUserType()));
            preparedStatement.setString(4, String.valueOf(user.getUserStatus()));
            preparedStatement.setString(5, user.getUserPwd());
            SysTime sysTime = TimeUtil.getSysTime();
            preparedStatement.setInt(6, sysTime.getCurrDate());
            preparedStatement.setInt(7, sysTime.getCurrTime());
            preparedStatement.setString(8, user.getRemark());
            preparedStatement.setString(9, String.valueOf(user.getLoginFlag()));
        });

        ResultInfo resultInfo = new ResultInfo();

        StringBuilder builder = new StringBuilder();
        builder.append("新增用户：").append(user.getUserName()).append("；");
        resultInfo.setOpRemark(builder.toString());
        long serialNo = 0L;
        resultInfo.setSerialNo(serialNo);

        return resultInfo;
    }
}
