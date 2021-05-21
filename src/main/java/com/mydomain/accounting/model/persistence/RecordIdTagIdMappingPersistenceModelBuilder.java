package com.mydomain.accounting.model.persistence;

import java.time.Instant;

public final class RecordIdTagIdMappingPersistenceModelBuilder {
    Long id;
    Long recordId;
    Long tagId;
    Integer status;
    Instant createTime;
    Instant updateTime;

    private RecordIdTagIdMappingPersistenceModelBuilder() {
    }

    public static RecordIdTagIdMappingPersistenceModelBuilder builder() {
        return new RecordIdTagIdMappingPersistenceModelBuilder();
    }

    public RecordIdTagIdMappingPersistenceModelBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public RecordIdTagIdMappingPersistenceModelBuilder recordId(Long recordId) {
        this.recordId = recordId;
        return this;
    }

    public RecordIdTagIdMappingPersistenceModelBuilder tagId(Long tagId) {
        this.tagId = tagId;
        return this;
    }

    public RecordIdTagIdMappingPersistenceModelBuilder status(Integer status) {
        this.status = status;
        return this;
    }

    public RecordIdTagIdMappingPersistenceModelBuilder createTime(Instant createTime) {
        this.createTime = createTime;
        return this;
    }

    public RecordIdTagIdMappingPersistenceModelBuilder updateTime(Instant updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public RecordIdTagIdMappingPersistenceModel build() {
        RecordIdTagIdMappingPersistenceModel recordIdTagIdMappingPersistenceModel =
            new RecordIdTagIdMappingPersistenceModel();
        recordIdTagIdMappingPersistenceModel.setId(id);
        recordIdTagIdMappingPersistenceModel.setRecordId(recordId);
        recordIdTagIdMappingPersistenceModel.setTagId(tagId);
        recordIdTagIdMappingPersistenceModel.setStatus(status);
        recordIdTagIdMappingPersistenceModel.setCreateTime(createTime);
        recordIdTagIdMappingPersistenceModel.setUpdateTime(updateTime);
        return recordIdTagIdMappingPersistenceModel;
    }
}
