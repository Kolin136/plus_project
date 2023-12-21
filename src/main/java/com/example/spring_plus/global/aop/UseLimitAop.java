package com.example.spring_plus.global.aop;


import com.example.spring_plus.global.exception.CustomException;
import com.example.spring_plus.global.exception.ErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Slf4j(topic = "log_print")
@Aspect
@Component
public class UseLimitAop {

  @Pointcut("execution(* com.example.spring_plus.post.controller.*.createPost(..))")
  private void post() {}

  @Pointcut("execution(* com.example.spring_plus.comment.controller.*.createComment(..))")
  private void comment() {}


  private  Map<String, Integer> requestCount = new HashMap<>();

  @Before("post() || comment()")
  public void limitRequest(JoinPoint joinPoint) {
    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    String clientIp = getIPaddress(request);

    log.info(joinPoint.getSignature().getName());
    int count = requestCount.getOrDefault(clientIp, 0);
    if (count > 2) {
      throw new CustomException(ErrorCode.DAILY_REQUEST_LIMIT_EXCEEDED);
    }
    requestCount.put(clientIp, count + 1);
  }

  public static String getIPaddress(HttpServletRequest req){

    String clientIp = req.getHeader("X-Forwarded-For");

    if (clientIp == null || clientIp.length() == 0 || "unknown".equalsIgnoreCase(clientIp)) {
      clientIp = req.getHeader("Proxy-Client-IP");
    }
    if (clientIp == null || clientIp.length() == 0 || "unknown".equalsIgnoreCase(clientIp)) {
      clientIp = req.getHeader("WL-Proxy-Client-IP");
    }
    if (clientIp == null || clientIp.length() == 0 || "unknown".equalsIgnoreCase(clientIp)) {
      clientIp = req.getHeader("HTTP_CLIENT_IP");
    }
    if (clientIp == null || clientIp.length() == 0 || "unknown".equalsIgnoreCase(clientIp)) {
      clientIp = req.getHeader("HTTP_X_FORWARDED_FOR");
    }
    if (clientIp == null || clientIp.length() == 0 || "unknown".equalsIgnoreCase(clientIp)) {
      clientIp = req.getRemoteAddr();
    }

    return clientIp;

  }
}