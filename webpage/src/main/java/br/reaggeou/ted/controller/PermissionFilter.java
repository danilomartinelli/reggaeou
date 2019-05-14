package br.reaggeou.ted.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;

@WebFilter("/admin/*")
public class PermissionFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		Object user = ((HttpServletRequest) request).getSession().getAttribute("user");

		if (user != null) {
			chain.doFilter(request, response);
		} else {
			((HttpServletResponse) response).sendRedirect("/Reaggeou/index.jsp");
		}
	}

}
