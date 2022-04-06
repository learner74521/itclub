package com.itclub;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动程序
 *
 * @author onePiece
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class ItClubApplication
{
    public static void main(String[] args) {

//        System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(ItClubApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  ITClub启动成功   ლ(´ڡ`ლ)ﾞ  \n");

    }
}
