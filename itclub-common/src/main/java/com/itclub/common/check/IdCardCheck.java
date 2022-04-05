package com.itclub.common.check;

import com.itclub.common.check.consts.PatternPool;
import com.itclub.common.check.enums.ProvinceCodeEnum;

/**
 * 身份证工具类
 *
 * @author onePiece
 */
public class IdCardCheck {

    /**
     * 18位身份证：
     * <ol>
     *     <li>第1、2位数字：省份</li>
     *     <li>第3、4位数字：城市</li>
     *     <li>第5、6位数字：区县</li>
     *     <li>第7-14位数字：出生年月日</li>
     *     <li>第15、16位数字：派出所代码</li>
     *     <li>第17位数字：性别，奇数为男性，偶数为女性</li>
     *     <li>第18位数字：校验码，0-9或x表示</li>
     * </ol>
     *
     * 校验第18为的合法性：
     * <ol>
     *     <li>将前面的身份证号码17位数分别乘以不同的系数。从第一位到第十七位的系数分别为：7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4 2</li>
     *     <li>将这17位数字和系数相乘的结果相加。</li>
     *     <li>用加出来和除以11，看余数是多少？</li>
     *     <li>余数只可能有0 1 2 3 4 5 6 7 8 9 10这11个数字。其分别对应的最后一位身份证的号码为1 0 X 9 8 7 6 5 4 3 2。</li>
     *     <li>通过上面得知如果余数是2，就会在身份证的第18位数字上出现罗马数字的Ⅹ。如果余数是10，身份证的最后一位号码就是2。</li>
     * </ol>
     */

    /**
     * 大陆身份证位数
     */
    private static final int CHINA_ID_CARD_MAX_LEN = 18;

    /**
     * 港澳台身份证位数
     */
    private static final int CHINA_SPECIALLY_ID_CARD_MAX_LEN = 10;

    /**
     * 大陆老式身份证馋
     */
    private static final int CHINA_OLD_ID_CARD_MAX_LEN = 15;

    /**
     * 加权因子
     */
    private static final int[] POWER = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};

    /**
     * 大陆身份证第18位序列
     */
    private static final String[] ID_CARD_18 = {"1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"};

    /**
     * 加权因子计算后取余
     */
    private static final int ID_CARD_REM = 11;

    public static boolean isValidateIdCard(String idCard) {
        // 去空格
        idCard = idCard.trim();
        if(StringCheck.isEmpty(idCard)){
            return false;
        }
        int idCardLen = idCard.length();
        // 校验长度
        if (CHINA_ID_CARD_MAX_LEN != idCardLen) {
            return false;
        }
        return isValidate18IdCard(idCard);
    }

    private static boolean isValidate18IdCard(String idCard) {
        // 前17位
        String idCard17 = idCard.substring(0, 17);
        // 第18位
        String idCardLastNum = idCard.substring(17, 18).toLowerCase();
        // 校验前17为是否都是数字
        boolean idCard17CheckResult = PatternPool.NUMBERS.matcher(idCard17).matches();
        if (!idCard17CheckResult) {
            return false;
        }
        // 校验省份代码
        String province = idCard17.substring(0, 2);
        boolean provinceCheckResult = ProvinceCodeEnum.checkCode(province);
        if (!provinceCheckResult) {
            return false;
        }
        // 校验生日代码
        String birthday = idCard17.substring(6, 14);
        boolean birthdayCheckResult = PatternPool.BIRTHDAY.matcher(birthday).matches();
        if (!birthdayCheckResult) {
            return false;
        }
        // 校验第18位

        return checkIdCard18Num(idCard17, idCardLastNum);
    }

    /**
     * 校验大陆身份证第18位是否正确
     *
     * @param idCard17      身份证前17位
     * @param idCardLastNum 身份证最后一份
     * @return 返回true则第18位正确，返回false则第18位错误
     */
    private static boolean checkIdCard18Num(String idCard17, String idCardLastNum) {
        char[] idCardChars = idCard17.toCharArray();
        // 加权
        int sum = 0;
        if (POWER.length == idCardChars.length) {
            for (int i = 0; i < idCardChars.length; i++) {
                sum += Integer.parseInt(String.valueOf(idCardChars[i])) * POWER[i];
            }
        }
        // 取余
        if (sum == 0) {
            return false;
        }
        int power18Num = sum % ID_CARD_REM;
        // 余数只可能在0-10之间
        if (power18Num > 10) {
            return false;
        }
        String check18IdCard = ID_CARD_18[power18Num];
        if (!idCardLastNum.equals(check18IdCard)) {
            return false;
        }
        return true;
    }

    // 等待后续补充校验港澳台身份证编码
}