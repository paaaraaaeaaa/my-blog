package com.wanted.fileupload;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/* comment.
*   WebMvcConfigurer
*   -> 인터셉터 등록, 정적인 리소스 핸들링!!
* */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /* comment.
        *   <img src="파일경로"> 브라우저에서 src 경로에 대한 요청이 일어나고 있다. == 이미지 내놔!!
        *   /uploads/abc.png : 요청 URL
        *   저장 폴더 경로를 연결한다.
        * */
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:" + uploadDir + "/");
    }
}
