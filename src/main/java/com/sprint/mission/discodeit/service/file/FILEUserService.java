package com.sprint.mission.discodeit.service.file;

import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.service.UserService;

import java.io.*;
import java.nio.file.Path;
import java.util.List;

public class FILEUserService extends FILEServiceSystem implements UserService {

    private final Path directory = Path.of("src/main/resources/Users/");




    public FILEUserService() {

    }


    @Override
    public void createUser(String nickname, String password, String userId) {

        if(isExistUser(userId)) {
            System.out.println("이미 존재하는 유저의 id입니다");
            return;
        }

        User user = new User(userId, password, nickname);

        save(idToPath(userId), user);

        System.out.println( nickname + "님 생성 완료!");

    }

    @Override
    public void readUser(String userId) {
        List<User> users = load(directory);
        users.stream()
                .filter(user -> user.getUserId().equals(userId))
                .forEach(System.out::println);

    }

    @Override
    public void readAllUser() {

        List<User> users = load(directory);
        users.stream()
                .sorted(User::compareTo)
                .forEach(System.out::println);

    }

    @Override
    public void updateNickname(String userId, String nickname) {

        if(!isExistUser(userId)){
            System.out.println("존재하지 않는 유저 아이디입니다.");
            return;
        }

        List<User> users = load(directory);

        User user = users.stream()
                .filter(user1 -> user1.getUserId().equals(userId))
                .findFirst()
                .orElseThrow();
        user.updateNickname(nickname);

        save(idToPath(userId) , user);

        System.out.println("유저 닉네임 업데이트 완료!");





    }

    @Override
    public void updatePassword(String userId, String oldPassword, String newPassword) {

        if(!isExistUser(userId)){
            System.out.println("존재하지 않는 유저 아이디입니다.");
            return;
        }

        List<User> users = load(directory);

        User user = users.stream()
                .filter(user1 -> user1.getUserId().equals(userId))
                .findFirst()
                .orElseThrow();
        if(!user.updatePassword(oldPassword, newPassword)){
            System.out.println("비밀번호가 일치하지 않습니다");
            return;

        }

        save(idToPath(userId) , user);

        System.out.println("유저 비밀번호 변경 완료!");
    }

    @Override
    public void updateStatus(String userId, User.Status status) {

        if(!isExistUser(userId)){
            System.out.println("존재하지 않는 유저 아이디입니다.");
            return;
        }

        List<User> users = load(directory);

        User user = users.stream()
                .filter(user1 -> user1.getUserId().equals(userId))
                .findFirst()
                .orElseThrow();
        user.updateStatus(status);

        save(idToPath(userId) , user);

        System.out.println("유저 상태 업데이트 완료!");

    }

    @Override
    public void deleteUser(String userId, String password) {


        if(!isExistUser(userId)){
            System.out.println("존재하지 않는 유저 아이디입니다.");
            return;

        }
        delete(idToPath(userId));





    }

    @Override
    public boolean isExistUser(String userId) {

        List<User> users = load(directory);
        for(User user : users){
            if(user.getUserId().equals(userId)){
                return true;
            }
        }
        return false;


    }



    Path idToPath(String userId){
        return directory.resolve(userId+".dat");
    }


}
