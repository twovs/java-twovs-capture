/**
 * $id: NoiseProducerI.java 2009-5-16 下午11:52:31 笨笨
 *
 * @version:
 *
 * Copyright (c) 2009 VOGUEVERGE.COM
 *
 */
package com.twovv.captcha4j;

import java.awt.image.BufferedImage;

/**
 * {@link NoiseProducerI} 添加干扰图形.
 */
public interface NoiseProducerI
{
	/**
	 * 采用4个因素产生干扰图形
	 * @param image 需要添加干扰的图像
	 *            浮点型
	 * @param factorOne
	 * @param factorTwo
	 * @param factorThree
	 * @param factorFour
	 */
	public void makeNoise1(BufferedImage image, float factorOne,
			float factorTwo, float factorThree, float factorFour);
	public void makeNoise2(BufferedImage image, float factorOne,
			float factorTwo, float factorThree, float factorFour);
	public void makeNoise3(BufferedImage image, float factorOne,
			float factorTwo, float factorThree, float factorFour);
	public void makeNoise4(BufferedImage image, float factorOne,
			float factorTwo, float factorThree, float factorFour);
}