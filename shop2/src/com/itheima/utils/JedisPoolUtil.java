package com.itheima.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolUtil {
	private static JedisPool pool = null;
	
	static{
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxIdle(30);//最大闲置个数
		poolConfig.setMinIdle(10);//最小闲置个数
		poolConfig.setMaxIdle(50);//最大连接数
		pool = new JedisPool(poolConfig, "localhost",6379);
	}
	
	public static Jedis getJedis(){
		return pool.getResource();	
	}
}
