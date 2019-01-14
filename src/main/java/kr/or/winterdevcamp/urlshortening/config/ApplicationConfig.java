package kr.or.winterdevcamp.urlshortening.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


@Configuration
@ComponentScan(basePackages = { "kr.or.winterdevcamp.urlshortening.dao",  "kr.or.winterdevcamp.urlshortening.service"})
@Import({ DBConfig.class })
public class ApplicationConfig {

}
