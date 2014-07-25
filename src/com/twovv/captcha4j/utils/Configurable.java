/**
 * $id: Configurable.java 2009-5-16 下午11:52:31 笨笨
 *
 * @version:
 *
 * Copyright (c) 2009 VOGUEVERGE.COM
 *
 */
package com.twovv.captcha4j.utils;


/**
 * 加载配置信息
 */
public abstract class Configurable
{
	private Config config = null;

	public Config getConfig()
	{
		return this.config;
	}

	public void setConfig(Config config)
	{
		this.config = config;
	}
}
