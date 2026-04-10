package com.sprint.mission.discodeit.entity;


import com.sprint.mission.discodeit.entity.base.BaseUpdatableEntity;
import lombok.Getter;
import java.util.UUID;

@Getter
public class User extends BaseUpdatableEntity {


  private String username;
  private String password;
  private String email;
  private BinaryContent profile;
  private final UserStatus status;

  public User(UserStatus status, BinaryContent profile, String email, String password,
      String username) {
    this.status = status;
    this.profile = profile;
    this.email = email;
    this.password = password;
    this.username = username;
  }

  public void updateUsername(String username) {
    this.username = username;
  }

  public void updatePassword(String password) {
    this.password = password;
  }

  public void updateEmail(String email) {
    this.email = email;
  }

  public void updateProfile(BinaryContent profile) {
    this.profile = profile;
  }


}
