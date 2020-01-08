package com.hundsun.xone.ssm.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;

public abstract class BaseDAO {

    @Autowired
    @Qualifier("readOnlyJdbcTemplate")
    protected JdbcTemplate readOnlyJdbcTemplate;

    @Autowired
    @Qualifier("writeAndReadJdbcTemplate")
    protected JdbcTemplate writeAndReadJdbcTemplate;
}
