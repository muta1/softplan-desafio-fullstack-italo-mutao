package com.muta1.italomutao.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;

import com.muta1.italomutao.security.jwt.JwtTokenUtil;
import com.muta1.italomutao.security.service.SecurityService;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Autowired
	private SecurityService detailsService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.any())              
          .paths(PathSelectors.any())                          
          .build();                                           
    }
	
	@Bean
	public SecurityConfiguration security() {
		String token;
		try {
			UserDetails userDetails = this.detailsService.loadUserByUsername("italo");
			token = jwtTokenUtil.generateToken(userDetails);
		} catch (Exception e) {
			e.printStackTrace();
			token = "";
		}

		return new SecurityConfiguration(null, null, null, null, "Bearer " + token, ApiKeyVehicle.HEADER,
				"Authorization", ",");
	}
}
