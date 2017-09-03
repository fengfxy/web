package com.fxy.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fxy.util.path.PathUtil;

@WebFilter(
		filterName="F003",
		urlPatterns={
				"/user.jsp",
				"/userPhoto.jsp",
				"/userPassWord.jsp",
				"/UsersInfoEditBeforeServlet.action",
				"/serlvet/UsersInfoEditServlet.action"
		}
		)
public class UsersFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest=(HttpServletRequest) request;
		HttpServletResponse httpServletResponse =(HttpServletResponse) response;
		httpServletRequest.setCharacterEncoding("UTF-8");
		httpServletResponse.setContentType("text/html; charset=UTF-8");
		if(httpServletRequest.getSession().getAttribute("Users")!=null){
			chain.doFilter(request, response);
			
		}else{
			httpServletResponse.sendRedirect(PathUtil.getBasePath(httpServletRequest, "index.jsp"));
		}
		
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
