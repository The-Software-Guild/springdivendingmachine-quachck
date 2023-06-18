package com.wileyedge.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wileyedge.dao.ItemDao;
import com.wileyedge.dao.ItemDaoImpl;
import com.wileyedge.model.VendingMachine;
import com.wileyedge.service.VendingMachineService;
import com.wileyedge.service.VendingMachineServiceImpl;
import com.wileyedge.view.UserIO;
import com.wileyedge.view.UserIOConsoleImpl;
import com.wileyedge.view.VendingMachineView;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = "com.wileyedge")
public class AppConfig {

	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
//	@Bean
//	public VendingMachine vendingMachine() {
//		return new VendingMachine();
//	}
//
//	@Bean
//	public ItemDao itemDao() {
//		return new ItemDaoImpl();
//	}
//	
//    @Bean
//    public VendingMachineService vendingMachineService(VendingMachine vendingMachine, ItemDao itemDao) {
//        return new VendingMachineServiceImpl(vendingMachine, itemDao);
//    }
//
//    @Bean
//    public VendingMachineView vendingMachineView(UserIO userIO) {
//        return new VendingMachineView(userIO);
//    }
//
//    @Bean
//    public UserIO userIO() {
//        return new UserIOConsoleImpl();
//    }
}
