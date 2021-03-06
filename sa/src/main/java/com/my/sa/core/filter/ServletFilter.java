package com.my.sa.core.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.my.sa.core.global.Constants;
import com.my.sa.domain.User;

@WebFilter(filterName="webFilter",urlPatterns="/*")
public class ServletFilter implements Filter{

	Logger logger = Logger.getLogger(ServletFilter.class);
	
	private String loginUrl;
	
	public String getLoginUrl() {
		return loginUrl;
	}

	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest hrequest = (HttpServletRequest) request;
		HttpServletResponse hresponse = (HttpServletResponse) response;
		
        String requestUri = hrequest.getRequestURI();
        String requestUrl = hrequest.getRequestURL().toString();
        String contextPath = hrequest.getContextPath();
        chain.doFilter(hrequest, hresponse);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
	private void showLoginPage(HttpServletRequest hrequest, HttpServletResponse response)
			throws UnsupportedEncodingException, ServletException, IOException {

		logger.info("show Login Page");
		
		String destination;

		String serverURL = hrequest.getScheme() + "://" + hrequest.getServerName() + ":" + hrequest.getServerPort()
				+ hrequest.getContextPath();

		destination = (serverURL + loginUrl);

		response.sendRedirect(destination);
	}

}
