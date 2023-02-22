package com.itclub.model.oss.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 阿里云OSS配置
 *
 * @author onePiece
 */
@Configuration
@ConfigurationProperties(prefix = "aliyun.oss")
public class AliyunOssConfig implements InitializingBean {

    /** 阿里云 oss 站点 */
    private String endpoint;

    /** 阿里云 oss 自定义域名 */
    private String dns;

    /** 阿里云 oss 公钥 */
    private String accessKeyId;

    /** 阿里云 oss 私钥 */
    private String accessKeySecret;

    /** 阿里云 oss 文件根目录 */
    private String bucketName;

    /** 阿里云 oss 文件推荐路径 */
    private String filePath;


    /** 阿里云 oss 文件推荐路径 */
    private String fileName;

    /** url有效期(S) */
    private Long policyExpire;

    /** 上传文件大小(M) */
    private Long maxSize;

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getDns() {
        return dns;
    }

    public void setDns(String dns) {
        this.dns = dns;
    }

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

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getPolicyExpire() {
        return policyExpire;
    }

    public void setPolicyExpire(Long policyExpire) {
        this.policyExpire = policyExpire;
    }

    public Long getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(Long maxSize) {
        this.maxSize = maxSize;
    }

    @Override
    public String toString() {
        return "AliyunOssConfig{" +
                "endpoint='" + endpoint + '\'' +
                ", dns='" + dns + '\'' +
                ", accessKeyId='" + accessKeyId + '\'' +
                ", accessKeySecret='" + accessKeySecret + '\'' +
                ", bucketName='" + bucketName + '\'' +
                ", filePath='" + filePath + '\'' +
                ", fileName='" + fileName + '\'' +
                ", policyExpire=" + policyExpire +
                ", maxSize=" + maxSize +
                '}';
    }

    // 方便直接获取
    public static String OSS_END_POINT;
    public static String OSS_DNS;
    public static String OSS_ACCESS_KEY_ID;
    public static String OSS_ACCESS_KEY_SECRET;
    public static String OSS_BUCKET_NAME;
    public static String OSS_FILE_PATH;
    public static String OSS_FILE_NAME;
    public static Long OSS_POLICY_EXPIRE;
    public static Long OSS_MAX_SIZE;

    @Override
    public void afterPropertiesSet() {
        OSS_END_POINT = endpoint;
        OSS_DNS=dns;
        OSS_ACCESS_KEY_ID = accessKeyId;
        OSS_ACCESS_KEY_SECRET = accessKeySecret;
        OSS_BUCKET_NAME = bucketName;
        OSS_FILE_PATH=filePath;
        OSS_FILE_NAME=fileName;
        OSS_POLICY_EXPIRE = policyExpire;
        OSS_MAX_SIZE = maxSize;
    }

}
