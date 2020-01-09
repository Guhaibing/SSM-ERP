/**
 * 公司：DLUT
 * 文件名：Base64Test
 * 作者：haibing
 * 时间：2020/1/9 10:10
 * 描述：
 */

package com.hundsun.xone.ssm.test;

import java.util.Arrays;
import java.util.Base64;

public class Base64Test {

    public static void main(String[] args) {
        String base64 = "升职加薪";

        Base64.Encoder encoder = Base64.getEncoder();

        byte[] bytes = encoder.encode(base64.getBytes());

        System.out.println("bytes=" + Arrays.toString(bytes));

        Base64.Decoder decoder = Base64.getDecoder();

        byte[] bytes1 = decoder.decode(bytes);

        String data = new String(bytes1);

        System.out.println(data);
    }
}
