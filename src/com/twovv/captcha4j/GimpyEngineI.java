/**
 * $id: GimpyEngineI.java 2009-5-16 下午11:52:31 笨笨
 *
 * @version:
 *
 * Copyright (c) 2009 VOGUEVERGE.COM
 *
 */
package com.twovv.captcha4j;

import java.awt.image.BufferedImage;

/**
 * {@link GimpyEngineI} 图形随机畸变.
 */
public interface GimpyEngineI
{
	/**
	 * @param baseImage 要畸变的图片
	 *            
	 * @return 畸变的图片
	 */
	public BufferedImage getDistortedImage(BufferedImage baseImage);
}