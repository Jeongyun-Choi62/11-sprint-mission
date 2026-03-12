package com.sprint.mission.discodeit.service.basic;

import com.sprint.mission.discodeit.dto.userdto.CreateUserDto;
import com.sprint.mission.discodeit.dto.userdto.UserInfoDto;
import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.entity.UserStatus;
import com.sprint.mission.discodeit.repository.*;
import com.sprint.mission.discodeit.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class BasicUserService implements UserService {

    private final UserRepository userRepository;
    private final UserStatusRepository userStatusRepository;
    private final BinaryContentRepository binaryContentRepository;





    @Override
    public void createUser(CreateUserDto createUserDTO) {

        //유저 생성
        User user = new User(
                createUserDTO.nickname(),
                createUserDTO.email(),
                createUserDTO.password(),
                createUserDTO.profileImage()
        );


        // 닉네임 체크
        if(userRepository.isExistUserByNickname(createUserDTO.nickname())){
            try {
                throwDupNameException();
            }catch (Exception e){
                throw new RuntimeException(e);
            }
            return;
        }

        //이메일 체크
        if(userRepository.isExistUserByEmail(createUserDTO.email())){
            try{
                throwDupEmailException();Exception();
            }
            catch (Exception e){
                throw new RuntimeException(e);
            }
            return;
        }

        //유저 저장
        userRepository.saveUser(user);
        //유저 상태 저장
        UserStatusRepository.saveUserStatus(new UserStatus(user.getId()));

        //출력
        System.out.println(user.getNickname()+ " 님 생성 완료!");


    }

    @Override
    public void readUser(UUID userId) {

        //유저 가져오기
        User user = userRepository.getUser(userId).orElseThrow();

        UserInfoDto userDto = new UserInfoDto(

                user.getId(),
                user.getNickname(),
                user.getEmail(),
                user.getProfileImage(),
                userStatusRepository.readUserStatus(userId)
        );

        //출력
        System.out.println(userDto);



    }

    @Override
    public void readAllUser() {

        //유저 리스트 가져오기
        List<UserInfoDto> users = userRepository.getAllUser().stream()
                .map(user -> {

                    new UserInfoDto(
                            user.getId(),
                            user.getNickname(),
                            user.getEmail(),
                            user.getProfileImage(),
                            userStatusRepository.readUserStatus(user.getId())
                    );
                });



        try {

            //stream으로 전체 출력
            users.forEach(System.out::println);
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }



    }

    @Override
    public void updateUser(UUID userId, String newNickname, String newEmail,UserStatus status, String oldPassword,String newPassword) {

        //유저 가져오기
        User user = userRepository.getUser(userId).orElseThrow();


        //이메일 체크
        if(!user.getEmail().equals(newEmail) && userStatusRepository.isExistUserByEmail(createUserDTO.email())){
            try{
                throwDupEmailException();
            }
            catch (Exception e){
                throw new RuntimeException(e);
            }
            return;
        }



        try {

            if(!user.updateNickname(newNickname,oldPassword))
                throwDiffPasswordException();

            if(!user.updateEmail(newEmail,oldPassword))
                throwDiffPasswordException();

            if(!user.updateProfileImage(user.getProfileImage(),oldPassword))
                throwDiffPasswordException();

            if(!user.updatePassword(oldPassword, newPassword))
                throwDiffPasswordException();

        }
        catch (Exception e){
            throw new RuntimeException(e);
        }


        System.out.println("유저 정보가 수정되었습니다.");
    }







    @Override
    public void deleteUser(UUID userId, String password) {



        // 유저 가져오기
        User user = userRepository.getUser(userId).orElseThrow();

        //잘못된 비밀번호 체크

        try {
            if (!user.checkSamePassword(password)) {
                throwDiffPasswordException();
            }
        }
        catch (Exception e){

            throw new RuntimeException(e);
        }

        //삭제
        if(!userRepository.deleteUser(userId)){
            System.out.println("삭제 도중 이상이 발생했습니다.");
            return;
        }

        if(!userStatusRepository.deleteUserStatus(userId)){
            System.out.println("삭제 도중 이상이 발생했습니다.");
            return;
        }



        //출력
        System.out.println(user.getNickname() + " 님 삭제 완료!");

    }


    void throwDupNameException() throws Exception{

        throw new Exception("중복되는 닉네임 입니다.");
    }

    void throwDupEmailException() throws Exception{


        throw new Exception("중복되는 이메일 입니다.");
    }
    void throwDiffPasswordException() throws Exception{
        throw new Exception("비밀번호가 일치하지 않습니다.");

    }


}


