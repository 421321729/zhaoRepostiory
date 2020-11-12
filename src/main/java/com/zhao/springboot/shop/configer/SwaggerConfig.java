package com.zhao.springboot.shop.configer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Value("${flag}")
    boolean flag;
    @Bean
    public  Docket docket(){
        return  new Docket(DocumentationType.SWAGGER_2).enable(flag)
                .apiInfo(apiInfo())
                .select().apis(RequestHandlerSelectors.basePackage("com.zhao.springboot.shop")).build().groupName("包路径");
    }
    @Bean
    public  Docket docket1(){
        return  new Docket(DocumentationType.SWAGGER_2).enable(flag)
                .apiInfo(apiInfo())
                .select().apis(RequestHandlerSelectors.withMethodAnnotation(GetMapping.class)).build().groupName("Get接口");
    }
    @Bean
    public  Docket docket2(){
        return  new Docket(DocumentationType.SWAGGER_2).enable(flag)
                .apiInfo(apiInfo())
                .select().apis(RequestHandlerSelectors.withMethodAnnotation(PostMapping.class)).build().groupName("Post请求");
    }
    private ApiInfo apiInfo(){
        Contact contact =new Contact("ZHAO", "", "zyj421321729@163.com");
        return  new ApiInfo("赵允舰的Swagger2测试", "便利店管理系统Api", "1.0", "https://www.baidu.com/",
                contact, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0", new ArrayList<VendorExtension>());

    }
}
