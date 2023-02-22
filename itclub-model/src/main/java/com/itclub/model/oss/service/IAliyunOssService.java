package com.itclub.model.oss.service;

import com.itclub.model.oss.domain.AliyunOssDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

/**
 * 功能描述
 *
 * @author: 天鸣
 * @date: 2023-02-17
 */
public interface IAliyunOssService {

    /**
     * 上传文件-自定义路径
     *
     * @param file 上传文件
     * @param path 上传至OSS的文件完整路径，例：example/img
     * @return
     */
    AliyunOssDTO uploadFile(MultipartFile file, String path);

    /**
     * 上传文件-配置固定路径
     *
     * @param file 上传文件
     * @return
     */
    AliyunOssDTO uploadFile(MultipartFile file);

    /**
     * 根据文件名生成文件的访问地址（带过期时间）
     *
     * @param fileName
     * @return
     */
    String getOssUrl(String fileName);

    /**
     * 通过文件名下载文件
     *
     * @param fileName      要下载的文件名（OSS服务器上的）
     * @param localFileName 本地要创建的文件名（下载到本地的）
     */
    void downloadFile(String fileName, String localFileName);

    /**
     * 通过文件名获取文件流
     *
     * @param fileName 要下载的文件名（OSS服务器上的）
     * @return
     */
    InputStream getInputStream(String fileName);

    /**
     * 通过文件名获取byte[]
     *
     * @param fileName 要下载的文件名（OSS服务器上的）
     * @return
     */
    byte[] getBytes(String fileName);

    /**
     * 根据文件名删除文件
     *
     * @param fileName 需要删除的文件名
     * @return boolean 是否删除成功
     * @return
     */
     boolean deleteFile(String fileName);


    /**
     * 列举所有的文件url
     *
     * @return
     */
     List<String> urlList();
}
