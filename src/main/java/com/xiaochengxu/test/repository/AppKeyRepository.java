package com.xiaochengxu.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xiaochengxu.test.entity.AppKey;

/**
 * 
 * @author xiefengchang
 *
 */
public interface AppKeyRepository extends JpaRepository<AppKey, Long> {
	/**根据appId查找app相关信息
	 * @param appId 参数appId为微信小程序id
	 * @return 返回值为app相关信息
	 * */
	AppKey findByAppId(String appId);
}
