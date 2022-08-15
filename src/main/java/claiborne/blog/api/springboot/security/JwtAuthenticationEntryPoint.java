//package claiborne.blog.api.springboot.security;
//
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.AuthenticationEntryPoint;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Component
//public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
//
//  // this method is called whenever an exception is thrown due to an unauthorized user trying to
//  // access a resource that requires auth
//  @Override
//  public void commence(HttpServletRequest request,
//                       HttpServletResponse response,
//                       AuthenticationException authException) throws IOException, ServletException {
//    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
//  }
//}
