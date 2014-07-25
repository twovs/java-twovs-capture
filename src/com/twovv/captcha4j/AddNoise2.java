/**
 * $id: AddNoise.java 2009-5-16 下午11:52:31 笨笨
 *
 * @version:
 *
 * Copyright (c) 2009 VOGUEVERGE.COM
 * 
 * 
 * 这个类存在一个无法保证条件判断使用同一个随机数,会出现2个波浪线同一个方向的问题
 *
 */
package com.twovv.captcha4j;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.twovv.captcha4j.utils.Configurable;




/**
 * 参考
 * http://www.iplab.cs.tsukuba.ac.jp/liuxj/jdk1.2/zh/docs/guide/2d/spec/j2d-awt.fm2.html
 * 
 * http://www.glyphic.com/transform/applet/1intro.html
 * 
 * {@link NoiseProducerI},为图片增加干扰线
 */
public class AddNoise2 extends Configurable implements NoiseProducerI
{
	/**
	 * 添加干扰因素.
	 * 当所有因素的值 > 1.0f,干扰因素将不可见
	 * 
	 * @param image
	 *            the image to add the noise to
	 * @param factorOne
	 * @param factorTwo
	 * @param factorThree
	 * @param factorFour
	 */

	public void makeNoise1(BufferedImage image, float factorOne,
			float factorTwo, float factorThree, float factorFour)
	{	
		//获取颜色
		Color color = getConfig().getNoiseColor();

		// 图片大小
		int width = image.getWidth();
		// 设置方向和具体点的位置
		Point2D[] pts = null;
		
		//创造一个随机数生成器,y坐标中点为25,从18随机生成int
		Random rand = new Random();

		// 根据.来设置弯曲度,y坐标点由rand随机数得到,此数字不可控制,x坐标在实例化时传入 参数为
//		x1 - 得到的 CubicCurve2D 起始点的 X 坐标
//		y1 - 得到的 CubicCurve2D 起始点的 Y 坐标
//		ctrlx1 - 得到的 CubicCurve2D 第一个控制点的 X 坐标
//		ctrly1 - 得到的 CubicCurve2D 第一个控制点的 Y 坐标
//		ctrlx2 - 得到的 CubicCurve2D 第二个控制点的 X 坐标
//		ctrly2 - 得到的 CubicCurve2D 第二个控制点的 Y 坐标
//		x2 - 得到的 CubicCurve2D 结束点的 X 坐标
//		y2 - 得到的 CubicCurve2D 结束点的 Y 坐标
		//构造y坐标点,浮动范围: 11 - 41 之间 
		// 创建绘制对象y坐标 浮动范围 正负15
		int heighty0;
		int heighty1;
		int heighty2;
		int heighty3;
		int heightyy1 = rand.nextInt(4);
		int heightyy2 = rand.nextInt(15);
		
		
		if(heightyy2<8){
			heightyy2=8;
		}
		if(rand.nextInt(200)%2==0){
			heighty0 = heightyy1;
			heighty1 = heightyy2*-1;
			heighty2 = heightyy2;
			heighty3 = heightyy1*-1;
		}else{
			heighty0 = heightyy1*-1;
			heighty1 = heightyy2;
			heighty2 = heightyy2*-1;
			heighty3 = heightyy1;
		};
		CubicCurve2D cc = new CubicCurve2D.Float(
				width * factorOne,   26 + heighty0, 
				width * factorTwo,   26 + heighty1, 
				width * factorThree, 26 + heighty2, 
				width * factorFour,  26 + heighty3);

		// 创建一个迭代器来定义曲线
		PathIterator pi = cc.getPathIterator(null, 2);
		Point2D tmp[] = new Point2D[200];
		int i = 0;
		// while pi is iterating the curve, adds points to tmp array
		while (!pi.isDone())
		{
			float[] coords = new float[6];
			switch (pi.currentSegment(coords))
			{
				case PathIterator.SEG_MOVETO:
				case PathIterator.SEG_LINETO:
					tmp[i] = new Point2D.Float(coords[0], coords[1]);
			}
			i++;
			pi.next();
		}

		pts = new Point2D[i];
		System.arraycopy(tmp, 0, pts, 0, i);

		Graphics2D graph = (Graphics2D) image.getGraphics();
		//抗锯齿
		RenderingHints hints = new RenderingHints(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON
				);
		//速度优先
		hints.add(new RenderingHints(
				RenderingHints.KEY_ALPHA_INTERPOLATION,
				RenderingHints.VALUE_ALPHA_INTERPOLATION_SPEED));
		graph.setRenderingHints(hints);
		graph.setColor(color);

		// 最大的三点来改变曲线的方向
		for (i = 0; i < pts.length - 1; i++)
		{
			if (i < 3)
				//画笔样式 0.9f * (4 - i)
				graph.setStroke(new BasicStroke(0.9f + 0.9f*(rand.nextInt(3)),BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
				graph.draw(cc);
		}

		graph.dispose();
	}

	public void makeNoise2(BufferedImage image, float factorOne,
			float factorTwo, float factorThree, float factorFour) {
	}

	public void makeNoise3(BufferedImage image, float factorOne,
			float factorTwo, float factorThree, float factorFour) {
	}

	public void makeNoise4(BufferedImage image, float factorOne,
			float factorTwo, float factorThree, float factorFour) {
	}
}
