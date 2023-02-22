package com.itclub.model.oss.service.Impl;

import cn.hutool.core.io.FastByteArrayOutputStream;
import cn.hutool.core.io.IoUtil;
import com.aliyun.oss.model.*;
import com.itclub.model.oss.config.AliyunOssConfig;
import com.itclub.model.oss.domain.AliyunOssDTO;
import com.itclub.model.oss.service.IAliyunOssService;
import com.itclub.model.oss.utils.AliyunOssUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 功能描述
 *
 * @author: onePiece
 */
@Component
public class AliyunOssServiceImpl implements IAliyunOssService {


    @Override
    public AliyunOssDTO uploadFile(MultipartFile file, String path) {
        InputStream inputStream;
        try {
            inputStream = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
            return new AliyunOssDTO(false, null, null, e.getMessage());
        }
        String fileType = file.getContentType();
        String fileName = AliyunOssUtil.setFilePath(file, path);
        String oldFilename = file.getOriginalFilename();
        // 上传文件
        return AliyunOssUtil.uploadInputStream(inputStream, fileType, oldFilename, fileName);
    }

    @Override
    public AliyunOssDTO uploadFile(MultipartFile file) {
        // 文件流
        InputStream inputStream;
        try {
            inputStream = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
            return new AliyunOssDTO(false, null, null, e.getMessage());
        }
        // 获取文件类型
        String fileType = file.getContentType();
        String fileName = AliyunOssUtil.setFilePath(file, null);
        String oldFilename = file.getOriginalFilename();
        // 上传文件
        return AliyunOssUtil.uploadInputStream(inputStream, fileType, oldFilename, fileName);
    }

    @Override
    public String getOssUrl(String fileName) {
        AliyunOssUtil.ossClient = AliyunOssUtil.initOSS();
        // 生成过期时间
        long expireEndTime = System.currentTimeMillis() + AliyunOssConfig.OSS_POLICY_EXPIRE * 1000;
        Date expiration = new Date(expireEndTime);
        GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(AliyunOssConfig.OSS_BUCKET_NAME, fileName);
        generatePresignedUrlRequest.setExpiration(expiration);
        URL url = AliyunOssUtil.ossClient.generatePresignedUrl(generatePresignedUrlRequest);
        return url.toString();
    }

    @Override
    public void downloadFile(String fileName, String localFileName) {
        AliyunOssUtil.ossClient = AliyunOssUtil.initOSS();
        // 下载OSS文件到指定目录。如果指定的本地文件存在会覆盖，不存在则新建。
        AliyunOssUtil.ossClient.getObject(new GetObjectRequest(AliyunOssConfig.OSS_BUCKET_NAME, fileName), new File(localFileName));
    }

    @Override
    public InputStream getInputStream(String fileName) {
        AliyunOssUtil.ossClient = AliyunOssUtil.initOSS();
        // 下载OSS文件到本地文件。如果指定的本地文件存在会覆盖，不存在则新建。
        return AliyunOssUtil.ossClient.getObject(new GetObjectRequest(AliyunOssConfig.OSS_BUCKET_NAME, fileName)).getObjectContent();
    }

    @Override
    public byte[] getBytes(String fileName) {
        InputStream inputStream = getInputStream(fileName);
        FastByteArrayOutputStream fastByteArrayOutputStream = IoUtil.read(inputStream);
        return fastByteArrayOutputStream.toByteArray();
    }

    @Override
    public boolean deleteFile(String fileName) {
        AliyunOssUtil.ossClient = AliyunOssUtil.initOSS();
        try {
            if (AliyunOssConfig.OSS_BUCKET_NAME == null || fileName == null) {
                return false;
            }
            GenericRequest request = new DeleteObjectsRequest(AliyunOssConfig.OSS_BUCKET_NAME).withKey(fileName);
            AliyunOssUtil.ossClient.deleteObject(request);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<String> urlList() {
        AliyunOssUtil.ossClient = AliyunOssUtil.initOSS();
        List<String> list = new ArrayList<>();
        // 构造ListObjectsRequest请求
        ListObjectsRequest listObjectsRequest = new ListObjectsRequest(AliyunOssConfig.OSS_BUCKET_NAME);
        // 列出文件
        ObjectListing listing = AliyunOssUtil.ossClient.listObjects(listObjectsRequest);
        // 遍历所有文件
        for (OSSObjectSummary objectSummary : listing.getObjectSummaries()) {
            // 把key全部转化成可以访问的url
            String url = getOssUrl(objectSummary.getKey());
            list.add(url);
        }
        return list;
    }


}
