package com.sprint.mission.discodeit.service.basic;

import com.sprint.mission.discodeit.dto.userdto.CreateUserDto;
import com.sprint.mission.discodeit.dto.userdto.UpdateUserDto;
import com.sprint.mission.discodeit.dto.userdto.UserInfoDto;
import com.sprint.mission.discodeit.entity.BinaryContent;
import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.entity.UserStatus;
import com.sprint.mission.discodeit.exception.service.DiffPasswordException;
import com.sprint.mission.discodeit.exception.service.DupEmailException;
import com.sprint.mission.discodeit.exception.service.DupNameException;
import com.sprint.mission.discodeit.repository.*;
import com.sprint.mission.discodeit.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class BasicUserService implements UserService {

    private final UserRepository userRepository;
    private final UserStatusRepository userStatusRepository;
    private final BinaryContentRepository binaryContentRepository;





    @Override
    public UserInfoDto createUser(CreateUserDto createUserDTO) {

        //유저 생성
        User user = new User(
                createUserDTO.nickname(),
                createUserDTO.email(),
                createUserDTO.password(),
                createUserDTO.profileImage().getId()
        );

        BinaryContent img = createUserDTO.profileImage();

        // 닉네임 체크
        if(userRepository.isExistUserByNickname(createUserDTO.nickname())){
            throw new DupNameException();
        }

        //이메일 체크
        if(userRepository.isExistUserByEmail(createUserDTO.email())){
           throw new DupEmailException();
        }

        //프로필 사진 체크
        if(img.getType() != BinaryContent.Type.PROFILEIMG)
            throw new IllegalArgumentException("프로필 사진 타입이 아닙니다.");



        //유저 저장
        userRepository.saveUser(user);
        //유저 상태 저장
        userStatusRepository.saveUserStatus(new UserStatus(user.getId()));
        //프로필 저장
        binaryContentRepository.saveBinaryContent(img);


        return infoDtoToUser(user);
    }

    @Override
    public UserInfoDto readUser(UUID userId) {

        //유저 가져오기
        User user = userRepository.getUser(userId).orElseThrow();
        return infoDtoToUser(user);



    }

    @Override
    public List<UserInfoDto> readAllUser() {

        //유저 리스트 가져오기
        return userRepository.getAllUser().stream()
                .map(this::infoDtoToUser)
                .toList();

    }

    @Override
    public UserInfoDto updateUser(UpdateUserDto updateUserDto) {

        //유저 가져오기
        User user = userRepository.getUser(updateUserDto.userId()).orElseThrow();

        //기존 닉네임과 다르면 중복 체크 후 변경
        if(!user.getNickname().equals(updateUserDto.newNickname())){

            if(userRepository.isExistUserByNickname(updateUserDto.newNickname())){
                throw new DupNameException();
            }
            user.updateNickname(updateUserDto.newNickname(),updateUserDto.oldPassword());
        }

        if(!user.getEmail().equals(updateUserDto.newEmail())){
            if(userRepository.isExistUserByEmail(updateUserDto.newEmail())){
                throw new DupEmailException();
            }
            user.updateEmail(updateUserDto.newEmail(),updateUserDto.oldPassword());
        }

        if(!user.checkSamePassword(updateUserDto.oldPassword())){
            throw new DiffPasswordException();
        }
        user.updatePassword(updateUserDto.oldPassword(),updateUserDto.newPassword());

        userRepository.saveUser(user);
        return infoDtoToUser(user);

    }







    @Override
    public boolean deleteUser(UUID userId, String password) {

        // 유저 가져오기
        User user = userRepository.getUser(userId).orElseThrow();


        if(!user.checkSamePassword(password)){
            throw new DiffPasswordException();
        }

        //삭제
        userRepository.deleteUser(userId);
        userStatusRepository.deleteUserStatus(userId);

        return true;
    }




    UserInfoDto infoDtoToUser(User user){


        return new UserInfoDto(

                user.getId(),
                user.getNickname(),
                user.getEmail(),
                binaryContentRepository.getBinaryContent(user.getProfileId()).orElseThrow(),
                userStatusRepository.readUserStatus(user.getId()).orElseThrow()

        );
    }


}


