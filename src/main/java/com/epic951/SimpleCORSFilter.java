package com.epic951;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.ForbiddenException;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)

public class SimpleCORSFilter implements Filter {

	@Override
	public void init(FilterConfig fc) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException, ForbiddenException {
		HttpServletResponse response = (HttpServletResponse) resp;
		HttpServletRequest request = (HttpServletRequest) req;
		System.err.println(request.getHeader("Host"));
		response.setHeader("Access-Control-Allow-Origin", determineAllowedOrigin(request.getHeader("Host")));
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers",
				"x-requested-with, authorization, Content-Type, Authorization, credential, X-XSRF-TOKEN");

		if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
			response.setStatus(HttpServletResponse.SC_OK);
		} else {
			chain.doFilter(req, resp);
		}
	}

	private String determineAllowedOrigin(String header) {
		String[] allowedOrigins = { "telecom-platform.herokuapp.com", "telecom-platform-frontend.herokuapp.com",
				"localhost:4200", "localhost:8080", "localhost:5000" };
		for (String origin : allowedOrigins) {
			if (origin.equals(header)) {
				System.err.println("origin " + origin + "   header " + header);
				return origin;
			}
		}
		System.err.println(header);
		throw new ForbiddenException();
	}

	@Override
	public void destroy() {
	}

}