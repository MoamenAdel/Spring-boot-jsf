package com.research.configurations;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DozerConfig {
	@Bean
	public DozerBeanMapper dozerBeanMapper() {

		DozerBeanMapper dozerBean = new DozerBeanMapper();
		dozerBean.setMappingFiles(Arrays.<String>asList("Mapping/ProjectMapping.xml", "Mapping/ProjectTypeMapping.xml",
				"Mapping/LFMMapping.xml", "Mapping/ProjectTypeMapping.xml,Mapping/TasksMapping.xml",
				"Mapping/TasksExpectedOutcomesMapping.xml"));

		return dozerBean;
	}
}
