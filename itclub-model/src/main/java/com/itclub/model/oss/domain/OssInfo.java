package com.itclub.model.oss.domain;

/**
 * oss请求参数
 *
 * @author onePiece
 */
public class OssInfo {

    // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
    protected final static String endpoint = "http://oss-cn-beijing.aliyuncs.com";
    // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
    protected final static  String accessKeyId = "LTAI4GAusAJC1cBLT7B93D6B";
    protected final static  String accessKeySecret = "BzH8mLArEDGREByUa4FZr4wx5plJKZ";
    // 填写Bucket名称，例如examplebucket。
    protected final static   String bucketName = "zuduiba";
    // 指定上传后的文件路径域名，需要在控制台配置oss免费域名获取
    protected final static  String afterFilePath = "https://img.linkcool.fun";

}
