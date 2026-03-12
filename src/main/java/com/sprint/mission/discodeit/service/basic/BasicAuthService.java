package com.sprint.mission.discodeit.service.basic;

import com.sprint.mission.discodeit.dto.userdto.UserInfoDto;
import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.entity.UserStatus;
import com.sprint.mission.discodeit.repository.UserRepository;
import com.sprint.mission.discodeit.repository.UserStatusRepository;
import com.sprint.mission.discodeit.service.AuthService;
import com.sprint.mission.discodeit.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
@RequiredArgsConstructor
public class BasicAuthService implements AuthService {

    private final UserRepository userRepository;
    private final UserStatusRepository userStatusRepository;

    @Override
    public UserInfoDto login(String userName, String password) {

        User user = userRepository.getUserByNickname(userName).orElseThrow(IllegalArgumentException::new);
        UserInfoDto userInfo;
        if (user.checkSamePassword(password)) {

            userInfo = new UserInfoDto(
                    user.getId(),
                    user.getNickname(),
                    user.getEmail(),
                    user.getProfileImage(),
                    userStatusRepository.readUserStatus(user.getId()).orElseThrow(IllegalArgumentException::new)
            );
        } else throw new PasswordfailException("비밀번호가 일치 하지 않습니다.");

        return userInfo;
    }

    public class PasswordfailException extends RuntimeException{

        public PasswordfailException(String message){
            super(message);
        }
    }
    






}













