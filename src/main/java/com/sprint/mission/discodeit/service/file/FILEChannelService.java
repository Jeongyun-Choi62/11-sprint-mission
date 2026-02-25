package com.sprint.mission.discodeit.service.file;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.service.ChannelService;

import java.nio.file.Path;
import java.util.List;

public class FILEChannelService extends FILEServiceSystem implements ChannelService {


    private final Path directory = Path.of("src/main/resources/Channels/");

    public FILEChannelService() {

    }

    @Override
    public void createChannel(String channelName, String ownerID, String channelId) {

        if(isExistChannel(channelId)){
            System.out.println("이미 존재하는 채널 아이디입니다.");
            return;

        }


        Channel channel = new Channel(channelName,ownerID,channelId);

        save(idToPath(channelId),channel);

        System.out.println(channel.getChannelName()+ " 채널 생성 완료!");



    }

    @Override
    public void readChannel(String channelId) {

        if(!isExistChannel(channelId)){
            System.out.println("존재하지 않는 채널 아이디입니다.");
            return;
        }

        List<Channel> Channels =  load(directory);

        Channel channel = Channels.stream().
                filter(c -> c.getChannelId().equals(channelId)).
                findFirst().orElse(null);


        System.out.println(channel);


    }

    @Override
    public void readAllChannel() {

        List<Channel> Channels =  load(directory);

        Channels.stream()
                .sorted(Channel::compareTo)
                .forEach(System.out::println);


    }

    @Override
    public void updateChannelName(String channelId, String channelName) {
        if(!isExistChannel(channelId)){
            System.out.println("존재하지 않는 채널 아이디입니다.");
            return;
        }

        List<Channel> Channels =  load(directory);
        Channel channel = Channels.stream()
                            .filter(c -> c.getChannelId().equals(channelId))
                            .findFirst()
                            .orElseThrow();
        channel.updateChannelName(channelName);

        save(idToPath(channelId),channel);

        System.out.println("채널 이름 업데이트 완료!");


    }

    @Override
    public void updateChannelOwner(String channelId, String ownerID) {
        if(!isExistChannel(channelId)){
            System.out.println("존재하지 않는 채널 아이디입니다.");
            return;
        }

        List<Channel> Channels =  load(directory);
        Channel channel = Channels.stream()
                .filter(c -> c.getChannelId().equals(channelId))
                .findFirst()
                .orElseThrow();

        if(!isChannelsMember(channelId,ownerID)){
            System.out.println("해당 유저는 해당 채널에 없습니다.");
            return;
        }

        channel.updateOwner(ownerID);


        save(idToPath(channelId),channel);

        System.out.println("채널장 업데이트 완료!");




    }

    @Override
    public void addMember(String channelId, String memberId) {

        if(!isExistChannel(channelId)){
            System.out.println("존재하지 않는 채널 아이디입니다.");
            return;
        }

        List<Channel> Channels =  load(directory);

        Channel channel = Channels.stream()
                .filter(c -> c.getChannelId().equals(channelId))
                .findFirst()
                .orElseThrow();

        channel.addMember(memberId);

        save(idToPath(channelId),channel);

        System.out.println(channel.getChannelName() +"채널에 멤버 추가 완료!");


    }

    @Override
    public void removeMember(String channelId, String memberId) {

        if(!isExistChannel(channelId)){
            System.out.println("존재하지 않는 채널 아이디입니다.");
            return;
        }

        List<Channel> Channels =  load(directory);

        Channel channel = Channels.stream()
                .filter(c -> c.getChannelId().equals(channelId))
                .findFirst()
                .orElseThrow();

        channel.removeMember(memberId);

        save(idToPath(channelId),channel);

        System.out.println("채널에서 유저가 나갔습니다!");

    }

    @Override
    public void deleteChannel(String channelId) {

        if(!isExistChannel(channelId)){
            System.out.println("존재하지 않는 채널 아이디입니다.");
            return;

        }
        delete(idToPath(channelId));


    }


    @Override
    public boolean isExistChannel(String channelId) {

        List<Channel> channels =  load(directory);

        for(Channel channel : channels){

            if(channel.getChannelId().equals(channelId)){
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean isChannelsMember(String channelId, String memberId) {

        if(!isExistChannel(channelId))
            return false;


        List<Channel> Channels =  load(directory);

        Channel channel = Channels.stream().
                filter(c -> c.getChannelId().equals(channelId)).
                findFirst().orElse(null);





        return false;
    }

    Path idToPath(String channelId){
        return directory.resolve(channelId+".dat");
    }
}
