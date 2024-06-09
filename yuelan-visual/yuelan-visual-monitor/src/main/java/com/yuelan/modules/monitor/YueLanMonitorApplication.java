package com.yuelan.modules.monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import de.codecentric.boot.admin.server.config.EnableAdminServer;

/**
 * 监控中心
 * 
 * @author ruoyi
 */
@EnableAdminServer
@SpringBootApplication
public class YueLanMonitorApplication
{
    public static void main(String[] args) {
        SpringApplication.run(YueLanMonitorApplication.class, args);
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
