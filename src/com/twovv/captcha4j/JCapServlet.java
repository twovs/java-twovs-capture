/**
 * $id: JCapServlet.java 2009-5-16 下午11:52:31 笨笨
 *
 * @version:
 *
 * Copyright (c) 2009 VOGUEVERGE.COM
 *
 */
package com.twovv.captcha4j;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.twovv.captcha4j.utils.Config;



/**
 * VogueVerge captcha4j servlet import
 * 
 * doGet 交由 doPost处理
 *
 */
public class JCapServlet extends HttpServlet implements Servlet
{
	// Fields: serialVersionUID
	private static final long serialVersionUID = 7576946468198995063L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Set to expire far in the past.
		resp.setDateHeader("Expires", 0);
		// Set standard HTTP/1.1 no-cache headers.
		resp.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
		// Set IE extended HTTP/1.1 no-cache headers (use addHeader).
		resp.addHeader("Cache-Control", "post-check=0, pre-check=0");
		// Set standard HTTP/1.0 no-cache header.
		resp.setHeader("Pragma", "no-cache");
		  
		// contentType jpeg
		resp.setContentType("image/jpeg");

		// 创建图片上的文字
		String capText = jcapProducer.createText();
		
		// 将文字内容存储在Session中
		req.getSession().setAttribute(this.sessionKeyValue, capText);

		// 创建图片+文字
		BufferedImage bi = jcapProducer.createImage(capText);

		ServletOutputStream out = resp.getOutputStream();
		
		// 输出图片数据
		ImageIO.write(bi, "jpg", out);
		try
		{
			out.flush();
		}
		finally
		{
			out.close();
		}
	}

	//配置文件属性
	private Properties props = new Properties();

	private ProducerI jcapProducer = null;
	
	private String sessionKeyValue = null;

	/*
	 * @see javax.servlet.Servlet#init(javax.servlet.ServletConfig)
	 */
	public void init(ServletConfig conf) throws ServletException
	{
		super.init(conf);

		// 关闭磁盘缓存.
		ImageIO.setUseCache(false);

		Enumeration<?> initParams = conf.getInitParameterNames();
		while (initParams.hasMoreElements())
		{
			String key = (String) initParams.nextElement();
			String value = conf.getInitParameter(key);
			props.put(key, value);
		}

		Config config = new Config(props);
		this.jcapProducer = (ProducerI) config.getProducerImpl();
		this.sessionKeyValue = config.getSessionKey();
	}
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		this.doPost(req, resp);
	}
	
	
}
