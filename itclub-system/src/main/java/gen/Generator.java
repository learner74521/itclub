package gen;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;

import java.util.Collections;

public class Generator {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/itclub?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8";
        FastAutoGenerator.create(url, "root", "123456")
                .globalConfig(builder -> {
                    builder.author("OnePiece") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .enableSwagger()
                            .outputDir("D://mybatisPlusGen"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.itclub.demo") // 设置父包名
                            .moduleName("system") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "D://mybatisPlusGen")); // 设置mapper生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("stu") // 设置需要生成的表名
                            .addTablePrefix(""); // 设置过滤表前缀
                })
                .templateEngine(new VelocityTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

}
