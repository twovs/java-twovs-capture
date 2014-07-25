/**
 * $id: ProducerI.java 2009-5-16 下午11:52:31 笨笨
 *
 * @version:
 *
 * Copyright (c) 2009 VOGUEVERGE.COM
 *
 */
package com.twovv.captcha4j;

import java.awt.image.BufferedImage;

/**
 * 创建验证码图片.
 */
public interface ProducerI
{
	/**
	 * 创建扭曲文本图片.
	 * 
	 * @param text 需要扭曲的字符
	 *            
	 * @return 图形验证码图片
	 */
	public BufferedImage createImage(String text);

	/**
	 * @return 要绘制的字符
	 */
	public abstract String createText();
}