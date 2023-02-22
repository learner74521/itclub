package com.itclub.model.sms.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * sms配置
 *
 * @author: onePiece
 */
@Configuration
@ConfigurationProperties("aliyun.sms")
public class AliyunSmsConfig implements InitializingBean {

    /** 阿里云 sms 公钥 */
    private String accessKeyId;

    /** 阿里云 sms 私钥 */
    private String accessKeySecret;

    /** 阿里云 oss 站点 */
    private String endpoint;

    /** 阿里云 oss 签名 */
    private String signName;

    /** 阿里云 oss 模板 */
    private String templateCode;

    /** 阿里云 oss 验证码位数  支持6位：SIX，4位：FOUR */
    private String codeCount;

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getSignName() {
        return signName;
    }

    public void setSignName(String signName) {
        this.signName = signName;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    public String getCodeCount() {
        return codeCount;
    }

    public void setCodeCount(String codeCount) {
        this.codeCount = codeCount;
    }

    @Override
    public String toString() {
        return "AliyunSmsConfig{" +
                "accessKeyId='" + accessKeyId + '\'' +
                ", accessKeySecret='" + accessKeySecret + '\'' +
                ", endpoint='" + endpoint + '\'' +
                ", signName='" + signName + '\'' +
                ", templateCode='" + templateCode + '\'' +
                ", codeCount='" + codeCount + '\'' +
                '}';
    }

    public static String SMS_END_POINT;

    public static String SMS_ACCESS_KEY_ID;

    public static String SMS_ACCESS_KEY_SECRET;

    public static String SMS_SIGN_NAME;

    public static String SMS_TEMPLATE_CODE;

    public static String SMS_CODE_COUNT;

    @Override
    public void afterPropertiesSet() throws Exception {
        SMS_END_POINT = endpoint;
        SMS_ACCESS_KEY_ID = accessKeyId;
        SMS_ACCESS_KEY_SECRET = accessKeySecret;
        SMS_SIGN_NAME = signName;
        SMS_TEMPLATE_CODE = templateCode;
        SMS_CODE_COUNT = codeCount;
    }

}
