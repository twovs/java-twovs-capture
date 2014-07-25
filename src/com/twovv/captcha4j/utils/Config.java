/**
 * $id: Config.java 2009-5-16 下午11:52:31 笨笨
 *
 * @version:
 *
 * Copyright (c) 2009 VOGUEVERGE.COM
 *
 */
package com.twovv.captcha4j.utils;

import java.awt.Color;
import java.awt.Font;
import java.util.Properties;

import com.twovv.captcha4j.AddNoise;
import com.twovv.captcha4j.AddNoiseImp;
import com.twovv.captcha4j.Background;
import com.twovv.captcha4j.BackgroundProducerI;
import com.twovv.captcha4j.Constants;
import com.twovv.captcha4j.CreatCap4j;
import com.twovv.captcha4j.GimpyEngineI;
import com.twovv.captcha4j.NoiseProducerI;
import com.twovv.captcha4j.ProducerI;
import com.twovv.captcha4j.text.TextProducerI;
import com.twovv.captcha4j.text.WordRendererI;
import com.twovv.captcha4j.text.TextCreator;
import com.twovv.captcha4j.text.WordRenderer;

/**
 * {@link Config} 从配置文件中取得配置参数,如果没有配置,则使用默认值
 */
public class Config
{
	private Properties properties;
	private ConfigHelper helper;
	private Color noise;
	public Config(Properties properties)
	{
		this.properties = properties;
		helper = new ConfigHelper();
	}
	/** 创建验证码图片 */
	public ProducerI getProducerImpl()
	{
		ProducerI producer = (ProducerI) helper.getClassInstance( new CreatCap4j(), this);
		return producer;
	}

	/** 创建文字 */
	public TextProducerI getTextProducerImpl()
	{
		TextProducerI textProducer = (TextProducerI) helper.getClassInstance(
				new TextCreator(), this);
		return textProducer;
	}

	/** 配置可用字符串,随机字符串将从这里取得 */
	public char[] getTextProducerCharString()
	{
		return helper.getChars("NaMbPcZdYeXfWgVh2Uj3TkS4mRn5QpL6qKr7JsHt8GuF9vEwDxCyBzA".toCharArray());
	}

	/**随机个数文字 */
	public int getTextProducerCharLength()
	{
		return 4;
	}

	/** 随机字体样式 */
	public Font[] getTextProducerFonts(int fontSize)
	{
		return helper.getFonts(fontSize);
	}

	/** 字体大小 */
	public int getTextProducerFontSize()
	{
		return 35;
	}
	/** 文字颜色 */
	public Color getTextProducerFontColor()
	{
		Color color = helper.getColor();
		this.setNoise(color);
		return color;
	}
	/**
	 * 干扰因素*/
	public NoiseProducerI getNoiseImpl()
	{
		NoiseProducerI noiseProducer = (NoiseProducerI) helper.getClassInstance(
				new AddNoise(), this);
		return noiseProducer;
	}

	/** 获得干扰因素的颜色 */
	public Color getNoiseColor()
	{
		return noise;
	}

	/** 添加干扰线 */
	public GimpyEngineI getObscurificatorImpl()
	{
		GimpyEngineI gimpyEngine = (GimpyEngineI) helper.getClassInstance( new AddNoiseImp(), this);
		return gimpyEngine;
	}
	public WordRendererI getWordRendererImpl()
	{
		WordRendererI wordRenderer = (WordRendererI) helper.getClassInstance(
				new WordRenderer(), this);
		return wordRenderer;
	}
	public BackgroundProducerI getBackgroundImpl()
	{
		BackgroundProducerI backgroundProducer = (BackgroundProducerI) helper.getClassInstance(
				new Background(), this);
		return backgroundProducer;
	}

	/**  图片背景起始颜色 如果是渐变色的话 */
	public Color getBackgroundColorFrom()
	{
		return new Color(240,243,248);
	}

	/** 获得背景图像渐变结束颜色 */
	public Color getBackgroundColorTo()
	{
		return new Color(240,243,248);
	}

	/** 图片宽度  如果要扩展配置,可以起用此方法,参考 CreatCap4j */
	public int getWidth()
	{
		return 130;
	}

	/** 图片高度 */
	public int getHeight()
	{		
		return 53;
	}

	/**
	 * Allows one to override the key name which is stored in the users
	 * HttpSession. Defaults to Constants.KAPTCHA_SESSION_KEY.
	 */
	public String getSessionKey()
	{
		return properties.getProperty(Constants.JCAP_SESSION_CONFIG_KEY, Constants.JCAP_SESSION_KEY);
	}
	public Properties getProperties()
	{
		return properties;
	}
	/*
	 * 将干扰线颜色设置为字体颜色
	 * */
	public void setNoise(Color color) {
		this.noise = color;
	}
}
