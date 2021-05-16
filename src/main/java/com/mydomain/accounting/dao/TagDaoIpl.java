package com.mydomain.accounting.dao;

import com.mydomain.accounting.dao.mapper.TagMapper;
import com.mydomain.accounting.model.persistence.TagPersistenceModel;
import org.springframework.stereotype.Repository;

@Repository
public class TagDaoIpl implements TagDao {
    private final TagMapper tagMapper;

    public TagDaoIpl(TagMapper tagMapper) {
        this.tagMapper = tagMapper;
    }

    @Override
    public void createTag(TagPersistenceModel tag) {
        tagMapper.createTag(tag);
    }

    @Override
    public TagPersistenceModel getTagByTagId(Long id) {
        return tagMapper.getTagByTagId(id);
    }

    @Override
    public TagPersistenceModel getTagByTagDescription(String description) {
        return tagMapper.getTagByTagDescription(description);
    }

    @Override
    public void updateTag(TagPersistenceModel tag) {
        tagMapper.updateTag(tag);
    }
}
