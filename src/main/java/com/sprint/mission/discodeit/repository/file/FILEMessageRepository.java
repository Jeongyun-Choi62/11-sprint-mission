package com.sprint.mission.discodeit.repository.file;

import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.repository.MessageRepository;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
//@Repository
public class FILEMessageRepository implements MessageRepository {

    private final Path directory;

    public FILEMessageRepository() {
        this.directory = Path.of("src/main/resources/Messages/");
    }

    @Override
    public boolean saveMessage(Message message) {
        return false;
    }

    @Override
    public Optional<Message> getMessage(UUID messageId) {
        return Optional.empty();
    }

    @Override
    public Optional<Message> getLastMessagebyChannelId(UUID channelId) {
        return Optional.empty();
    }

    @Override
    public List<Message> getAllMessage() {
        return List.of();
    }

    @Override
    public List<Message> getAllByChannelId(UUID channelId) {
        return List.of();
    }

    @Override
    public boolean deleteMessage(UUID messageId) {
        return false;
    }

    @Override
    public boolean isExistMessage(UUID messageId) {
        return false;
    }

    @Override
    public boolean channelsMessagedelete(UUID channelId) {
        return false;
    }

    private Map<UUID ,Message> load(Path directory) {
        if (Files.exists(directory)) {


            try (Stream<Path> stream =  Files.list(directory))

            {
                Map<UUID ,Message> map;


                map = stream.map(path -> {
                            try (
                                    FileInputStream fis = new FileInputStream(path.toFile());
                                    ObjectInputStream ois = new ObjectInputStream(fis)
                            ) {
                                Object data = ois.readObject();
                                return  (Message)data;
                            } catch (IOException | ClassNotFoundException e) {
                                throw new RuntimeException(e);
                            }
                        })
                        .collect(Collectors.toMap(
                                Message::getId,
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

    private void save(Path filePath, Message message) {
        try(
                FileOutputStream fos = new FileOutputStream(filePath.toFile());
                ObjectOutputStream oos = new ObjectOutputStream(fos)
        ) {
            oos.writeObject(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    private Path pathToUserId(UUID messageId){

        return directory.resolve(messageId + ".dat");

    }


}
