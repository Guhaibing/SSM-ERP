/**
 * 公司：DLUT
 * 文件名：Md5Test
 * 作者：haibing
 * 时间：2020/1/9 15:29
 * 描述：
 */

package com.hundsun.xone.ssm.test;

import com.hundsun.xone.ssm.util.Md5Util;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class Md5Test {

    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String password = "123456778889";

        String cipher = Md5Util.getEncryptedPwd(password);

        System.out.println(cipher);
        System.out.println(cipher.length());

        if (Md5Util.validPassword(password, cipher)){
            System.out.println("密码一致");
        }
    }
}
