package com.sprint.mission.discodeit.service.basic;

import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.repository.UserRepository;
import com.sprint.mission.discodeit.service.UserService;

import java.util.List;

public class BasicUserService implements UserService {

    UserRepository userRepository;

    public BasicUserService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }


    @Override
    public void createUser(String nickname, String password, String userId) {

        User user = new User(userId, password, nickname);

        if(!userRepository.saveUser(user)){

            System.err.println("저장에 실패했습니다.");
            return;
        }


        System.out.println(user.getNickname()+ " 님 생성 완료!");



    }

    @Override
    public void readUser(String userId) {

        User user = userRepository.getUser(userId);

        if(user == null){
            System.err.println("유저를 읽어오지 못했습니다.");
            return;
        }

        System.out.println(user);



    }

    @Override
    public void readAllUser() {

        List<User> users = userRepository.getAllUser();

        if(users == null){
            System.err.println("유저 리스트를 불러오는데 문제가 발생했습니다.");
            return;
        }



        users.stream()
                .sorted(User::compareTo)
                .forEach(System.out::println);




    }

    @Override
    public void updateNickname(String userId, String password, String nickname) {

        User user = userRepository.getUser(userId);

        // 잘못된 유저 아이디 체크
        if(user == null){
            System.err.println("존재하지 않는 유저 아이디입니다.");
            return;
        }

        // 닉네임 업데이트
        if(!user.updateNickname(nickname,password)){
            //비밀번호 불일치
            System.err.println("패스워드가 일치하지 않습니다.");
            return;

        }

        //repository에 반영
        if(!userRepository.updateUser(user)){
            System.err.println("유저 업데이트 중 이상이 발생했습니다.");

        }






    }

    @Override
    public void updatePassword(String userId, String oldPassword, String newPassword) {

        User user = userRepository.getUser(userId);

        // 잘못된 유저 아이디 체크
        if(user == null){
            System.err.println("존재하지 않는 유저 아이디입니다.");
            return;
        }

        // 비밀번호 업데이트
        if(!user.updatePassword(oldPassword,newPassword)){
            //비밀번호 불일치
            System.err.println("패스워드가 일치하지 않습니다.");
            return;

        }

        //repository에 반영
        if(!userRepository.updateUser(user)){
            System.err.println("유저 업데이트 중 이상이 발생했습니다.");

        }



    }

    @Override
    public void updateStatus(String userId, String password, User.Status status) {

        User user = userRepository.getUser(userId);

        // 잘못된 유저 아이디 체크
        if(user == null){
            System.err.println("존재하지 않는 유저 아이디입니다.");
            return;
        }

        // 비밀번호 업데이트
        if(!user.updateStatus(status,password)){
            //비밀번호 불일치
            System.err.println("패스워드가 일치하지 않습니다.");
            return;

        }

        //repository에 반영
        if(!userRepository.updateUser(user)){
            System.err.println("유저 업데이트 중 이상이 발생했습니다.");

        }






    }

    @Override
    public void deleteUser(String userId, String password) {



        // 유저 가져오기
        User user = userRepository.getUser(userId);


        // 잘못된 유저 아이디 체크
        if(user == null){
            System.err.println("존재하지 않는 유저 아이디입니다.");
            return;
        }

        //잘못된 비밀번호 체크
        if(!user.checkSamePassword(password)){
            System.err.println("패스워드가 일치하지 않습니다.");
            return;

        }




        //삭제
        if(!userRepository.deleteUser(userId)){
            System.err.println("삭제 도중 이상이 발생했습니다.");
        }







    }


}
