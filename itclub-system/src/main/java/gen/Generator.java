package gen;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Generator {

    private static final String prefix = "E:/itclub/itclub-main/src/main/java/com/itclub/app/";

    public static void main(String[] args) {

        Map<OutputFile, String> outputFileStringMap = new HashMap<>(10);
        outputFileStringMap.put(OutputFile.controller, prefix + "controller");
        outputFileStringMap.put(OutputFile.serviceImpl, prefix + "service/impl");
        outputFileStringMap.put(OutputFile.service, prefix + "service");
        outputFileStringMap.put(OutputFile.entity, prefix + "domain");
        outputFileStringMap.put(OutputFile.mapper, prefix + "mapper");
        outputFileStringMap.put(OutputFile.xml, prefix + "mapper");
        String url = "jdbc:mysql://192.168.80.249:3306/alumni_test?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8";
        FastAutoGenerator.create(url, "root", "123456")
                .globalConfig(builder -> {
                    builder.author("onePiece") // 设置作者
                            .enableSwagger(); // 开启 swagger 模式
                })
                .packageConfig(builder -> {
                    builder.parent("com.itclub.app") // 设置父包名
                            .moduleName("") // 设置父包模块名
                            .pathInfo(outputFileStringMap);

                })
                //策略配置
                .strategyConfig(builder -> {
                    builder.addInclude("") // 设置需要生成的表名
                            .addTablePrefix(""); // 设置过滤表前缀
                })

                .templateEngine(new VelocityTemplateEngine())
                .execute();
    }

}

