package com.sprint.mission.discodeit.repository.file;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.repository.ChannelRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
//@Repository
public class FILEChannelRepository implements ChannelRepository {

    private final Path directory;

    public FILEChannelRepository() {

        directory = Path.of("src/main/resources/Channels/");
    }

    @Override
    public boolean saveChannel(Channel channel) {
        return false;
    }

    @Override
    public Optional<Channel> getChannel(UUID channelId) {
        return Optional.empty();
    }

    @Override
    public List<Channel> getAllChannel() {
        return List.of();
    }

    @Override
    public boolean deleteChannel(UUID channelId) {
        return false;
    }

    @Override
    public boolean isExistChannel(UUID channelId) {
        return false;
    }

    private Map<UUID,Channel> load(Path directory) {
        if (Files.exists(directory)) {


            try (Stream<Path> stream =  Files.list(directory))

            {
                Map<UUID,Channel> map;


                map = stream.map(path -> {
                            try (
                                    FileInputStream fis = new FileInputStream(path.toFile());
                                    ObjectInputStream ois = new ObjectInputStream(fis)
                            ) {
                                Object data = ois.readObject();
                                return  (Channel)data;
                            } catch (IOException | ClassNotFoundException e) {
                                throw new RuntimeException(e);
                            }
                        })
                        .collect(Collectors.toMap(
                                Channel::getId,
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

    private void save(Path filePath, Channel channel) {
        try(
                FileOutputStream fos = new FileOutputStream(filePath.toFile());
                ObjectOutputStream oos = new ObjectOutputStream(fos)
        ) {
            oos.writeObject(channel);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    private Path pathToUserId(UUID channelId){

        return directory.resolve(channelId + ".dat");

    }











}
