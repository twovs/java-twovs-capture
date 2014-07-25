/**
 * $id: BackgroundProducerI.java 2009-5-16 下午11:52:31 笨笨
 *
 * @version:
 *
 * Copyright (c) 2009 VOGUEVERGE.COM
 *
 */
package com.twovv.captcha4j;

import java.awt.image.BufferedImage;

/**
 * {@link BackgroundProducerI} 生成背景图片.
 */
public interface BackgroundProducerI
{
	public abstract BufferedImage addBackground(BufferedImage image);
}
