/**
 * $id: WordRenderer.java 2009-5-16 下午11:52:31 笨笨
 *
 * @version:
 *
 * Copyright (c) 2009 VOGUEVERGE.COM
 *
 */
package com.twovv.captcha4j.text;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.twovv.captcha4j.utils.Configurable;


/**
 * {@link WordRenderer},描绘文字在图片上的实现方法,
 * 		当第一个字符朝正旋转的话,第2个字符应反向旋转回来,以得到正确的显示区域
 */
public class WordRenderer extends Configurable implements WordRendererI
{
	/**
	 * 
	 * @param word 随机文本
	 *         
	 * @param width 图像的宽度
	 *            
	 * @param height 图像的高度
	 *         
	 * @return 创建成功的图片缓存.
	 */
	public BufferedImage renderWord(String word, int width, int height)
	{	
		// 40
		int fontSize = getConfig().getTextProducerFontSize();
		// 字体
		Font[] fonts = getConfig().getTextProducerFonts(fontSize);
		// 文本颜色
		Color color = getConfig().getTextProducerFontColor();
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2D = image.createGraphics();
		g2D.setColor(color);

		RenderingHints hints = new RenderingHints(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		hints.add(new RenderingHints(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY));
		g2D.setRenderingHints(hints);

		FontRenderContext frc = g2D.getFontRenderContext();
		Random random = new Random();
//		文字所在图片的宽度和高度计算
		int startPosX = width / (2 + word.length());
		int startPosY = height/2 - random.nextInt(5);
		
		char[] wordChars = word.toCharArray();
		
//		定义一个正负角度
		int tally = 0;
		if(random.nextInt(200)%2==0){
			tally = random.nextInt(20);
		}else{
			tally = random.nextInt(20)*(-1);
		}
//		判断字符描绘起始位置
		if(tally<0 && tally >= -3){
			startPosY =38;
		}else if(tally > -9 && tally < -3 ){
			startPosY =45;
		}else if(tally <= -9){
			startPosY =50;
		}else if (tally >=0 && tally <= 7) {
			tally = 7;
			startPosY = 35;
		}else if(tally > 7 && tally< 18){
			startPosY = 29;
		}else if(tally >= 18){
			startPosY = 20;
		}
		
		String tmpstr0 = String.valueOf(wordChars[0]);
		String tmpstr1 = String.valueOf(wordChars[1]);
		String tmpstr2 = String.valueOf(wordChars[2]);
		String tmpstr3 = String.valueOf(wordChars[3]);
		Font chosenFont = fonts[random.nextInt(fonts.length)];
		g2D.setFont(chosenFont);
		//生成第一个字符
		GlyphVector gv = chosenFont.createGlyphVector(frc, tmpstr0);
		double charWidth0 = gv.getVisualBounds().getWidth();
		g2D.translate(0, 0);
		g2D.rotate(Math.toRadians(tally));
		g2D.drawString(tmpstr0 , startPosX, startPosY);
		//生成第二个字符
		gv = chosenFont.createGlyphVector(frc, tmpstr1);
		double charWidth1 = gv.getVisualBounds().getWidth()+(int)charWidth0+2;
		g2D.translate(0, 0);
		g2D.rotate(-(Math.toRadians(tally)),startPosX +(int)charWidth0+2, startPosY);
		g2D.drawString(tmpstr1 , startPosX +(int)charWidth0+2, startPosY);
		//生成第三个字符
		gv = chosenFont.createGlyphVector(frc, tmpstr2);
		double charWidth2 = gv.getVisualBounds().getWidth()+(int)charWidth1+2;
		g2D.translate(0, 0);
		g2D.rotate(Math.toRadians(tally),startPosX +(int)charWidth1+2, startPosY);
		g2D.drawString(tmpstr2 , startPosX +(int)charWidth1+2, startPosY);
		//生成第四个字符
		g2D.translate(0, 0);
		g2D.rotate(-(Math.toRadians(tally)),startPosX +(int)charWidth2+2, startPosY);
		g2D.drawString(tmpstr3 , startPosX +(int)charWidth2+2, startPosY);
		return image;
	}
}
