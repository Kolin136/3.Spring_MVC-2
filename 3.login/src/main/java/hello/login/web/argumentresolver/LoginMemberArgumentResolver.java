package hello.login.web.argumentresolver;

import hello.login.domain.member.Member;
import hello.login.web.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Slf4j
public class LoginMemberArgumentResolver implements HandlerMethodArgumentResolver {
  @Override
  public boolean supportsParameter(MethodParameter parameter) {

    log.info("supportsParameter 실행");
    boolean hasLoginAnnotation = parameter.hasParameterAnnotation(Login.class); // 로그인 애노테이션이 파라미터에 있나 체크
    boolean hasMemberType = Member.class.isAssignableFrom(parameter.getParameterType());

    return hasLoginAnnotation && hasMemberType;  // 이 둘다 트루면 resolveArgument()메소드 실행
  }

  @Override
  public Object resolveArgument(MethodParameter parameter,
      ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
      WebDataBinderFactory binderFactory) throws Exception {

    log.info("resolveArgument 실행");
    // NativeWebRequest 에서 getNativeRequest() 하면  HttpServletRequest 뽑을 수 있다.
    HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();

    HttpSession session = request.getSession(false);

    if (session == null) {
      return null; // 직접 만든 로그인 애노테이션인 Member 객체에 null을 넣는다
    }
    // 세션에 해당 상수데 대한 값이 있으면 값 넘어 가고 없으면 null
    return session.getAttribute(SessionConst.LOGIN_MEMBER);
  }
}