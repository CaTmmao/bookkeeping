package com.mydomain.accounting.dao;

import com.mydomain.accounting.model.persistence.TagPersistenceModel;

public interface TagDao {
    void createTag(TagPersistenceModel tag);
    TagPersistenceModel getTagByTagDescription(String description);
    TagPersistenceModel getTagByTagId(Long id);
    void updateTag(TagPersistenceModel tag);
}
