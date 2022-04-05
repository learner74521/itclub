package com.itclub.common.check.enums;

/**
 * 城市代码枚举
 *
 * @author onePiece
 */
public enum CityCodeEnum {
    /**
     * 台湾省的城市
     */
    TAIWAN_A("A", "10"),
    TAIWAN_B("B", "11"),
    TAIWAN_C("C", "12"),
    TAIWAN_D("D", "13"),
    TAIWAN_E("E", "14"),
    TAIWAN_F("F", "15"),
    TAIWAN_G("G", "16"),
    TAIWAN_H("H", "17"),
    TAIWAN_J("J", "18"),
    TAIWAN_K("K", "19"),
    TAIWAN_L("L", "20"),
    TAIWAN_M("M", "21"),
    TAIWAN_N("N", "22"),
    TAIWAN_P("P", "23"),
    TAIWAN_Q("Q", "24"),
    TAIWAN_R("R", "25"),
    TAIWAN_S("S", "26"),
    TAIWAN_T("T", "27"),
    TAIWAN_U("U", "28"),
    TAIWAN_V("V", "29"),
    TAIWAN_W("W", "30"),
    TAIWAN_X("X", "31"),
    TAIWAN_Y("Y", "32"),
    TAIWAN_Z("Z", "33"),
    TAIWAN_I("I", "34"),
    TAIWAN_O("O", "45"),

    /**
     * 香港的市级
     */
    HONGKONG_A("A", "1"),
    HONGKONG_B("B", "2"),
    HONGKONG_C("C", "3"),
    HONGKONG_R("R", "18"),
    HONGKONG_U("U", "21"),
    HONGKONG_Z("Z", "26"),
    HONGKONG_X("X", "24"),
    HONGKONG_W("W", "23"),
    HONGKONG_O("O", "15"),
    HONGKONG_N("N", "14");

    public final String code;
    public final String number;

    CityCodeEnum(String code, String desc) {
        this.code = code;
        this.number = desc;
    }

    /**
     * 校验code是否存在
     *
     * @param code 需要校验的城市code
     * @return 返回true则存在，返回false则不存在
     */
    public static boolean checkCode(String code) {
        for (CityCodeEnum city : values()) {
            if (city.code.equals(code)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 校验numer是否正确
     *
     * @param code   城市code
     * @param number 需要校验的number
     * @return 返回true则存在，返回false则不存在
     */
    public static boolean checkNumber(String code, String number) {
        for (CityCodeEnum city : values()) {
            if (city.code.equals(code)) {
                if (city.number.equals(number)) {
                    return true;
                }
            }
        }
        return false;
    }
}