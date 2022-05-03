package com.senko.framework.config.swagger;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Swagger配置类
 *
 * @author senko
 * @date 2022/5/2 22:39
 */
@Configuration
public class SwaggerConfig {

    /**
     * swagger 3之后的文档类型就是OAS3了
     * 同样改变的还有路径 /swagger-ui.html ==> /swagger-ui/index.html
     *
     * 不过我这里是用了更加符合国人习惯的knife4j(swagger-bootstrap-ui)，所以路径就变成了/doc.html了
     */
    @Bean
    public Docket docket() {
        //swagger毕竟是前后端不分离，要引用一些网页静态资源，记得WebSecurity放行
        //   /swagger**/**      /webjars/**     /v3/**      /doc.html
        return new Docket(DocumentationType.OAS_30)
                //自动开启swagger
                .enable(true)
                // Open Api 3
                .apiInfo(getApiInfo())
                // 选择 构建者：api扫描 & 路径选择
                .select()
                // XXXSelectors 挺好记的
                // Api接口的扫描方式：带有@ApiOperation注解的方法
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                // 匹配请求路径：假如是 /api/** ==> /api/test1...，如果是any就相当于匹配所有controller的request path
                .paths(PathSelectors.any())
                .build();

                //如果项目用的鉴权系统是通过在headers里存储jwt token令牌来实现的，
                //swagger也可以在每个请求里在请求头中加入一个header
                //.securityContexts(securityContexts())  // 安全上下文
                //.securitySchemes(securitySchemes());  // 安全方案

    }

    /**
     * swagger文档信息
     * @return
     */
    private ApiInfo getApiInfo() {
        //都是构建者模式，构建者的方法都是return this，
        // 所以不是方法大于对象，必须要先new出实例才能调用方法
        return new ApiInfoBuilder()
                .title("标题：个人博客_接口文档")
                .description("描述：统一管理各项接口，虽然swagger只是方便了后端开发人员")
                .contact(new Contact("Senko", "https://space.bilibili.com/1041337349", "1641801056@qq.com"))
                .version("0.0.1")
                .build();
    }

    /*
    //留个模板在这，毕竟Jwt验证更加安全可靠，以后肯定还会用到

   //Apikey就相当于一个header
   private List<ApiKey> securitySchemes() {
      //设置请求头信息
      List<ApiKey> result = new ArrayList<>();
      //往headers里加入一个Authrorization header
      ApiKey apiKey = new ApiKey("Authorization", "Authorization", "Header");
      result.add(apiKey);
      return result;
  }

    private List<SecurityContext> securityContexts() {
        //设置需要登录认证的路径
        List<SecurityContext> result = new ArrayList<>();
        //SecurityContext也是用构建者模式生成的，不过SecurityReference是真难弄，他要传入的是数组!
        result.add(getContextByPath("/.*"));
        return result;
    }

    private SecurityContext getContextByPath(String pathRegex) {
        return SecurityContext.builder()
                //security认证域：global，相关apiKey：Authorization
                .securityReferences(defaultAuth())
                //需要的路径 /.* 全部
                .forPaths(PathSelectors.regex(pathRegex))
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        List<SecurityReference> result = new ArrayList<>();
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        result.add(new SecurityReference("Authorization", authorizationScopes));
        return result;
    }
     */

}
