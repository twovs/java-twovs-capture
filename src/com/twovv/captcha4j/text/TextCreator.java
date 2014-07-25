/**
 * $id: TextCreator.java 2009-5-16 下午11:52:31 笨笨
 *
 * @version:
 *
 * Copyright (c) 2009 VOGUEVERGE.COM
 *
 */
package com.twovv.captcha4j.text;

import java.util.Random;

import com.twovv.captcha4j.utils.Configurable;



/**
 * {@link TextCreator} 创建任意指定长度的字符串
 */
public class TextCreator extends Configurable implements TextProducerI
{
	/**
	 * @return 随机文本
	 */
	public String getText()
	{	
		// 4个随机字符
		int length = getConfig().getTextProducerCharLength();
		char[] chars = getConfig().getTextProducerCharString();
		int randomContext = chars.length - 1;
		Random rand = new Random();
		StringBuffer text = new StringBuffer();
		for (int i = 0; i < length; i++)
		{
			text.append(chars[rand.nextInt(randomContext) + 1]);
		}

		return text.toString();
	}
}
