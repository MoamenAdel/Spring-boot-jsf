package com.research.configurations;

import java.util.Arrays;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DozerConfig {
	@Bean
	public DozerBeanMapper dozerBeanMapper() {

		DozerBeanMapper dozerBean = new DozerBeanMapper();
		dozerBean.setMappingFiles(Arrays.<String>asList("classpath:Mapping/ProjectMapping.xml",
				 "classpath:Mapping/ProjectTypeMapping.xml","classpath:Mapping/DocsMapping.xml","classpath:Mapping/PaymentRequestParent.xml"
				 ,"classpath:Mapping/PaymentRequest.xml"));

		return dozerBean;
	}
}