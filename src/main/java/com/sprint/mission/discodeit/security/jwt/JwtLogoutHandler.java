package com.sprint.mission.discodeit.security.jwt;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtLogoutHandler implements LogoutHandler {

  //private final JwtRegistry jwtRegistry;

  @Override
  public void logout(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) {

    if (request.getCookies() == null) {
      return;
    }

    Arrays.stream(request.getCookies())
        .filter(cookie -> cookie.getName().equals(JwtTokenProvider.REFRESH_TOKEN_COOKIE_NAME))
        .findFirst()
        .ifPresent(cookie -> {

          // jwtRegistry.invalidate(refreshToken);

          //TODO 3: 응답에 만료된(또는 빈 값의) 쿠키를 다시 설정해서
          //         브라우저에서도 쿠키가 삭제되도록 하세요.
          //         힌트: 같은 이름의 쿠키를 maxAge=0으로 설정하면 브라우저가 즉시 삭제합니다.

          ResponseCookie deleteCookie = ResponseCookie.from(
                  JwtTokenProvider.REFRESH_TOKEN_COOKIE_NAME, "")
              .httpOnly(true)
              .secure(true)        // 로컬 http면 false
              .path("/")
              .maxAge(0)           // 즉시 삭제
              .sameSite("Strict")
              .build();

          response.addHeader(HttpHeaders.SET_COOKIE, deleteCookie.toString());
        });

    // ???
  }
}