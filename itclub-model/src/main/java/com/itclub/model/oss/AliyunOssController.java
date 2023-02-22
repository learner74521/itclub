package com.itclub.model.oss;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.io.FileUtil;
import com.itclub.common.core.domain.AjaxResult;
import com.itclub.model.oss.domain.AliyunOssDTO;
import com.itclub.model.oss.service.IAliyunOssService;
import com.itclub.model.oss.utils.AliyunOssUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * 阿里云OSS接口
 *
 * @author onePiece
 */
@RestController
@RequestMapping("/oss")
@RequiredArgsConstructor
public class AliyunOssController {

    private final IAliyunOssService aliyunOssService;

    /**
     * -上传文件
     * -自定义OSS存储路径
     */
    @PostMapping("/uploadFile")
    public AjaxResult uploadFile(MultipartFile file,
                                 @RequestParam(value = "path", defaultValue = "example") String path) {
        AliyunOssDTO ossResult= aliyunOssService.uploadFile(file, path);
        return AjaxResult.success(ossResult);
    }

    /**
     * 上传文件-指定目录
     */
    @PostMapping("/uploadFixed")
    public AjaxResult uploadFixed(MultipartFile file) {
        AliyunOssDTO ossResult= aliyunOssService.uploadFile(file);
        return AjaxResult.success(ossResult);
    }

    /**
     * 删除文件
     */
    @GetMapping("/deleteFile")
    public AjaxResult deleteFile(@RequestParam String fileName) {
        if (aliyunOssService.deleteFile(fileName)) {
            AjaxResult.success("成功");
        }
        return AjaxResult.error("删除失败");
    }

    /**
     * oss路径下获取文件带有效期的url，获取的url可下载
     */
    @GetMapping("/getOssUrl")
    public AjaxResult getOssUrl(@RequestParam String fileName) {
        return AjaxResult.success(aliyunOssService.getOssUrl(fileName));
    }

    /**
     * 预览PDF或图片
     */
    @GetMapping("/preview")
    public ResponseEntity<InputStreamResource> previewFile(@RequestParam String fileName) {
        // 获取文件类型
        String contentType = FileUtil.extName(fileName);
        // 获取文件流
        InputStream inputStream = aliyunOssService.getInputStream(fileName);
        // 预览
        HttpHeaders httpHeaders = new HttpHeaders();
        if ("pdf".equalsIgnoreCase(contentType)) {
            httpHeaders.add("Content-Type", MediaType.APPLICATION_PDF_VALUE);
        } else if ("jpg".equalsIgnoreCase(contentType) || "png".equalsIgnoreCase(contentType) || "jpeg".equalsIgnoreCase(contentType)) {
            httpHeaders.add("Content-Type", MediaType.IMAGE_JPEG_VALUE);
        }
        InputStreamResource inputStreamResource = new InputStreamResource(inputStream);
        return new ResponseEntity<>(inputStreamResource, httpHeaders, HttpStatus.OK);
    }

    /**
     * 列举所有的文件url
     */
    @GetMapping("/urlList")
    public AjaxResult urlList() {
        return AjaxResult.success(aliyunOssService.urlList());
    }

    /**
     * 图片转base64
     */
    @GetMapping("/base64")
    public AjaxResult base64(@RequestParam String fileName) {
        return AjaxResult.success(Base64.encode(aliyunOssService.getInputStream(fileName)));
    }

    /**
     * 文件转byte[]
     */
    @GetMapping("/bytes")
    public AjaxResult bytes(@RequestParam String fileName) {
        return AjaxResult.success(aliyunOssService.getBytes(fileName));
    }

}
