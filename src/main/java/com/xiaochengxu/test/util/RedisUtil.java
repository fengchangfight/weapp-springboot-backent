package com.xiaochengxu.test.util;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
/**
 * 
 * @author xiefengchang
 *
 */
@Service
public class RedisUtil {
	/**
	 * 
	 * @author xiefengchang
	 *
	 */
	@Autowired(required=true)
	private RedisTemplate<String,String> redisTemplate;

	public boolean add(final String key, final String value) {
		redisTemplate.execute(new RedisCallback<Object>() {  
			@Override  
			public Object doInRedis(RedisConnection connection)  
					throws DataAccessException {  
				connection.set(  
						redisTemplate.getStringSerializer().serialize(key),  
						redisTemplate.getStringSerializer().serialize(value));
				return true;  
			}  
		});
		return false;  
	}  
	
	public boolean add(final String key, final Long expires, final String value) {
		redisTemplate.execute(new RedisCallback<Object>() {  
			@Override  
			public Object doInRedis(RedisConnection connection)  
					throws DataAccessException {  
				connection.setEx(
						redisTemplate.getStringSerializer().serialize(key), 
						expires, 
						redisTemplate.getStringSerializer().serialize(value)
						);
				return true;  
			}  
		});
		return false;  
	}  
	
	public boolean add(final Map<String,String>map) {
		Assert.notEmpty(map,"This map is empty");  
		boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {  
			@Override
			public Boolean doInRedis(RedisConnection connection)  
					throws DataAccessException {  
				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();  
				for (Map.Entry<String, String> entry : map.entrySet()) {  
					byte[] key  = serializer.serialize(entry.getKey());  
					byte[] name = serializer.serialize(entry.getValue());  
					connection.setNX(key, name);  
				}  
				return true;  
			}  
		}, false, true);  
		return result; 
	}  

	public void delete(String key) {  
		redisTemplate.delete(key);
	}  

	public boolean update(final String key,final String value) {
		if (get(key) == null) {  
			throw new NullPointerException("数据行不存在, key = " + key);  
		}  
		boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {  
			@Override
			public Boolean doInRedis(RedisConnection connection)  
					throws DataAccessException {  
				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();  
				connection.set(serializer.serialize(key), serializer.serialize(value));  
				return true;  
			}  
		});  
		return result;   

	}  

	public Object get(final String keyId) {
		Object result = redisTemplate.execute(new RedisCallback<Object>() {  
			@Override
			public Object doInRedis(RedisConnection connection)  
					throws DataAccessException {  
				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();  
				byte[] key = serializer.serialize(keyId);  
				byte[] value = connection.get(key);  
				if (value == null) {  
					return null;  
				}  
				return serializer.deserialize(value);
			}  
		});  
		return result;  
	}
	
	/**
	 * @author Victor.zhang
	 * @param keyId
	 * @return user's open id in wechat App
	 * @throws Exception
	 */
	public String getOpenId(String keyId) throws NullPointerException{
		Object result = get(keyId);
		if(null == result) {
			throw new NullPointerException("No such session Id!");
		}
		String wxSessionStr = (String)result;
		String userId = wxSessionStr.split("#")[1];
		return userId;
	}
}
