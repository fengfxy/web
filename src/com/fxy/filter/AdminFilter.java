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

import com.fxy.util.log.LogUtil;
import com.fxy.util.path.PathUtil;

@WebFilter(
		filterName="F002",
		urlPatterns={"/admin/*"}
		)
public class AdminFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest=(HttpServletRequest) request;
		HttpServletResponse httpServletResponse =(HttpServletResponse) response;
		LogUtil.log("ContextPath:"+httpServletRequest.getContextPath());
		LogUtil.log("RequestURI:"+httpServletRequest.getRequestURI());
		if(httpServletRequest.getSession().getAttribute("Admin")!=null||httpServletRequest.getRequestURL().equals("/Profxy2017/admin/upload/*")){
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
