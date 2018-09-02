
package com.winterchen.dao.redis;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;


@Service
public class Redis {
	private static Logger log = Logger.getLogger(Redis.class);
	@Resource(name = "redisTemplate")
	RedisTemplate<Object, Object> redisTemplate;
	@Resource(name = "redisTemplate")
	ValueOperations<String, String> valueOpsStr;
	@Resource(name = "redisTemplate")
	ValueOperations<String, Object> valueOps;
	@Resource(name = "redisTemplate")
	public HashOperations hashOperations;
	@Resource(name = "redisTemplate")
	private ListOperations listOps;
	@Resource(name = "redisTemplate")
	private SetOperations setOps;
	@Resource(name = "redisTemplate")
	private ZSetOperations zSetOps;

	/**
	 * 存数据
	 * 
	 * @param key
	 * @param value
	 */

	public void set(String key, Object value) {
		try {
			valueOps.set(key, value);
		} catch (Exception e) {
			log.error(e.getMessage());
		} finally {
		}
	}

	public void rightPushAll(String key, Collection values, long millseconds) {
		try {
			listOps.rightPushAll(key, values);
			redisTemplate.expire(key, millseconds, TimeUnit.MILLISECONDS);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			log.error("redis 异常");
		} finally {

		}
	}

	public void setList(String key, Collection values, long millseconds) {
		try {
			listOps.remove(key, -1, null);
			listOps.leftPushAll(key, values);
			redisTemplate.expire(key, millseconds, TimeUnit.MILLISECONDS);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			log.error("redis 异常");
		} finally {

		}
	}

	public List getAllList(String key) {
		try {
			return listOps.range(key, 0, -1);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			log.error("redis 异常");
			return null;
		} finally {

		}
	}

	/**
	 * 存数据存数据
	 * 
	 * @param key
	 * @param value
	 * @param liveTime
	 */

	public void set(String key, Object value, long liveTime) {
		try {
			valueOps.set(key, value, liveTime, TimeUnit.MILLISECONDS);
		} catch (Exception e) {
			log.error(e.getMessage());
			log.error("redis 异常");
		} finally {

		}

	}

	/**
	 * 获取数据
	 * 
	 * @param key
	 */

	public Object get(String key) {
		try {
			return valueOps.get(key);
		} catch (Exception e) {
			log.error(e.getMessage());
			log.error("redis 异常");
			return null;
		} finally {

		}

	}

	/**
	 * 删数据
	 */

	public long del(final String... keys) {
		return (long) redisTemplate.execute(new RedisCallback() {
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				long result = 0;
				for (int i = 0; i < keys.length; i++) {
					result = connection.del(keys[i].getBytes());
				}
				return result;
			}
		});
	}

	public Object hget(String key, String field) {
		try {
			return hashOperations.get(key, field);
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		} finally {
		}

	}

	public void hset(String key, String field, String value) {
		try {
			hashOperations.put(key, field, value);
		} catch (Exception e) {
			log.error(e.getMessage());
		} finally {
		}

	}

	public Long sadd(Object key, Object value) {

		try {
			return setOps.add(key, value);
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		} finally {
		}
	}

	public Long sadd(Object key, Object... value) {
		try {
			return setOps.add(key, value);
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		} finally {
		}
	}

	public Set members(Object key1) {
		try {
			return setOps.members(key1);
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		} finally {
		}

	}

	public Long smemberscount(Object key) {
		try {
			return setOps.size(key);
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		} finally {
		}

	}

	public Boolean expires(String key, Long miniseconds) {

		try {
			return redisTemplate.expire(key, miniseconds, TimeUnit.MILLISECONDS);
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		} finally {
		}

	}

	public Boolean expires(String key, Integer miniseconds) {
		return redisTemplate.expire(key, miniseconds, TimeUnit.MILLISECONDS);
	}

	public Set intersect(String key1, String key2) {
		return setOps.intersect(key1, key2);
	}

	public Set intersect(String key1, String... key2) {
		return setOps.intersect(key1, key2);
	}

	public Set intersect(String... keys) {
		if (keys.length == 0)
		{
			return null;}
		else if (keys.length == 1) {
			String key1 = keys[0];
			return setOps.members(key1);
		} else {
			String key1 = keys[0];
			Integer i = 0;
			Set otherKeys = new HashSet<>();
			for (String s : keys) {
				if (i > 0)
				{
					otherKeys.add(s);
				}
				i++;
			}
			return setOps.intersect(key1, otherKeys);
		}
	}

	public Long intersectAndStore(String key1, String key2, String key3) {
		try {
			return setOps.intersectAndStore(key1, key2, key3);
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		} finally {
		}

	}

	public Long intersectAndStore(String key1, String key2, String... key3) {

		try {
			return setOps.intersectAndStore(key1, key2, key3);
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		} finally {
		}
	}

	public List multiGet(String h, List keys) {

		try {
			return hashOperations.multiGet(h, keys);
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		} finally {
		}
	}

	public Set union(String key1, String key2) {

		try {
			return setOps.union(key1, key2);
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		} finally {
		}
	}

	public Set difference(String key1, String key2) {

		try {
			return setOps.difference(key1, key2);
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		} finally {
		}

	}

	Map hgetAll(String key) {
		return hashOperations.entries(key);
	}

	public Boolean exists(final String key) {
		try {
			return redisTemplate.hasKey(key);
		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		} finally {
		}
	}

}
