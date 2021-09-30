package site.ilemon.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@EnableWebMvc
public class SystemConfig implements WebMvcConfigurer{


	@Override
	public void addArgumentResolvers(
			List<HandlerMethodArgumentResolver> resolvers) {
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/css/*").addResourceLocations("classpath:/static/css/");
		registry.addResourceHandler("/js/*").addResourceLocations("classpath:/static/js/");
		registry.addResourceHandler("/images/*").addResourceLocations("classpath:/static/images/");
		registry.addResourceHandler("/fonts/*").addResourceLocations("classpath:/static/fonts/");
		registry.addResourceHandler("/*.JPG").addResourceLocations("classpath:/static/");
	}
	
	
	
	

	
}
