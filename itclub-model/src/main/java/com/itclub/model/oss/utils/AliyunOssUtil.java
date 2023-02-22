package com.itclub.model.oss.utils;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.aliyun.oss.model.*;
import com.itclub.common.utils.DateUtils;
import com.itclub.common.utils.uuid.IdUtils;
import com.itclub.model.oss.config.AliyunOssConfig;
import com.itclub.model.oss.domain.AliyunOssDTO;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;

/**
 * 阿里云OSS工具类
 *
 * @author onePiece
 */
@Component
public class AliyunOssUtil {

    /**
     * oss 工具客户端
     */
    public static volatile OSSClient ossClient = null;

    /**
     * 单例模式
     * 初始化 oss 客户端
     */
    public static OSSClient initOSS() {
        if (ossClient == null) {
            synchronized (AliyunOssUtil.class) {
                if (ossClient == null) {
                    ossClient = new OSSClient(AliyunOssConfig.OSS_END_POINT,
                            new DefaultCredentialProvider(AliyunOssConfig.OSS_ACCESS_KEY_ID, AliyunOssConfig.OSS_ACCESS_KEY_SECRET),
                            new ClientConfiguration());
                }
            }
        }
        return ossClient;
    }

    /**
     * 上传文件-自定义路径
     *
     * @param inputStream 上传文件流
     * @param fileType    文件类型，例：png
     * @param fileName    上传至OSS的文件完整路径，例：cf/abc.png
     *                    上传至根目录，例：abc.png
     * @return
     */
    public static AliyunOssDTO uploadInputStream(InputStream inputStream, String fileType, String oldFilename, String fileName) {
        if (inputStream == null) {
            return new AliyunOssDTO(false, null, null, "文件不能为空");
        }
        // 上传文件最大值 MB->bytes
        long maxSize = AliyunOssConfig.OSS_MAX_SIZE * 1024 * 1024;
        // 本次上传文件的大小
        long fileSize = getInputStreamSize(inputStream);
        if (fileSize <= 0 || fileSize > maxSize) {
            return new AliyunOssDTO(false, null, null, "请检查文件大小");
        }

        // 上传文件
        return putFile(inputStream, fileType, oldFilename, fileSize, fileName);
    }

    /**
     * 上传文件
     *
     * @param input
     * @param fileType
     * @param fileName
     * @return
     */
    private static AliyunOssDTO putFile(InputStream input, String fileType, String oldFilename, Long fileSize, String fileName) {
        ossClient = initOSS();
        try {
            // 创建上传Object的Metadata
            ObjectMetadata meta = new ObjectMetadata();
            // 设置上传内容类型
            meta.setContentType(fileType);
            //被下载时网页的缓存行为
            meta.setCacheControl("no-cache");
            //创建上传请求
            PutObjectRequest request = new PutObjectRequest(AliyunOssConfig.OSS_BUCKET_NAME, fileName, input, meta);
            //上传文件
            ossClient.putObject(request);
            //预览地址
            String url = AliyunOssConfig.OSS_DNS + "/" + fileName;
            //获取上传成功的文件地址
            return new AliyunOssDTO(true, fileName, oldFilename, fileSize, url, "上传成功");
        } catch (OSSException | ClientException e) {
            e.printStackTrace();
            return new AliyunOssDTO(false, fileName, null, e.getMessage());
        }
    }

    /**
     * 现象：inputStream.available() == 0 但实际文件不为空
     * 原因：从网络中读取InputStream后，可能因网络质量一次读取后InputStream长度为0，这里最多读5次，没读到就视为0
     */
    private static long getInputStreamSize(InputStream inputStream) {
        long fileSize = 0;
        int cunt = 1;
        try {
            while (fileSize == 0 && cunt <= 5) {
                fileSize = inputStream.available();
                cunt++;
            }
        } catch (IOException ignored) {
        }
        return fileSize;
    }

    /**
     * setFilePath 设置文件所在文件夹
     */
    public static String setFilePath(MultipartFile file, String path) {
        String fileName;
        String fileNameStr = setFileName(file);
        if (null != path && !"".equals(path)) {
            fileName = path + "/" + DateUtils.dateTime() + "/" + fileNameStr;
        } else {
            fileName = AliyunOssConfig.OSS_FILE_PATH + "/" + DateUtils.dateTime() + "/" + fileNameStr;
        }
        return fileName;


    }

    /**
     * fileName 重新命名文件,防止文件名重复,默认uuid
     */
    public static String setFileName(MultipartFile file) {
        String fileNameStr;
        String suffixName = FilenameUtils.getExtension(file.getOriginalFilename());
        if ("timestamp".equals(AliyunOssConfig.OSS_FILE_NAME)) {
            fileNameStr = System.currentTimeMillis() + "" + new SecureRandom().nextInt(0x0400);
        } else {
            fileNameStr = IdUtils.fastSimpleUUID();
        }
        // 生成上传文件名
        fileNameStr = fileNameStr + "." + suffixName;
        return fileNameStr;


    }
}
