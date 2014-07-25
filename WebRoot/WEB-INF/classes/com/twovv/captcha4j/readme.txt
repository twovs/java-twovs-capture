1. 在web.xml文件中配置
	
	<servlet>
	        <servlet-name>Captcha4j</servlet-name>
	        <servlet-class>com.twovv.captcha4j.JCapServlet</servlet-class>
	</servlet>
	<servlet-mapping>
	        <servlet-name>Captcha4j</servlet-name>
	        <url-pattern>/getCaptImg</url-pattern>
	</servlet-mapping>
	
2. 在需要显示图片的页面中,通过如下代码获得验证图片

		<img src="getCaptImg" /> <br/>
		<input type="text" name="captcha4j" value="" />
		
3. Action处理

	import javax.servlet.http.HttpServletRequest;

	import org.apache.struts2.interceptor.ServletRequestAware;
	
	import com.twovv.captcha4j.Constants;
	import com.opensymphony.xwork2.ActionSupport;
	
	public class TestCapt extends ActionSupport implements ServletRequestAware {
		
		private HttpServletRequest request;
		
		public void setServletRequest(HttpServletRequest request) {
			this.request = request;
		}
	
		@Override
		public String execute() throws Exception {
			String expectedCapt4j = (String)request.getSession()
		    .getAttribute(Constants.JCAP_SESSION_KEY);
		String inputCapt4j = request.getParameter("captcha4j");
		
		if (inputCapt4j == null || !inputCapt4j.equalsIgnoreCase(expectedCapt4j))
		{
			return INPUT;
			
		}
			return SUCCESS;
		}
	
	}	
	
4. 在struts2配置文件中配置Action结果			