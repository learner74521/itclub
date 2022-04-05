package com.itclub.common.check;

import com.itclub.common.check.consts.PatternPool;

/**
 * 正则校验工具类
 *
 * @author OnePiece
 */
public class RexCheck {

    /**
     * 校验邮箱格式
     *
     * @param email 邮箱地址
     * @return 返回true格式正确，返回false格式错误
     */
    public static boolean checkEmail(String email) {
        if (StringCheck.isBlank(email)) {
            return false;
        }
        return PatternPool.EMAIL.matcher(email).matches();
    }

    /**
     * 校验大陆手机号码
     *
     * @param phone 大陆手机号码
     * @return 返回true格式正确，返回false格式错误
     */
    public static boolean checkPhone(String phone) {
        if (StringCheck.isBlank(phone)) {
            return false;
        }
        return PatternPool.MOBILE.matcher(phone).matches();
    }

    /**
     * 校验邮政编号格式
     *
     * @param zipCode 邮政编码
     * @return 返回true格式正确，返回false格式错误
     */
    public static boolean checkZipCode(String zipCode) {
        if (StringCheck.isBlank(zipCode)) {
            return false;
        }
        return PatternPool.ZIP_CODE.matcher(zipCode).matches();
    }
}