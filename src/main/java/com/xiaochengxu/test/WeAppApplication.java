package com.xiaochengxu.test;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import com.xiaochengxu.test.repository.AppKeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.xiaochengxu.test.config.WxAuth;
import com.xiaochengxu.test.entity.AppKey;

/**
 * 
 * @author xiefengchang
 *
 */
@SpringBootApplication
@EnableConfigurationProperties(value={WxAuth.class})
public class WeAppApplication implements CommandLineRunner {

	@Autowired
	private Environment env;
	
	@Autowired
	private AppKeyRepository appKeyRepository;

	
	private static ImmutableMap<String, String>errorCodeMap = null;
	static {
		try {
			Properties prop = PropertiesLoaderUtils.loadAllProperties("error_code.properties");
			errorCodeMap = Maps.fromProperties(prop);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		SpringApplication.run(WeAppApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		appKeyRepository.deleteAll();
		// create AppKey object and save it in DB as this is the back end for one wechat app only
		String appId = env.getProperty("wxapp.appId");
		String secret = env.getProperty("wxapp.secret");
		
		appKeyRepository.save(new AppKey(appId, secret, new Date(), new Date(), "1", false));
	}
	
	@Bean
	public ImmutableMap<String, String> errorCodeMap(){
		return errorCodeMap;
	}
}
