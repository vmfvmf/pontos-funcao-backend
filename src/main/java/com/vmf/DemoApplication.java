package com.vmf;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.vmf.mappers.AbstractContagemItemMapper;
import com.vmf.mappers.ContagemItemArquivoReferenciadoMapper;
import com.vmf.mappers.ContagemItemTransacaoMapper;
import com.vmf.mappers.ContagemMapper;
import com.vmf.mappers.GrupoMapper;
import com.vmf.mappers.ProjetoMapper;
import com.vmf.mappers.SistemaMapper;
import com.vmf.mappers.SprintMapper;

@SpringBootApplication
public class DemoApplication {
	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration()
		  .setFieldMatchingEnabled(true)
		  .setFieldAccessLevel(AccessLevel.PRIVATE)
		  .setSkipNullEnabled(true);
	    return modelMapper;
	}
	
	@Bean
	public ProjetoMapper dedMapper() {
	    return new ProjetoMapper();
	}
	
	@Bean
	public SistemaMapper sistemaMapper() {
	    return new SistemaMapper();
	}
	
	@Bean
	public SprintMapper sprintMapper() {
	    return new SprintMapper();
	}
	
	@Bean
	public ContagemMapper contagemMapper() {
	    return new ContagemMapper();
	}
	
	@Bean
	public AbstractContagemItemMapper abstractContagemItemMapper() {
	    return new AbstractContagemItemMapper();
	}
	
	@Bean
	public ContagemItemTransacaoMapper contagemItemTransacaoMapper() {
	    return new ContagemItemTransacaoMapper();
	}
	
	@Bean
	public ContagemItemArquivoReferenciadoMapper contagemItemArquivoReferenciadoMapper() {
	    return new ContagemItemArquivoReferenciadoMapper();
	}
	
	@Bean
	public GrupoMapper grupoMapper() {
	    return new GrupoMapper();
	}
	
	public static void main(String[] args) {
		
		SpringApplication.run(DemoApplication.class, args);
	}
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                .allowedOrigins("*") // Access-Control-Allow-Origin
                .allowedMethods("GET", "POST", "OPTIONS", "PUT", "DELETE")
                .allowedHeaders("Content-Type", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
                      "Access-Control-Request-Headers", "Access-Control-Allow-Origin")
                ;
            }
        };
    }
	
	@Bean
	public CorsFilter corsFilter() {

	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    CorsConfiguration config = new CorsConfiguration();
	    //config.setAllowCredentials(true); // you USUALLY want this
	    config.addAllowedOrigin("*");
	    config.addAllowedHeader("*");
	    config.addAllowedMethod("OPTIONS");
	    config.addAllowedMethod("HEAD");
	    config.addAllowedMethod("GET");
	    config.addAllowedMethod("PUT");
	    config.addAllowedMethod("POST");
	    config.addAllowedMethod("DELETE");
	    config.addAllowedMethod("PATCH");
	    source.registerCorsConfiguration("/**", config);
	    return new CorsFilter(source);
	}
//	@Bean
//	public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**")
//                .allowedOrigins("*")
//                .allowedMethods("GET", "POST", "OPTIONS", "PUT")
//                .allowedHeaders("Content-Type", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
//                                "Access-Control-Request-Headers")
//     			.exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials")
//                .allowCredentials(true).maxAge(3600);
//    }
//   };
//}
}
