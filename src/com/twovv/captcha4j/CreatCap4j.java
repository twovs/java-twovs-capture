/**
 * $id: CreatCap4j.java 2009-5-16 下午11:52:31 笨笨
 *
 * @version:
 *
 * Copyright (c) 2009 VOGUEVERGE.COM
 *
 */
package com.twovv.captcha4j;
import java.awt.image.BufferedImage;

import com.twovv.captcha4j.text.WordRendererI;
import com.twovv.captcha4j.utils.Configurable;


/**
 * Default {@link ProducerI} implementation which draws a captcha image using
 * {@link WordRenderer}, {@link GimpyEngineI}, {@link BackgroundProducerI}.
 * Text creation uses {@link TextProducer}.
 */
public class CreatCap4j extends Configurable implements ProducerI
{
	private int width = 130;

	private int height = 53;

	/**
	 * 创建一个图像.
	 * 
	 * @param text
	 *            the distorted characters
	 * @return image with the text
	 */
	public BufferedImage createImage(String text)
	{
		WordRendererI wordRenderer = getConfig().getWordRendererImpl();
		GimpyEngineI gimpyEngine = getConfig().getObscurificatorImpl();
		BackgroundProducerI backgroundProducer = getConfig().getBackgroundImpl();
		BufferedImage bi = wordRenderer.renderWord(text, width, height);
		bi = gimpyEngine.getDistortedImage(bi);
		bi = backgroundProducer.addBackground(bi);
		return bi;
	}
	/**
	 * @return the text to be drawn
	 */
	public String createText()
	{
		return getConfig().getTextProducerImpl().getText();
	}
}
