package es.albergonpi.exercises.backuser.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfiguration {

	@Bean(name = "modelMapper")
	public ModelMapper getModelMapper(){
		ModelMapper mapper = new ModelMapper();
		return mapper;
	}
}
