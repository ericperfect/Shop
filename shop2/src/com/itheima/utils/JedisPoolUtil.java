package com.itheima.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolUtil {
	private static JedisPool pool = null;
	
	static{
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxIdle(30);//������ø���
		poolConfig.setMinIdle(10);//��С���ø���
		poolConfig.setMaxIdle(50);//���������
		pool = new JedisPool(poolConfig, "localhost",6379);
	}
	
	public static Jedis getJedis(){
		return pool.getResource();	
	}
}
