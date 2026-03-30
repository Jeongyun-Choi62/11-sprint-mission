package com.sprint.mission.discodeit.repository.file;


import com.sprint.mission.discodeit.entity.Entity;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FileSaveLoadFactory {

  // 팩토리가 스프링으로부터 유일한 LockProvider 빈을 받아서 독박을 씁니다.
  private FileLockProvider fileLockProvider;

  // Service 클래스들이 호출할 생성 메서드
  public <T extends Entity> FileSaveLoad<T> createSaveLoad() {
    // 여기서 팩토리가 조립을 대신 해줍니다!
    return new FileSaveLoad<T>(fileLockProvider);
  }
}