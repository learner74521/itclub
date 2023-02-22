package com.itclub.model.oss.domain;

/**
 * 阿里云OSS上传结果
 *
 * @author onePiece
 */
public class AliyunOssDTO {
    /**
     * 上传是否成功
     */
    private boolean success;

    /**
     * 上传的文件名（如果使用自定义文件路径，会返回完整的路径+文件名，例：cf/abc.png）
     */
    private String fileName;

    /**
     * 原文件名
     */
    private String oldFileName;

    /**
     * 文件大小
     */
    private Long fileSize;

    /**
     * 上传成功的返回url（带过期时间）
     */
    private String url;

    /**
     * 提示信息
     */
    private String msg;


    public AliyunOssDTO(boolean success, String fileName, String url, String msg) {
        this.success = success;
        this.fileName = fileName;
        this.url = url;
        this.msg = msg;
    }

    public AliyunOssDTO(boolean success, String fileName, String oldFileName, Long fileSize, String url, String msg) {
        this.success = success;
        this.fileName = fileName;
        this.oldFileName = oldFileName;
        this.fileSize = fileSize;
        this.url = url;
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getOldFileName() {
        return oldFileName;
    }

    public void setOldFileName(String oldFileName) {
        this.oldFileName = oldFileName;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "AliyunOssResult{" +
                "success=" + success +
                ", fileName='" + fileName + '\'' +
                ", oldFileName" + oldFileName + '\'' +
                ", fileSize" + fileSize + '\'' +
                ", url='" + url + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}