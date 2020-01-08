/**
 * 公司：DLUT
 * 文件名：ResultInfo
 * 作者：haibing
 * 时间：2020/1/8 14:06
 * 描述：
 */

package com.hundsun.xone.ssm.dao.support;

public class ResultInfo {

    /**
     * 操作备注串
     */
    private String opRemark;
    /**
     * 流水序号
     */
    private Long serialNo;


    public ResultInfo() {
        opRemark = "";
        serialNo = 0L;
    }

    public ResultInfo(String opRemark, Long serialNo) {
        this.opRemark = opRemark;
        this.serialNo = serialNo;
    }

    public String getOpRemark() {
        return opRemark;
    }

    public void setOpRemark(String opRemark) {
        this.opRemark = opRemark;
    }

    public Long getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(Long serialNo) {
        this.serialNo = serialNo;
    }
}
