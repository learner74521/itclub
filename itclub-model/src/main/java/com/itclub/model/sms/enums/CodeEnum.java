package com.itclub.model.sms.enums;

/**
 * 验证码枚举
 *
 * @author: onePiece
 */
public enum CodeEnum {


    SIX("SIX",6),

    FOUR("FOUR",4);

    private String en;

    private Integer value;

    CodeEnum(String en, Integer value) {
        this.en = en;
        this.value = value;
    }

    public static Integer ofValue(String name){
        for (CodeEnum codeEnum: values()){
            if (name.equals(codeEnum.en)){
                return codeEnum.value;
            }
        }
        return SIX.value;
    }
}
