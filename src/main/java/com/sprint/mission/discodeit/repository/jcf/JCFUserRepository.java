package com.sprint.mission.discodeit.repository.jcf;

import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.repository.UserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JCFUserRepository implements UserRepository {

    Map<String, User> data;

    public JCFUserRepository(){

        data = new HashMap<>();

    }

    @Override
    public boolean saveUser(User user) {

        if(data.containsKey(user.getUserId())){
            return false;
        }
        return data.put(user.getUserId(), user) != null;

    }

    @Override
    public User getUser(String userId) {

        return data.getOrDefault(userId, null);

    }

    @Override
    public List<User> getAllUser() {
        return data.values().stream().toList();
    }

    @Override
    public boolean updateUser(User user) {

        return data.put(user.getUserId(), user) != null;

    }

    @Override
    public boolean deleteUser(String userId) {

        return data.remove(userId) != null;

    }

    @Override
    public boolean isExistUser(String userId) {
        return data.containsKey(userId);
    }




}
