package com.sprint.mission.discodeit.repository.file;

import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
//@Repository
public class FILEUserRepository  implements UserRepository {

    private final Path directory;
    public FILEUserRepository() {
        directory = Path.of("src/main/resources/Users/");
    }

    @Override
    public boolean saveUser(User user) {
        return false;
    }

    @Override
    public Optional<User> getUser(UUID userId) {
        return Optional.empty();
    }

    @Override
    public List<User> getAllUser() {
        return List.of();
    }

    @Override
    public Optional<User> getUserByNickname(String nickname) {
        return Optional.empty();
    }

    @Override
    public boolean deleteUser(UUID userId) {
        return false;
    }

    @Override
    public boolean isExistUserByNickname(String nickname) {
        return false;
    }

    @Override
    public boolean isExistUserByEmail(String Email) {
        return false;
    }

    @Override
    public boolean isExistUser(UUID userId) {
        return false;
    }

    private Map<UUID,User> load(Path directory) {
        if (Files.exists(directory)) {


            try (Stream<Path> stream =  Files.list(directory))

            {
                Map<UUID,User> map;


                map = stream.map(path -> {
                        try (
                                    FileInputStream fis = new FileInputStream(path.toFile());
                                    ObjectInputStream ois = new ObjectInputStream(fis)
                            ) {
                                Object data = ois.readObject();
                                return  (User)data;
                            } catch (IOException | ClassNotFoundException e) {
                                throw new RuntimeException(e);
                            }
                        })
                        .collect(Collectors.toMap(
                                User::getId,
                                Function.identity()

                        ));
                return map;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            return new HashMap<>();
        }
    }

     private void save(Path filePath, User user) {
        try(
                FileOutputStream fos = new FileOutputStream(filePath.toFile());
                ObjectOutputStream oos = new ObjectOutputStream(fos)
        ) {
            oos.writeObject(user);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    private Path pathToUserId(UUID userId){

        return directory.resolve(userId + ".dat");

    }


}
