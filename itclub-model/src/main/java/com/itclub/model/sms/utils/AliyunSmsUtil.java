package com.itclub.model.sms.utils;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.tea.TeaException;
import com.aliyun.teaopenapi.models.Config;
import com.aliyun.teautil.models.RuntimeOptions;
import com.itclub.common.core.text.Convert;
import com.itclub.model.sms.config.AliyunSmsConfig;
import com.itclub.model.sms.enums.CodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 功能描述
 *
 * @author: onePiece
 */
@Component
@Slf4j
public class AliyunSmsUtil {

    public static Client createClient() throws Exception {
        Config config = new Config()
                .setAccessKeyId(AliyunSmsConfig.SMS_ACCESS_KEY_ID)
                .setAccessKeySecret(AliyunSmsConfig.SMS_ACCESS_KEY_SECRET);
        // 访问的域名
        config.endpoint = AliyunSmsConfig.SMS_END_POINT;
        return new Client(config);
    }

    public boolean sendSms(String phone) throws Exception {
        String template = "{\"code\": " + this.code() + "}";
        Client client = AliyunSmsUtil.createClient();
        SendSmsRequest sendSmsRequest = new SendSmsRequest()
                .setSignName(AliyunSmsConfig.SMS_SIGN_NAME)
                .setPhoneNumbers(phone)
                .setTemplateCode(template);
        RuntimeOptions runtime = new RuntimeOptions();
        try {
            // 复制代码运行请自行打印 API 的返回值
            client.sendSmsWithOptions(sendSmsRequest, runtime);
        } catch (TeaException error) {
            log.error("发送失败：",error.message);
        } catch (Exception _error) {
            log.error("发送失败：",_error.getMessage());
        }
        return true;
    }

    /** 随机生成验证码 */
    public String code(){
        Integer count = CodeEnum.ofValue(AliyunSmsConfig.SMS_CODE_COUNT);
        return String.valueOf(Convert.toInt((Math.random() * 9 + 1) * Math.pow(10,count)));
    }

}
