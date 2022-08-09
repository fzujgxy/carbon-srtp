package com.example.demo.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;
/**
 * @program: Java
 * @author: HarryGao
 * @create: 2022-08-09 00:14
 */
@Configuration
public class KaptchaConfig {

    @Bean
    public DefaultKaptcha  getDefalutKaptcha() {
        Properties properties = new Properties();
        //不带边框
        properties.put("kaptcha.border", "no");
        //字符颜色
        properties.put("kaptcha.textproducer.font.color", "black");
        //字符间距
        properties.put("kaptcha.textproducer.char.space", "5");
        Config config = new Config(properties);
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }
}
