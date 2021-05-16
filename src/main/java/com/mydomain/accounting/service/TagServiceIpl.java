package com.mydomain.accounting.service;

import java.util.Optional;

import com.mydomain.accounting.converter.persistenceToCommon.TagP2CConverter;
import com.mydomain.accounting.dao.TagDao;
import com.mydomain.accounting.exception.InvalidParameterException;
import com.mydomain.accounting.exception.ResourceNotFoundException;
import com.mydomain.accounting.model.common.TagCommonModel;
import com.mydomain.accounting.model.persistence.TagPersistenceModel;
import com.mydomain.accounting.model.persistence.TagPersistenceModelBuilder;
import org.springframework.stereotype.Service;

@Service
public class TagServiceIpl implements TagService {
    private static final int ENABLE_STATUS = 1;
    private final TagDao tagDao;
    private final TagP2CConverter tagP2CConverter;

    public TagServiceIpl(TagDao tagDao,
                         TagP2CConverter tagP2CConverter) {
        this.tagDao = tagDao;
        this.tagP2CConverter = tagP2CConverter;
    }

    @Override
    public TagCommonModel createTag(String description, Long userId) {
        Optional.ofNullable(tagDao.getTagByTagDescription(description))
            .ifPresent((tag) -> {
                throw new InvalidParameterException("已经存在该标签");
            });

        TagPersistenceModel newTag = new TagPersistenceModelBuilder()
            .description(description)
            .userId(userId)
            .status(ENABLE_STATUS)
            .build();

        tagDao.createTag(newTag);
        return tagP2CConverter.convert(newTag);
    }

    @Override
    public TagCommonModel updateTag(TagCommonModel tag) {
        TagCommonModel tagDB = getTagByTagId(tag.getId());

        if (!tag.getUserId().equals(tagDB.getUserId())) {
            throw new InvalidParameterException("无法修改，该标签不是当前用户创建");
        }

        tagDao.updateTag(tagP2CConverter.reverse().convert(tag));
        return getTagByTagId(tag.getId());
    }

    @Override
    public TagCommonModel getTagByTagId(Long id) {
        return Optional.ofNullable(tagDao.getTagByTagId(id))
            .map(tagP2CConverter::convert)
            .orElseThrow(() -> new ResourceNotFoundException("没有该标签"));
    }
}
