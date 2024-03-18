package backend.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.Authentication;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthorizationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtProvider jwtProvider;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		Authentication authentication = jwtProvider.getAuthentication(request);
		if (authentication != null && jwtProvider.isTokenValid(request)) {
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}else {
	        System.out.println("Token is not valid or authentication is null.");
	    }
		
		filterChain.doFilter(request, response);
		
		}
	
}
