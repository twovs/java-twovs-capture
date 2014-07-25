/**
 * $id: ConfigHelper.java 2009-5-16 下午11:52:31 笨笨
 *
 * @version:
 *
 * Copyright (c) 2009 VOGUEVERGE.COM
 *
 */
package com.twovv.captcha4j.utils;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;


/**
 * This class provides helper methods in parsing configuration values.
 */
public class ConfigHelper
{	
	Random random = new Random();
	
	/** 获得类实例 */
	public Object getClassInstance(Object defaultInstance, Config config)
	{
		Object instance = defaultInstance;
		setConfigurable(instance, config);

		return instance;
	}

	/** 设置字体样式 */
	public Font[] getFonts(int fontSize){
		String [] fontNames = new String[]{"Georgia","Century Gothic","Times","Arial","serif","Times New Roman","Trebuchet MS","Verdana","Tahoma"};
		
		Font[] fonts = new Font[fontNames.length];
			for (int i = 0; i < fontNames.length; i++)
			{
				fonts[i] = new Font(fontNames[i], Font.PLAIN, fontSize);
			}
		return fonts;
	}
	public char[] getChars(char[] defaultChars)
	{
		char[] chars = defaultChars ;
		return chars;
	}
	private void setConfigurable(Object object, Config config)
	{
		if (object instanceof Configurable)
		{
			((Configurable) object).setConfig(config);
		}
	}
	//获取颜色
	public Color getColor() {
		Color color = new Color(random.nextInt(200),random.nextInt(200),random.nextInt(200)) ;
		return color;
	}
}
