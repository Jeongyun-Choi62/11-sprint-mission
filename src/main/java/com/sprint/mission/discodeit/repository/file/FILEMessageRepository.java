package com.sprint.mission.discodeit.repository.file;

import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.repository.MessageRepository;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
@Repository
public class FILEMessageRepository implements MessageRepository {

    private final Path directory;

    public FILEMessageRepository() {
        this.directory = Path.of("src/main/resources/Messages/");
    }

    @Override
    public boolean saveMessage(Message message) {

        if(isExistMessage(message.getId())){
            return false;
        }
        save(pathToUserId(message.getId()), message);
        return true;


    }

    @Override
    public Message getMessage(UUID messageId) {
        Map<UUID ,Message> map = load(directory);
        return map.getOrDefault(messageId,null);
    }

    @Override
    public List<Message> getAllMessage() {
        Map<UUID ,Message> map = load(directory);
        return map.values().stream().toList();
    }

    @Override
    public boolean updateMessage(Message message) {
        Map<UUID ,Message> map = load(directory);
        map.put(message.getId(), message);
        save(pathToUserId(message.getId()),message);
        return true;
    }

    @Override
    public boolean deleteMessage(UUID messageId) {

        if(!isExistMessage(messageId))
            return false;
        try {
            Files.deleteIfExists(pathToUserId(messageId));
        }
        catch(IOException e){
            return false;
        }
        return true;
    }



    @Override
    public boolean isExistMessage(UUID messageId) {
        Map<UUID, Message> map = load(directory);

        return map.containsKey(messageId);

    }

    @Override
    public boolean channelsMessagedelete(UUID channelId) {
        Map<UUID, Message> map = load(directory);

        map.values().stream()
                .filter(msg-> msg.getChannelId().equals(channelId))
                .forEach(msg -> deleteMessage(msg.getId()));

        return true;

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
