/**
 * $id: WordRendererI.java 2009-5-16 下午11:52:31 笨笨
 *
 * @version:
 *
 * Copyright (c) 2009 VOGUEVERGE.COM
 *
 */
package com.twovv.captcha4j.text;

import java.awt.image.BufferedImage;

/**
 * {@link WordRendererI} 描绘文字.
 */
public interface WordRendererI
{
	public BufferedImage renderWord(String word, int width, int height);
}
