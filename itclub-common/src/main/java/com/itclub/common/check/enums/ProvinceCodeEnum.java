package com.itclub.common.check.enums;

/**
 * 省份代码枚举
 *
 * @author onePiece
 */
public enum ProvinceCodeEnum {

    /**
     * 省份代码枚举
     */
    BEIJING("11", "北京"),
    TIANJIN("12", "天津"),
    HEBEI("13", "河北"),
    SHANXI("14", "山西"),
    INNER_MONGOLIA("15", "内蒙古"),
    LIAONING("21", "辽宁"),
    JINLIN("22", "吉林"),
    HEILONGJIANG("23", "黑龙江"),
    SHANGHAI("31", "上海"),
    JIANGSU("32", "江苏"),
    ZHEJIANG("33", "浙江"),
    ANHUI("34", "安徽"),
    FUJIAN("35", "福建"),
    JIANGXI("36", "江西"),
    SHANDONG("37", "山东"),
    HENAN("41", "河南"),
    HUBEI("42", "湖北"),
    HUNAN("43", "湖南"),
    GUANGDONG("44", "广东"),
    GUANGXI("45", "广西"),
    HAINAN("46", "海南"),
    CHONGQING("50", "重庆"),
    SICHUAN("51", "四川"),
    GUIZHOU("52", "贵州"),
    YUNNAN("53", "云南"),
    TIBET("54", "西藏"),
    SHAANXI("61", "陕西"),
    GANSU("62", "甘肃"),
    QINGHAI("63", "青海"),
    NINGXIA("64", "宁夏"),
    SINKIANG("65", "新疆"),
    TAIWAN("71", "台湾"),
    HONGKONG("81", "香港"),
    MACAU("82", "澳门"),
    OTHER("65", "国外");

    public final String code;
    public final String desc;

    ProvinceCodeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 校验省份code是否正确
     *
     * @param code 省份code
     * @return true中国省份，false没找到对应省份代码
     */
    public static boolean checkCode(String code) {
        // TODO 等待添补字符串工具类
        if (code == null || code == "") {
            return false;
        }
        for (ProvinceCodeEnum value : values()) {
            if (value.code.equals(code)) {
                return true;
            }
        }
        return false;
    }
}