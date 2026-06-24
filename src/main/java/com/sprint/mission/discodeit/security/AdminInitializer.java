package com.sprint.mission.discodeit.security;

import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.repository.JPAUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminInitializer implements ApplicationRunner {

  private final JPAUserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @Value("${admin.username:admin}")
  private String adminUsername;

  @Value("${admin.password}")
  private String adminPassword;

  @Override
  public void run(ApplicationArguments args) {
    // 이미 있으면 아무것도 안 함
    if (userRepository.existsByUsername(adminUsername)) {
      return;
    }

    User admin = new User(
        adminUsername,
        "discodeit@discodeit.com",
        adminPassword,
        null,
        null
    );

    userRepository.save(admin);
  }
}