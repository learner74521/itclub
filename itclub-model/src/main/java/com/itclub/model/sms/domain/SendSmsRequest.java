package com.itclub.model.sms.domain;

import java.io.Serializable;

/**
 * 阿里云短信服务请求体
 *
 * @author xinfeng
 * @since 1.0.0
 */
public class SendSmsRequest implements Serializable {

    /**
     * 访问的KEY
     */
    private String accessKey;

    /**
     * 访问的secret
     */
    private String accessSecret;

    /**
     * 区域选择
     */
    private String regionId = "cn-hangzhou";

    /**
     * 系统域名
     */
    private String sysDomain = "dysmsapi.aliyuncs.com";

    /**
     * 系统版本
     */
    private String sysVersion = "2017-05-25";

    /**
     * 手机号码
     */
    private String PhoneNumbers;

    /**
     * 签名
     */
    private String signName;

    /**
     * 模板
     */
    private String templateCode;

    /**
     * 验证码
     */
    private String code;

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getAccessSecret() {
        return accessSecret;
    }

    public void setAccessSecret(String accessSecret) {
        this.accessSecret = accessSecret;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getSysDomain() {
        return sysDomain;
    }

    public void setSysDomain(String sysDomain) {
        this.sysDomain = sysDomain;
    }

    public String getSysVersion() {
        return sysVersion;
    }

    public void setSysVersion(String sysVersion) {
        this.sysVersion = sysVersion;
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

    public String getPhoneNumbers() {
        return PhoneNumbers;
    }

    public void setPhoneNumbers(String phoneNumbers) {
        PhoneNumbers = phoneNumbers;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}