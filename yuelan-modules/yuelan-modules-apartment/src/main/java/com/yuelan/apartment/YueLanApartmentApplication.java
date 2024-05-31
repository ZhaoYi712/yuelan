package com.yuelan.apartment;

import com.yuelan.common.security.annotation.EnableCustomConfig;
import com.yuelan.common.security.annotation.EnableRyFeignClients;
import com.yuelan.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @description:
 * @Author: ZhaoYi
 * @date: 24-5-18 15:17
 */
@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
@SpringBootApplication
public class YueLanApartmentApplication {
    public static void main(String[] args) {
        SpringApplication.run(YueLanApartmentApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  监控中心启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
                "                 _ooOoo_                                 \n" +
                "                o8888888o                                \n" +
                "               88888888888                               \n" +
                "                (| ^_^ |)                                \n" +
                "                O|  =  /O                                \n" +
                "             ___//`---'\\___                             \n" +
                "            .'  \\|     |//  `.                          \n" +
                "           /  \\|||  :  |||// \\                         \n" +
                "          /  _||||| -:- |||||- \\                        \n" +
                "         |   | \\  -  /// |   |                          \n" +
                "         | \\_|  ''\\---/''  |   |                       \n" +
                "          \\  .-__  `-`  ___/-. /                        \n" +
                "        ___`. .'  /--.--\\  `. . ___                     \n" +
                "     .| | '<  `.___<|>_/___.'  > ' | |.                  \n" +
                "    | | :  `- \\`.;`\\ _ /`;.`/ - ` : | |                \n "+
                "    \\ `-.   \\_ __\\ /__ _/   .-` /  /                  \n" +
                "========`-.____`-.___\\____/___.-`____.-'========        \n "+
                "                     `=---='                               \n" +
                "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^         \n" +
                "     佛祖保佑       永不宕机       永无BUG                     "

        );
    }
}
