/**
 * 公司：DLUT
 * 文件名：UserDAOImpl
 * 作者：haibing
 * 时间：2020/1/5 18:17
 * 描述：
 */

package com.hundsun.xone.ssm.dao.impl;

import com.hundsun.xone.ssm.dao.UserDAO;
import com.hundsun.xone.ssm.entity.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 * 弃用
 */
@Repository
public class UserDAOImpl extends SqlSessionDaoSupport implements UserDAO {

    @Autowired(required = false)
    @Qualifier("readOnlySqlSessionFactory")
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Override
    public User selectUserByUserId(String userId) {
        SqlSession sqlSession = this.getSqlSession();
        User user = sqlSession.selectOne("com.hundsun.xone.ssm.dao.UserDAO.selectUserByUserId", userId);
        return user;
    }

    @Override
    public User selectUserByUserName(String username) {
        SqlSession sqlSession = this.getSqlSession();
        User user = sqlSession.selectOne("com.hundsun.xone.ssm.dao.UserDAO.selectUserByUserName", username);
        return user;
    }
}
