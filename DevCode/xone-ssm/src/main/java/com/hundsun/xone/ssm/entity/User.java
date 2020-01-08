/**
 * 公司：DLUT
 * 文件名：User
 * 作者：haibing
 * 时间：2020/1/4 12:24
 * 描述：
 */

package com.hundsun.xone.ssm.entity;

import java.io.Serializable;

public class User implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * 客户编号
     *
     * Table:     ssm_user
     * Column:    user_id
     * Nullable:  false
     */
    private String userId;

    /**
     * 客户名字
     *
     * Table:     ssm_user
     * Column:    user_name
     * Nullable:  false
     */
    private String userName;

    /**
     * 客户类别
     *
     * Table:     ssm_user
     * Column:    user_type
     * Nullable:  false
     */
    private Character userType;

    /**
     * 用户状态
     *
     * Table:     ssm_user
     * Column:    user_status
     * Nullable:  false
     */
    private Character userStatus;

    /**
     * 认证密码
     *
     * Table:     ssm_user
     * Column:    user_pwd
     * Nullable:  false
     */
    private String userPwd;

    /**
     * 上次更新日期
     *
     * Table:     ssm_user
     * Column:    last_update_date
     * Nullable:  false
     */
    private Integer lastUpdateDate;

    /**
     * 上次更新时间
     *
     * Table:     ssm_user
     * Column:    last_update_time
     * Nullable:  false
     */
    private Integer lastUpdateTime;

    /**
     * 备注
     *
     * Table:     ssm_user
     * Column:    remark
     * Nullable:  false
     */
    private String remark;

    /**
     * 登录标志
     *
     * Table:     ssm_user
     * Column:    login_flag
     * Nullable:  false
     */
    private Character loginFlag;

    public User() {
    }

    public User(String userId, String userName, Character userType, Character userStatus,
                String userPwd, Integer lastUpdateDate, Integer lastUpdateTime,
                String remark, Character loginFlag) {
        this.userId = userId;
        this.userName = userName;
        this.userType = userType;
        this.userStatus = userStatus;
        this.userPwd = userPwd;
        this.lastUpdateDate = lastUpdateDate;
        this.lastUpdateTime = lastUpdateTime;
        this.remark = remark;
        this.loginFlag = loginFlag;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", userType=" + userType +
                ", userStatus=" + userStatus +
                ", userPwd='" + userPwd + '\'' +
                ", lastUpdateDate=" + lastUpdateDate +
                ", lastUpdateTime=" + lastUpdateTime +
                ", remark='" + remark + '\'' +
                ", loginFlag=" + loginFlag +
                '}';
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Character getUserType() {
        return userType;
    }

    public void setUserType(Character userType) {
        this.userType = userType;
    }

    public Character getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Character userStatus) {
        this.userStatus = userStatus;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public Integer getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Integer lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public Integer getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Integer lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Character getLoginFlag() {
        return loginFlag;
    }

    public void setLoginFlag(Character loginFlag) {
        this.loginFlag = loginFlag;
    }
}
