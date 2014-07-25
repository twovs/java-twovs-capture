/**
 * $id: com.twovv.captcha4j.AddNoise.java 2010-3-2 VogueVerge
 *
 * @version:
 *
 * Copyright (c) 2009 VOGUEVERGE.COM
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
public class AddNoise extends Configurable implements NoiseProducerI
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
//	第一条干扰线变量: Y坐标
	int heighty11;
	int heighty12;
	int heighty13;
	int heighty14;
	
//	第二条干扰线变量: Y坐标
	int heighty21;
	int heighty22;
	int heighty23;
	int heighty24;
	
//	第三条干扰线变量: Y坐标
	int heighty31;
	int heighty32;
	int heighty33;
	int heighty34;
	
//	第四条干扰线变量: Y坐标
	int heighty41;
	int heighty42;
	int heighty43;
	int heighty44;
		
	public void makeNoise1(BufferedImage image, float factorOne,
			float factorTwo, float factorThree, float factorFour)
	{
		Random rand = new Random();
		//获取颜色
		Color color = getConfig().getNoiseColor();

		// 图片大小
		int width = image.getWidth();
		// 设置方向和具体点的位置
		Point2D[] pts = null;
		
		//创造一个随机数生成器,y坐标中点为25,从18随机生成int
		
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
		int heightyy1 =rand.nextInt(15); //让第二个点和第三个点的y坐标保持一致,保证曲线的圆滑
		int heightyy2 =rand.nextInt(6);	//让第一个点和第四个点的y坐标保持一致,保证曲线的圆滑
		if(heightyy1<8){ //第二个点和第三个点最小值设置,当小于8时,曲线不够圆滑
			heightyy1=8;	
		}
		if(heightyy2<3){ //第二个点和第三个点最小值设置,当小于8时,曲线不够圆滑
			heightyy2=3;	
		}
		if(rand.nextInt(200)%2==0){
			heighty11 = heightyy2;
			heighty12 = heightyy1*-1;
			heighty13 = heightyy1;
			heighty14 = heightyy2*-1;
		}else{
			heighty11 = heightyy2*-1;
			heighty12 = heightyy1;
			heighty13 = heightyy1*-1;
			heighty14 = heightyy2;
		};
		CubicCurve2D cc = new CubicCurve2D.Float(
				width * factorOne,   32 + heighty11, 
				width * factorTwo,   32 + heighty12, 
				width * factorThree, 33 + heighty13, 
				width * factorFour,  32 + heighty14);

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
				graph.setStroke(new BasicStroke(0.9f + 0.9f*(rand.nextInt(2)),BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
				graph.draw(cc);
		}

		graph.dispose();
		this.setHeighty21(heighty14); //干扰线的第一个点为第一条线的第四个点的y坐标
		this.setHeighty31(heighty14);
		this.setHeighty41(heighty14);
		
		this.setHeighty22(heighty12);
		this.setHeighty23(heighty13);
		this.setHeighty24(heighty14); 
		
		this.setHeighty32(heighty12);
		this.setHeighty33(heighty13);
		this.setHeighty34(heighty14);
		
		this.setHeighty42(heighty12);
		this.setHeighty43(heighty13);
		this.setHeighty44(heighty14);
	}
	public void makeNoise2(BufferedImage image, float factorOne,
			float factorTwo, float factorThree, float factorFour) {
		Random rand = new Random();
		Color color = getConfig().getNoiseColor();
		int width = image.getWidth();
		Point2D[] pts = null;
		CubicCurve2D cc = new CubicCurve2D.Float(
				width * factorOne,   32 + heighty21, 
				width * factorTwo,   32 + heighty22, 
				width * factorThree, 32 + heighty23, 
				width * factorFour,  32 + heighty24);
		PathIterator pi = cc.getPathIterator(null, 2);
		Point2D tmp[] = new Point2D[200];
		int i = 0;
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
		RenderingHints hints = new RenderingHints(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON
				);
		hints.add(new RenderingHints(
				RenderingHints.KEY_ALPHA_INTERPOLATION,
				RenderingHints.VALUE_ALPHA_INTERPOLATION_SPEED));
		graph.setRenderingHints(hints);
		graph.setColor(color);

		for (i = 0; i < pts.length - 1; i++)
		{
			if (i < 3)
				graph.setStroke(new BasicStroke(0.9f + 0.9f*(rand.nextInt(2)),BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
				graph.draw(cc);
		}

		graph.dispose();
	}
	public void makeNoise3(BufferedImage image, float factorOne,
			float factorTwo, float factorThree, float factorFour) {
		Random rand = new Random();
		Color color = getConfig().getNoiseColor();
		int width = image.getWidth();
		Point2D[] pts = null;
		CubicCurve2D cc = new CubicCurve2D.Float(
				width * factorOne,   32 + heighty31, 
				width * factorTwo,   32 + heighty32, 
				width * factorThree, 32 + heighty33, 
				width * factorFour,  32 + heighty34);
		PathIterator pi = cc.getPathIterator(null, 2);
		Point2D tmp[] = new Point2D[200];
		int i = 0;
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
		RenderingHints hints = new RenderingHints(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON
				);
		hints.add(new RenderingHints(
				RenderingHints.KEY_ALPHA_INTERPOLATION,
				RenderingHints.VALUE_ALPHA_INTERPOLATION_SPEED));
		graph.setRenderingHints(hints);
		graph.setColor(color);

		for (i = 0; i < pts.length - 1; i++)
		{
			if (i < 3)
				graph.setStroke(new BasicStroke(0.9f + 0.9f*(rand.nextInt(2)),BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
				graph.draw(cc);
		}

		graph.dispose();
	}
	public void makeNoise4(BufferedImage image, float factorOne,
			float factorTwo, float factorThree, float factorFour) {
		Random rand = new Random();
		Color color = getConfig().getNoiseColor();
		int width = image.getWidth();
		Point2D[] pts = null;
		CubicCurve2D cc = new CubicCurve2D.Float(
				width * factorOne,   32 + heighty41, 
				width * factorTwo,   32 + heighty42, 
				width * factorThree, 32 + heighty43, 
				width * factorFour,  32 + heighty44);
		PathIterator pi = cc.getPathIterator(null, 2);
		Point2D tmp[] = new Point2D[200];
		int i = 0;
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
		RenderingHints hints = new RenderingHints(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON
				);
		hints.add(new RenderingHints(
				RenderingHints.KEY_ALPHA_INTERPOLATION,
				RenderingHints.VALUE_ALPHA_INTERPOLATION_SPEED));
		graph.setRenderingHints(hints);
		graph.setColor(color);

		for (i = 0; i < pts.length - 1; i++)
		{
			if (i < 3)
				graph.setStroke(new BasicStroke(0.9f + 0.9f*(rand.nextInt(2)),BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
				graph.draw(cc);
		}

		graph.dispose();
	}
	public int getHeighty21() {
		return heighty21;
	}
	public void setHeighty21(int heighty21) {
		this.heighty21 = heighty21;
	}
	public int getHeighty22() {
		return heighty22;
	}
	public void setHeighty22(int heighty22) {
		this.heighty22 = heighty22;
	}
	public int getHeighty23() {
		return heighty23;
	}
	public void setHeighty23(int heighty23) {
		this.heighty23 = heighty23;
	}
	public int getHeighty24() {
		return heighty24;
	}
	public void setHeighty24(int heighty24) {
		this.heighty24 = heighty24;
	}
	public int getHeighty31() {
		return heighty31;
	}
	public void setHeighty31(int heighty31) {
		this.heighty31 = heighty31;
	}
	public int getHeighty32() {
		return heighty32;
	}
	public void setHeighty32(int heighty32) {
		this.heighty32 = heighty32;
	}
	public int getHeighty33() {
		return heighty33;
	}
	public void setHeighty33(int heighty33) {
		this.heighty33 = heighty33;
	}
	public int getHeighty34() {
		return heighty34;
	}
	public void setHeighty34(int heighty34) {
		this.heighty34 = heighty34;
	}
	public int getHeighty41() {
		return heighty41;
	}
	public void setHeighty41(int heighty41) {
		this.heighty41 = heighty41;
	}
	public int getHeighty42() {
		return heighty42;
	}
	public void setHeighty42(int heighty42) {
		this.heighty42 = heighty42;
	}
	public int getHeighty43() {
		return heighty43;
	}
	public void setHeighty43(int heighty43) {
		this.heighty43 = heighty43;
	}
	public int getHeighty44() {
		return heighty44;
	}
	public void setHeighty44(int heighty44) {
		this.heighty44 = heighty44;
	}
}
