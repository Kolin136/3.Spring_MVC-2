package hello.login.web.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogFilter implements Filter {

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    log.info("log filter init");
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    log.info("log filter doFilter");

    HttpServletRequest httpRequest = (HttpServletRequest) request;

    String requestURI = httpRequest.getRequestURI();  // 모든 사용자의 요청 url 남기기
    String uuid = UUID.randomUUID().toString();  // 요청 구분용 uuid

    try {
      log.info("REQUEST [{}][{}]", uuid, requestURI);
      chain.doFilter(request, response);  // 다음 필터 호출하기
    } catch (Exception e) {
      throw e;
    } finally {
      log.info("RESPONSE [{}][{}]", uuid, requestURI);
    }
  }


  @Override
  public void destroy() {
    log.info("log filter destroy");
  }
}
