package com.itclub.model.oss;

import com.aliyun.oss.*;
import com.aliyun.oss.model.PutObjectRequest;
import com.itclub.common.core.domain.AjaxResult;
import com.itclub.common.utils.StringUtils;
import com.itclub.common.utils.file.MimeTypeUtils;
import com.itclub.model.oss.domain.OssInfo;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.net.URL;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * oss上传下载
 *
 * @author onePiece
 */
@RestController
@RequestMapping("/model")
public class OssFileController extends OssInfo {

    // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
    String endpoint = super.endpoint;
    // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
    String accessKeyId = super.accessKeyId;
    String accessKeySecret = super.accessKeySecret;
    // 填写Bucket名称，例如examplebucket。
    String bucketName = super.bucketName;
    // 填写Object完整路径，完整路径中不能包含Bucket名称。
    String objectName;
    // 指定上传后的文件路径域名，需要配控制台配置oss免费域名
    String afterFilePath = super.afterFilePath;


    @PostMapping("/oss/uploadOss")
    public AjaxResult uploadFile(MultipartFile file) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        //文件路径
        String suffixName = FilenameUtils.getExtension(file.getOriginalFilename());

        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try {
            if (StringUtils.isEmpty(suffixName)) {
                // 获取文件的后缀名
                suffixName = suffixName.substring(MimeTypeUtils.getExtension(file.getContentType()).lastIndexOf("."));
            }
            // 生成上传文件名
            String finalFileName = System.currentTimeMillis() + "" + new SecureRandom().nextInt(0x0400) + "." + suffixName;

            objectName = "degree" + "/" + sdf.format(new Date()) + "/" + finalFileName;

            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, new File(file.getOriginalFilename()));

            ossClient.putObject(putObjectRequest);
            // 设置URL过期时间为1小时。
            Date expiration = new Date(System.currentTimeMillis() + 3600 * 1000);
            // 生成以GET方法访问的签名URL，访客可以直接通过浏览器访问相关内容。
            URL downloadUrl = ossClient.generatePresignedUrl(bucketName, objectName, expiration);
            String fileName = afterFilePath + "/" + objectName;
            ossClient.shutdown();
            // 上传并返回新文件名称
            AjaxResult ajax = AjaxResult.success();
            ajax.put("fileName", fileName);
            ajax.put("url", downloadUrl);
            return ajax;
        } catch (OSSException oe) {
            System.out.println("oss异常");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
            return AjaxResult.error(oe.getErrorMessage());
        } catch (ClientException ce) {
            System.out.println("网络异常");
            System.out.println("Error Message:" + ce.getMessage());
            return AjaxResult.error(ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }

}
