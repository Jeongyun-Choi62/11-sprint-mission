package com.sprint.mission.discodeit;

import com.sprint.mission.discodeit.binary.BinaryFile;
import com.sprint.mission.discodeit.dto.channeldto.ChannelMemberDto;
import com.sprint.mission.discodeit.dto.channeldto.CreatePrivateChannelDto;
import com.sprint.mission.discodeit.dto.channeldto.CreatePublicChannelDto;
import com.sprint.mission.discodeit.dto.channeldto.UpdateChannelDto;
import com.sprint.mission.discodeit.dto.messagedto.CreateMessageDto;
import com.sprint.mission.discodeit.dto.messagedto.UpdateMessageDto;
import com.sprint.mission.discodeit.dto.readstatusdto.CreateReadStatusDto;
import com.sprint.mission.discodeit.dto.userdto.CreateUserDto;
import com.sprint.mission.discodeit.dto.userdto.UpdateUserDto;
import com.sprint.mission.discodeit.dto.userstatusdto.CreateUserStatusDto;
import com.sprint.mission.discodeit.service.*;
import com.sprint.mission.discodeit.service.basic.BasicChannelService;
import com.sprint.mission.discodeit.service.basic.BasicMessageService;
import com.sprint.mission.discodeit.service.basic.BasicUserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class DiscodeitApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(DiscodeitApplication.class, args);

		UserService userService;
		ChannelService channelService;
		MessageService messageService;
		UserStatusService userStatusService;
		ReadStatusService readStatusService;
		BinaryContentService binaryContentService;
		AuthService authService;

		userService = context.getBean(BasicUserService.class);
		channelService = context.getBean(BasicChannelService.class);
		messageService = context.getBean(BasicMessageService.class);
		userStatusService = context.getBean(UserStatusService.class);
		readStatusService = context.getBean(ReadStatusService.class);
		binaryContentService = context.getBean(BinaryContentService.class);
		authService = context.getBean(AuthService.class);




		System.out.println("---------------------------------------------------");
		System.out.println("1. 유저 CRUD 테스트");
		System.out.println("---------------------------------------------------");
		System.out.println("1-1. Create User ");

		//유저 5명 생성

		UUID [] userArr = new UUID[5];

		System.out.println("5명 생성");

		userArr[0] = userService.create(new CreateUserDto("김하나","hanamail@gmail.com" ,"hanakim1111", new BinaryFile())).userId();
		userArr[1] = userService.create(new CreateUserDto("이두리","durimail@gmail.com" ,"durilee2222", null)).userId();
		userArr[2] = userService.create(new CreateUserDto("최삼식","samsikmail@gmail.com" ,"samsikchoi3333", null)).userId();
		userArr[3] = userService.create(new CreateUserDto("박네모","nemomail@gmail.com" ,"nemopark4444", null)).userId();
		userArr[4] = userService.create(new CreateUserDto("정오리","orimail@gmail.com" ,"orijeong5555", null)).userId();


		System.out.println("생성 완료!");

		System.out.println("\n");

		System.out.println("1-2. Read User");

		// 개인 출력

		System.out.println("이두리, 박네모 출력 테스트\n");

		System.out.println(userService.find(userArr[1]));
		System.out.println(userService.find(userArr[3]));

		//전체 출력
		System.out.println("\n모든 유저 출력\n");
		userService.findAll().forEach(System.out::println);
		System.out.println("\n");

		System.out.println("1-3. Update User");

		//닉네임 변경
		userService.updateUser(new UpdateUserDto(userArr[0],"뉴하나" ,"hanamail@naver.com", "hanakim1111", "hanakim1111", null));
		System.out.println(userService.find(userArr[0]));


		// 비밀번호 변경(틀린 비밀번호)
		try {
			userService.updateUser(new UpdateUserDto(userArr[0], "이상하나", "hanamail@naver.com", "hamakim1113", "hanakim", null));
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}


		//전체 출력
		System.out.println(userService.find(userArr[1]));
		System.out.println("\n");


		//2번째 유저 삭제 후 유저 목록 출력
		System.out.println("1-4. Delete User");
		userService.delete(userArr[1],"durilee2222");
		System.out.println("이두리 삭제 완료!");
		userService.findAll().forEach(System.out::println);

		System.out.println("\n");




		System.out.println("---------------------------------------------------");
		System.out.println("2. 채널 CRUD 테스트");
		System.out.println("---------------------------------------------------");



		System.out.println("2-1. Create Channel");
		//채널 3개 생성
		System.out.println("채널 3개 생성!\n");

		UUID [] channelArr = new UUID[3];

		List<UUID> userList1 = new ArrayList<>();
		userList1.add(userArr[0]);
		userList1.add(userArr[2]);

		channelArr[0] = channelService.createPublic(new CreatePublicChannelDto("신라면파", userArr[0],"신라면 이외의 라면은 취급하지 않는 사람들",userList1)).channelId();

		List<UUID> userList2 = new ArrayList<>();
		userList2.add(userArr[3]);
		userList2.add(userArr[4]);
		channelArr[1] = channelService.createPublic(new CreatePublicChannelDto("진라면파", userArr[3],"진라면 이외의 라면은 취급하지 않는 사람들",userList2)).channelId();

		List<UUID> userList3 = new ArrayList<>();
		userList3.add(userArr[0]);
		userList3.add(userArr[4]);
		channelArr[2] = channelService.createPrivate(new CreatePrivateChannelDto(userArr[0],userList3)).channelId();

		System.out.println("\n");


		System.out.println("2-2. Read Channel");

		System.out.println("2-2-1. Read Public Channel");
		System.out.println("진라면파 출력");
		System.out.println(channelService.findPublic(channelArr[1]));
		System.out.println("\n");

		System.out.println("2-2-2. Read Private Channel");
		System.out.println(channelService.findPrivate(channelArr[2],userArr[0]));
		System.out.println("\n");



		//채널 전체 출력
		System.out.println("\n유하나 전체 채널 출력\n");

		channelService.findAllById(userArr[0]).forEach(System.out::println);

		System.out.println("\n");

		System.out.println("2-3. Update Channel");

		System.out.println("2-3-1. add/remove Member");

		//채널 멤버 추가
		channelService.addMember(new ChannelMemberDto(userArr[4],channelArr[0]));

		System.out.println(channelService.findPublic(channelArr[0]));

		System.out.println("\n//////////////////\n");
		//채널 멤버 삭제
		channelService.removeMember(new ChannelMemberDto(userArr[2],channelArr[0]));
		System.out.println(channelService.findPublic(channelArr[0]));


		System.out.println("\n");

		UpdateChannelDto upchan = new UpdateChannelDto(channelArr[0], "육개장파", userArr[4], "육개장 좋아 최고로 좋아");

		//채널 업데이트
		System.out.println("2-3-2. Update Channel Name,Description,Owner");
		channelService.updateChannel(upchan);
		System.out.println(channelService.findPublic(channelArr[0]));
		System.out.println("\n");



		//채널 삭제
		System.out.println("2-4. Delete Channel");
		channelService.deleteChannel(channelArr[0]);
		channelService.findAllById(userArr[4]).forEach(System.out::println);

		System.out.println("\n");

		System.out.println("---------------------------------------------------");
		System.out.println("3. 메세지 CRUD 테스트");
		System.out.println("---------------------------------------------------");



		System.out.println("3-1. Create Message");



		messageService.create(new CreateMessageDto("안녕하세요",userArr[3],channelArr[1],null));
		messageService.create(new CreateMessageDto("안녕하세요~",userArr[4],channelArr[1],null));
		messageService.create(new CreateMessageDto("뭐 드셨어요",userArr[3],channelArr[1],null));
		messageService.create(new CreateMessageDto("진라면",userArr[3],channelArr[1],null));




		//모두 출력
		messageService.findAllById(channelArr[1]).forEach(System.out::println);
		System.out.println("\n");




		System.out.println("3-2. Read Message");

		// 메시지 아이디 받아와서 출력
		BinaryFile bf = new BinaryFile();
		List<BinaryFile> bfList = new ArrayList<>();
		bfList.add(bf);

		UUID mID1 = messageService.create(new CreateMessageDto("저도요",userArr[4],channelArr[1],bfList)).messageId();

		System.out.println(messageService.find(mID1));

		//전체 출력
		System.out.println("\n모든 진라면파 채널 메시지 출력!\n");
		messageService.findAllById(channelArr[1]).forEach(System.out::println);
		System.out.println("\n");


		System.out.println("3-3. Update Message");

		//메시지 내용 업데이트
		messageService.updateMessage(new UpdateMessageDto(mID1,"전 사실 신라면 먹었어요",null));
		System.out.println(messageService.find(mID1));

		System.out.println("\n");


		//메시지 삭제
		System.out.println("3-4. Delete Message");
		messageService.deleteMessage(mID1);
		messageService.findAllById(channelArr[1]).forEach(System.out::println);
		System.out.println("\n");


		System.out.println("---------------------------------------------------");
		System.out.println("4. 유저 스테이터스 CRUD 테스트");
		System.out.println("---------------------------------------------------");

		//create는 멤버 생성시 자동 생성, 임의로 생성 X

		System.out.println("4-1. read User State");

		System.out.println("4-1-1. read User Status");
		System.out.println(userStatusService.find(new CreateUserStatusDto(userArr[0])));
		System.out.println("4-1-2. read User Status List");
		userStatusService.findAll().forEach(System.out::println);

		System.out.println("4-2. Update User Status");

		userStatusService.update(new CreateUserStatusDto(userArr[3]));
		System.out.println(userStatusService.find(new CreateUserStatusDto(userArr[3])));

		//delete도 마찬가지

		System.out.println("---------------------------------------------------");
		System.out.println("5. 읽기 스테이터스 CRUD 테스트");
		System.out.println("---------------------------------------------------");

		//create, delete는 사용자 채널 참가, 나갈시 자동 생성 및 삭제

		System.out.println("5-1-1. read Channel Status");
		System.out.println(readStatusService.find(userArr[3],channelArr[1]));

		System.out.println("5-1-2. read Channel Status List");
		readStatusService.findAllById(userArr[4]).forEach(System.out::println);

		System.out.println("5-2. Update Channel Status");
		readStatusService.update(new CreateReadStatusDto(userArr[3],channelArr[1]));
		System.out.println(readStatusService.find(userArr[3],channelArr[1]));

		System.out.println("---------------------------------------------------");
		System.out.println("6. 읽기 스테이터스 CRUD 테스트");
		System.out.println("---------------------------------------------------");

		//create는 유저에서 프로필 or 메시지에서 추가할때 생성

		binaryContentService.findAll().forEach(System.out::println);


		System.out.println("---------------------------------------------------");
		System.out.println("7. AuthService 테스트");
		System.out.println("---------------------------------------------------");


		System.out.println(authService.login("뉴하나","hanakim1111"));















		//파일 정리
		try (var stream = Files.list(Path.of("src/main/resources/users/"))) {
			stream.sorted(Comparator.reverseOrder())
					.map(Path::toFile)
					.forEach(File::delete);



		} catch (IOException e) {
			throw new RuntimeException("삭제 중 오류 발생", e);

		}

		try (var stream = Files.list(Path.of("src/main/resources/Channels/"))) {
			stream.sorted(Comparator.reverseOrder())
					.map(Path::toFile)
					.forEach(File::delete);



		} catch (IOException e) {
			throw new RuntimeException("삭제 중 오류 발생", e);

		}
		try (var stream = Files.list(Path.of("src/main/resources/Messages/"))) {
			stream.sorted(Comparator.reverseOrder())
					.map(Path::toFile)
					.forEach(File::delete);



		} catch (IOException e) {
			throw new RuntimeException("삭제 중 오류 발생", e);

		}

	}

}
