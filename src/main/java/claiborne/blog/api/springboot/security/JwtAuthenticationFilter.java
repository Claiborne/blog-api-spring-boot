//package claiborne.blog.api.springboot.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//
//  @Autowired
//  private JwtTokenProvider tokenProvider;
//
//  @Autowired
//  private CustomUserDetailsService customUserDetailsService;
//
//  @Override
//  protected void doFilterInternal(HttpServletRequest request,
//                                  HttpServletResponse response,
//                                  FilterChain filterChain) throws ServletException, IOException {
//    // get JWT token from http request
//
//    // validate token
//
//    // get username from token
//
//    //load user associated with token
//
//  }
//}
