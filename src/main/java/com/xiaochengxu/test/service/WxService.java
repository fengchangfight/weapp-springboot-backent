package com.xiaochengxu.test.service;

import java.util.Map;

import com.xiaochengxu.test.config.WxAuth;
import com.xiaochengxu.test.util.HttpRequest;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSON;
import com.xiaochengxu.test.util.RedisUtil;
/**
 * 
 * @author xiefengchang
 *
 */
@Service
public class WxService {
	@Autowired
	private WxAuth wxAuth;
	@Autowired
	private RedisUtil redisUtil;
	
	@SuppressWarnings("unchecked")
	public Map<String,Object>getWxSession(String wxCode){
		StringBuffer sb = new StringBuffer();
		sb.append("appid=").append(wxAuth.getAppId());
		sb.append("&secret=").append(wxAuth.getSecret());
		sb.append("&js_code=").append(wxCode);
		sb.append("&grant_type=").append(wxAuth.getGrantType());
		String res = HttpRequest.sendGet(wxAuth.getSessionHost(), sb.toString());
		if(res == null || "".equals(res)){
			return null;
		}
		return JSON.parseObject(res, Map.class);
	}
	
	public String create3rdSession(String wxOpenId, String wxSessionKey, Long expires){
		String thirdSessionKey = RandomStringUtils.randomAlphanumeric(64);
		StringBuffer sb = new StringBuffer();
		sb.append(wxSessionKey).append("#").append(wxOpenId);
		redisUtil.add(thirdSessionKey, expires, sb.toString());
		return thirdSessionKey;
	}
}
