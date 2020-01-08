/**
 * 公司：DLUT
 * 文件名：TimeUtil
 * 作者：haibing
 * 时间：2020/1/8 15:50
 * 描述：
 */

package com.hundsun.xone.ssm.util;

import com.hundsun.xone.ssm.dao.SysDAO;
import com.hundsun.xone.ssm.entity.SysTime;

import java.text.SimpleDateFormat;

public class TimeUtil {

    private static final ThreadLocal<SimpleDateFormat> SDF = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyyMMddHHmmss"));

    public static SysTime getSysTime(){
        return SpringContextUtil.getBean(SysDAO.class).getSysTimeMy();
    }
}
