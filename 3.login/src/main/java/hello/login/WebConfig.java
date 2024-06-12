package hello.login;

import hello.login.web.filter.LogFilter;
import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {
  @Bean
  public FilterRegistrationBean logFilter() {
    FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
    filterRegistrationBean.setFilter(new LogFilter()); // 직접 만든 로그 필터를 넣어준다
    filterRegistrationBean.setOrder(1);  // 필터 순서 정하기
    filterRegistrationBean.addUrlPatterns("/*");  // 어떤 URL 패턴에 할건가 /*은 모든 url에 적용
    return filterRegistrationBean;
  }
}