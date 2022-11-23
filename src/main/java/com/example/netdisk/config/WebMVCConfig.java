package com.example.netdisk.config;

import com.example.netdisk.handler.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebMVCConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    //跨域
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedHeaders("*")
                .allowedOriginPatterns("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE");
    }

    @Bean
    public CorsFilter corsFilter(){
        UrlBasedCorsConfigurationSource source= new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOriginPattern("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**",config);
        return new CorsFilter(source);
    }

    //拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/Upload/*")
                .addPathPatterns("/File/*")
                .addPathPatterns("/Folder/*")
                .addPathPatterns("/Sharing/Add");
    }


    //访问文件路径
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //访问路径
        registry.addResourceHandler("/api/upload/**")
                //映射真实路径
                .addResourceLocations("file:" + System.getProperty("user.dir") + "/");
    }



//    @Bean
//    public ServletRegistrationBean ServletRegistrationbean(){
//        ServletRegistrationBean bean = new ServletRegistrationBean();
//        bean.setServlet(new TestServlet());
//        List<String> list =new ArrayList<>();
//        list.add("/TestServlet");
//        bean.setUrlMappings(list);
//        return bean;
//    }

}
