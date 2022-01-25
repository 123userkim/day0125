package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration
public class SpringConfig {
	
	@Bean //환경설정파일로 객체를 제공해줌
	//CommonsMultipartResolver: bean을 설정하여 파일 업로드를 가능하게 해줌
	public CommonsMultipartResolver multipartResolver() {
		return new CommonsMultipartResolver();
	}
}