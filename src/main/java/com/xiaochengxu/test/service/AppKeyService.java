package com.xiaochengxu.test.service;


import com.xiaochengxu.test.entity.AppKey;
import com.xiaochengxu.test.repository.AppKeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author xiefengchang
 *
 */
@Service
@Transactional(readOnly=true, rollbackFor = Exception.class)
public class AppKeyService {
	@Autowired
    AppKeyRepository appKeyRepository;
	
	
	public AppKey getByAppId(String appId){
		return appKeyRepository.findByAppId(appId);
	}
}
