/**
 * $id: com.twovv.captcha4j.rest.CaptHelper.java by fjt 2012-1-18
 *
 * @version:
 *
 * Copyright (c) 2012 twovv.com 
 */
package com.twovv.captcha4j.rest;

import javax.servlet.http.HttpServletRequest;

import com.twovv.captcha4j.Constants;

/** @Class: CaptHelper @TODO: 前端验证码判断*/
public class CaptHelper {

	/** 
	 * @method: checkCapt() -by fjt
	 * @TODO:  检查验证码是否正确
	 * @return boolean
	 */
	public static boolean checkCapt(HttpServletRequest request,String iptcapt){
		//自动生成的字符串
		String expectedCapt4j = (String) request.getSession().getAttribute(Constants.JCAP_SESSION_KEY);
		if(iptcapt!=null && iptcapt.equalsIgnoreCase(expectedCapt4j)){
			return true; //输入了正确的验证码
		}else{
			return false; // 验证码错误
		}
	}
}
