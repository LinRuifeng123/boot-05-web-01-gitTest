package com.linruifeng.boot.config;

import com.linruifeng.boot.bean.Pet;
import com.linruifeng.boot.converter.GuiguMessageConverter;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.StringUtils;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;
import org.springframework.web.accept.ParameterContentNegotiationStrategy;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UrlPathHelper;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 有可能我们添加的自定义的功能会覆盖默认很多功能，导致一些默认的功能失效。
 *  大家考虑，上述功能除了我们完全自定义外？SpringBoot有没有为我们提供基于配置文件的快速修改媒体类型功能？
 *  怎么配置呢？【提示：参照SpringBoot官方文档web开发内容协商章节】
 * @author linruifeng
 * @create 2022-11-06 18:40
 */
@Configuration(proxyBeanMethods = true)
public class WebConfig /*implements WebMvcConfigurer*/ {

    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter(){
        HiddenHttpMethodFilter methodFilter = new HiddenHttpMethodFilter();
        methodFilter.setMethodParam("_m");
        return methodFilter;
    }

    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        return new WebMvcConfigurer() {

            // /**
            //  * 自定义内容协商策略，会覆盖原本默认的协商策略（基于请求头的协商策略）
            //  * @param configurer
            //  */
            // @Override
            // public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
            //     //Map<String, MediaType> mediaTypes
            //     Map<String, MediaType> mediaTypes = new HashMap<>();
            //     mediaTypes.put("json",MediaType.APPLICATION_JSON);
            //     mediaTypes.put("xml",MediaType.APPLICATION_XML);
            //     mediaTypes.put("gg",MediaType.parseMediaType("application/x-guigu"));
            //     //指定支持哪些参数对应的哪些媒体类型
            //     ParameterContentNegotiationStrategy parameterStrategy = new ParameterContentNegotiationStrategy(mediaTypes);
            //
            //     //由于使用自定义协商管理器之后，默认的基于请求头的协商管理器就被删除了
            //     //那么再放一个基于请求头的协商管理器
            //     HeaderContentNegotiationStrategy headerStrategy = new HeaderContentNegotiationStrategy();
            //
            //     configurer.strategies(Arrays.asList(parameterStrategy,headerStrategy));
            // }

            //自定义配置MessageConverter，在容器中添加WebMvcConfigurer中的extendMessageConverters（不覆盖默认的converter）
            @Override
            public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
                converters.add(new GuiguMessageConverter());
            }

            @Override
            public void configurePathMatch(PathMatchConfigurer configurer) {
                UrlPathHelper urlPathHelper = new UrlPathHelper();
                //     //设置为不移除；后面的内容。矩阵变量功能就可以生肖
                urlPathHelper.setRemoveSemicolonContent(false);
                configurer.setUrlPathHelper(urlPathHelper);
            }
            @Override
            //自定义Converter
            public void addFormatters(FormatterRegistry registry) {
                registry.addConverter(new Converter<String, Pet>() {
                    @Override
                    public Pet convert(String source) {
                        //阿猫,3
                        if(!StringUtils.isEmpty(source)){
                            Pet pet = new Pet();
                            String[] split = source.split(",");
                            pet.setName(split[0]);
                            pet.setAge(Integer.parseInt(split[1]));
                            return pet;
                        }
                        return null;
                    }
                });
            }

        };
    }


    // @Override
    // public void configurePathMatch(PathMatchConfigurer configurer) {
    //
    //     UrlPathHelper urlPathHelper = new UrlPathHelper();
    //     //设置为不移除；后面的内容。矩阵变量功能就可以生肖
    //     urlPathHelper.setRemoveSemicolonContent(false);
    //     configurer.setUrlPathHelper(urlPathHelper);
    // }


}


