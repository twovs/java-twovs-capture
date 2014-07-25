/**
 * $id: Background.java 2009-5-16 下午11:52:31 笨笨
 *
 * @version:
 *
 * Copyright (c) 2009 VOGUEVERGE.COM
 *
 */
package com.twovv.captcha4j;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import com.twovv.captcha4j.utils.Configurable;




/**
 * {@link BackgroundProducerI},默认背景实现类 Color From(top left) and Color To (bottom right).
 */
public class Background extends Configurable implements BackgroundProducerI
{
	/**
	 * @param baseImage 基础图片
	 * @return an image with a gradient background added to the base image.
	 */
	public BufferedImage addBackground(BufferedImage baseImage)
	{
		Color colorFrom = getConfig().getBackgroundColorFrom();
		Color colorTo = getConfig().getBackgroundColorTo();

		int width = baseImage.getWidth();
		int height = baseImage.getHeight();

		// create an opaque image
		BufferedImage imageWithBackground = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);

		Graphics2D graph = (Graphics2D) imageWithBackground.getGraphics();
		RenderingHints hints = new RenderingHints(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_OFF);

		hints.add(new RenderingHints(RenderingHints.KEY_COLOR_RENDERING,
				RenderingHints.VALUE_COLOR_RENDER_QUALITY));
		hints.add(new RenderingHints(RenderingHints.KEY_ALPHA_INTERPOLATION,
				RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY));

		hints.add(new RenderingHints(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY));

		graph.setRenderingHints(hints);

		GradientPaint paint = new GradientPaint(0, 0, colorFrom, width, height,
				colorTo);
		graph.setPaint(paint);
		graph.fill(new Rectangle2D.Double(0, 0, width, height));

		// draw the transparent image over the background
		graph.drawImage(baseImage, 0, 0, null);

		return imageWithBackground;
	}
}
