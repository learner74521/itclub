package com.itclub.app.common;

import com.itclub.common.config.ItClubConfig;
import com.itclub.common.core.domain.AjaxResult;
import com.itclub.common.utils.StringUtils;
import com.itclub.common.utils.file.FileUploadUtils;
import com.itclub.common.utils.file.FileUtils;
import com.itclub.framework.config.ServerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 本地上传下载
 *
 * @author onePiece
 */
@RestController
@RequestMapping("/model")
public class LocalFileController
{
    private static final Logger log = LoggerFactory.getLogger(LocalFileController.class);

    @Autowired
    private ServerConfig serverConfig;


    /**
     * 通用上传请求
     */
    @PostMapping("/local/upload")
    public AjaxResult uploadFile(MultipartFile file) throws Exception
    {
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getName());
        System.out.println(file.getResource().getFile());
        try
        {
            // 上传文件路径
            String filePath = ItClubConfig.getUploadPath();
            // 上传并返回新文件名称
            String fileName = FileUploadUtils.upload(filePath, file);
            String url = serverConfig.getUrl() + fileName;
            AjaxResult ajax = AjaxResult.success();
            ajax.put("fileName", fileName);
            ajax.put("url", url);
            return ajax;
        }
        catch (Exception e)
        {
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 通用下载请求
     *
     * @param fileName 文件名称
     * @param delete 是否删除
     */
    @GetMapping("/local/download")
    public void fileDownload(String fileName, Boolean delete, HttpServletResponse response, HttpServletRequest request)
    {
        try
        {
            if (!FileUtils.checkAllowDownload(fileName))
            {
                throw new Exception(StringUtils.format("文件名称({})非法，不允许下载。 ", fileName));
            }
            String realFileName = System.currentTimeMillis() + fileName.substring(fileName.indexOf("_") + 1);
            String filePath = ItClubConfig.getDownloadPath() + fileName;

            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            FileUtils.setAttachmentResponseHeader(response, realFileName);
            FileUtils.writeBytes(filePath, response.getOutputStream());
            if (delete)
            {
                FileUtils.deleteFile(filePath);
            }
        }
        catch (Exception e)
        {
            log.error("下载文件失败", e);
        }
    }

}
