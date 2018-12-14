package com.xiaohe.ysjspt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import javax.servlet.MultipartConfigElement;

/**
 * @author wzq
 */
@SpringBootApplication
@EnableAsync
@MapperScan(basePackages = {"com.xiaohe.ysjspt.modules.*.dao"})
@EnableCaching
public class SuperJsptApplication {
    public static void main(String[] args) {
        SpringApplication.run(SuperJsptApplication.class, args);
    }

    /**
     * 配置上传文件大小的配置
     *
     * @return
     */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //  单个数据大小
        factory.setMaxFileSize("2MB");
        /// 总上传数据大小
        factory.setMaxRequestSize("10MB");
        return factory.createMultipartConfig();
    }
}
