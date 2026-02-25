package com.sprint.mission.discodeit.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

public abstract class Entity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private final UUID id;
    private final Long createdAt;
    private Long updatedAt;




    public Entity() {

        id = UUID.randomUUID();
        createdAt = System.currentTimeMillis();
        updatedAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public Long getUpdatedAt() {
        return updatedAt;
    }

    public void updateUpdatedAt(){

        updatedAt   = System.currentTimeMillis();

    }


    public int compareTo(Entity entity) {
        return this.createdAt.compareTo(entity.getCreatedAt());
    }
}
