java-twovs-capture
==================

java 模仿QQ图片验证码-源代码

<img src="https://id.twovs.com/getcapt.gif"/>

这是一个普通的java项目，依赖servlet实现，将项目生成的class文件原样打包，作为jar引入到项目中即可。

配置：

一、Servlet配置：

1. 在web.xml文件中配置
	
	<servlet>
	        <servlet-name>Captcha4j</servlet-name>
	        <servlet-class>com.twovv.captcha4j.JCapServlet</servlet-class>
	</servlet>
	<servlet-mapping>
	        <servlet-name>Captcha4j</servlet-name>
	        <url-pattern>/getCaptImg.jpg</url-pattern>
	</servlet-mapping>
	
2. 在需要显示图片的页面中,通过如下代码获得验证图片

		<img src="/getCaptImg.jpg" /> <br/>
		<input type="text" name="captcha4j" value="" />
二、示例：
3. Action处理
		String expectedCapt4j = (String)javax.servlet.http.HttpServletRequest.getSession()
		    .getAttribute(com.twovv.captcha4j.Constants.JCAP_SESSION_KEY);
		String inputCapt4j = request.getParameter("captcha4j");
		
		if (inputCapt4j == null || !inputCapt4j.equalsIgnoreCase(expectedCapt4j))
		{
			return INPUT;
			
		}
			return SUCCESS;
		}
	
	}	
	
4. 将验证结果反馈会前台			

二、前台界面调用：

	<input type="text" name="capt" id="capt" value="请输入下图中的验证码" autocomplete="off" class="ipt">
	<span class="bspan lip">
		<img id="a" src="/getcapt.gif" alt="验证码图片" title="验证码图片" width="130px" height="53px">
		<a id="b" href="javascript:return false;">认不得？换一张</a>
	</span>
	
   添加以下js代码实现点击图片、文字切换验证码图片
  <script type="/text/javascript"> 
   //刷新验证码
$(function(){$("#a").click(function(){$("#a").attr("src","/getcapt.gif?"+Math.floor(Math.random()*100)).fadeIn()});$("#b").click(function(){$("#a").attr("src","/getcapt.gif?"+Math.floor(Math.random()*100)).fadeIn()})});
</script>
