/**
 * $id: AddNoiseImp.java 2009-5-16 下午11:52:31 笨笨
 *
 * @version:
 *
 * Copyright (c) 2009 VOGUEVERGE.COM
 *
 */
package com.twovv.captcha4j;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.twovv.captcha4j.utils.Configurable;



/**
 * {@link AddNoiseImp} 添加干扰线
 */
public class AddNoiseImp extends Configurable implements GimpyEngineI
{
	public BufferedImage getDistortedImage(BufferedImage baseImage)
	{
		NoiseProducerI noiseProducer = getConfig().getNoiseImpl();
		BufferedImage distortedImage = new BufferedImage(baseImage.getWidth(),
				baseImage.getHeight(), BufferedImage.TYPE_INT_ARGB);

		Graphics2D graph = (Graphics2D) distortedImage.getGraphics();
		graph.drawImage(baseImage, 0, 0, null, null);
		graph.dispose();
//		随机缺少一条干扰线,以便更加的动态
		Random rand = new Random();
		int iRand= rand.nextInt(2000);
		if(iRand%2==0){
			noiseProducer.makeNoise1(distortedImage, .0f, .08f, .15f, .24f);
			noiseProducer.makeNoise2(distortedImage, .25f, .33f, .42f, .49f);
			noiseProducer.makeNoise3(distortedImage, .5f, .59f, .65f, .75f);
			noiseProducer.makeNoise4(distortedImage, .76f, .85f, .90f, 1f);
		}else if(iRand%3==0){
			noiseProducer.makeNoise1(distortedImage, .0f, .15f, .25f, .33f);
//			noiseProducer.makeNoise2(distortedImage, .25f, .33f, .42f, .49f);
			noiseProducer.makeNoise3(distortedImage, .5f, .59f, .65f, .75f);
			noiseProducer.makeNoise4(distortedImage, .76f, .85f, .90f, 1f);
		}else{
			noiseProducer.makeNoise1(distortedImage, .0f, .08f, .15f, .24f);
			noiseProducer.makeNoise2(distortedImage, .25f, .42f, .5f, .59f);
//			noiseProducer.makeNoise3(distortedImage, .5f, .59f, .65f, .75f);
			noiseProducer.makeNoise4(distortedImage, .76f, .85f, .90f, 1f);
		}
		
		return distortedImage;
	}
}
