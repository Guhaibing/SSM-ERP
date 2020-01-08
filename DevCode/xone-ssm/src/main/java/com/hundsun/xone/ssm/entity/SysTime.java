/**
 * 公司：DLUT
 * 文件名：SysTime
 * 作者：haibing
 * 时间：2020/1/8 15:42
 * 描述：
 */

package com.hundsun.xone.ssm.entity;

public class SysTime {
    private Integer currDate;

    private Integer currTime;

    private Integer MillsTime;

    public Integer getCurrDate() {
        return currDate;
    }

    public void setCurrDate(Integer currDate) {
        this.currDate = currDate;
    }

    public Integer getCurrTime() {
        return currTime;
    }

    public void setCurrTime(Integer currTime) {
        this.currTime = currTime;
    }

    public Integer getMillsTime() {
        return MillsTime;
    }

    public void setMillsTime(Integer millsTime) {
        MillsTime = millsTime;
    }
}
