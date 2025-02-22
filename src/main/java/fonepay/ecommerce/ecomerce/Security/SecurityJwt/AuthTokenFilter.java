package fonepay.ecommerce.ecomerce.Security.SecurityJwt;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import fonepay.ecommerce.ecomerce.Security.SecurityService.UserDetailsImplementationService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthTokenFilter extends OncePerRequestFilter {

	@Autowired
	UserDetailsImplementationService impl;
	
	@Autowired
	JwtUtils utils;
	
	private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String jwt = parseJwt(request);
			if(jwt != null && utils.validateToken(jwt)) {
				String username = utils.getUserNameFromJwtToken(jwt);
				UserDetails userDetails = impl.loadUserByUsername(username);
				UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, null, userDetails.getAuthorities());
				token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(token);
			}
			
		} catch(Exception e) {
			logger.error("Cannot set user authentication: {}" + e.getMessage());
		}
		filterChain.doFilter(request, response);
	}
	
	private String parseJwt(HttpServletRequest request) {
		String headerAuth = request.getHeader("Authorization");
		if(StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
			return headerAuth.substring(7);
		}
		return null;
	}

}
