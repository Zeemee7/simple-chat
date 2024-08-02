package simonerhardt.simplechat;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerTypePredicate;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import java.io.IOException;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		configurer.addPathPrefix("/api/v1", HandlerTypePredicate.forAnnotation(RestController.class));
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
				// Capture everything (REST controllers get priority over this, see above).
				.addResourceHandler("/**")
				// Add locations where files might be found. Direct file access is required during development.
				.addResourceLocations("classpath:/static/**", "file:src/main/resources/static/**").resourceChain(true)
				// Required for React Router: Everything except actual files are handled by index.html.
				.addResolver(new PathResourceResolver() {
					@Override
					protected Resource getResource(String resourcePath, Resource location) throws IOException {
						Resource requestedResource = location.createRelative(resourcePath);
						if (requestedResource.exists() && requestedResource.isReadable()) {
							return requestedResource;
						}
						return new ClassPathResource("/static/index.html");
					}
				});
	}
}
