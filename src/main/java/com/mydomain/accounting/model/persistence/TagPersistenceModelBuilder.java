package com.mydomain.accounting.model.persistence;

import java.time.Instant;

public final class TagPersistenceModelBuilder {
    Long id;
    String description;
    Integer status;
    Long userId;
    Instant createTime;
    Instant updateTime;

    public TagPersistenceModelBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public TagPersistenceModelBuilder description(String description) {
        this.description = description;
        return this;
    }

    public TagPersistenceModelBuilder status(Integer status) {
        this.status = status;
        return this;
    }

    public TagPersistenceModelBuilder userId(Long userId) {
        this.userId = userId;
        return this;
    }

    public TagPersistenceModelBuilder createTime(Instant createTime) {
        this.createTime = createTime;
        return this;
    }

    public TagPersistenceModelBuilder updateTime(Instant updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public TagPersistenceModel build() {
        TagPersistenceModel tagPersistenceModel = new TagPersistenceModel();
        tagPersistenceModel.setId(id);
        tagPersistenceModel.setDescription(description);
        tagPersistenceModel.setStatus(status);
        tagPersistenceModel.setUserId(userId);
        tagPersistenceModel.setCreateTime(createTime);
        tagPersistenceModel.setUpdateTime(updateTime);
        return tagPersistenceModel;
    }
}
