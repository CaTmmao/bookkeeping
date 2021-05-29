package com.mydomain.accounting.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.github.pagehelper.PageInfo;
import com.mydomain.accounting.converter.persistenceToCommon.TagP2CConverter;
import com.mydomain.accounting.dao.TagDao;
import com.mydomain.accounting.exception.InvalidParameterException;
import com.mydomain.accounting.exception.ResourceNotFoundException;
import com.mydomain.accounting.model.common.TagCommonModel;
import com.mydomain.accounting.model.persistence.TagPersistenceModel;
import com.mydomain.accounting.model.persistence.TagPersistenceModelBuilder;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
                throw new InvalidParameterException(String.format("已经存在 description 为 %s 的标签", description));
            });

        TagPersistenceModel newTag = new TagPersistenceModelBuilder()
            .description(description)
            .userId(userId)
            .status(ENABLE_STATUS)
            .build();

        tagDao.createTag(newTag);
        return tagP2CConverter.convert(newTag);
    }

    @CacheEvict(cacheNames = "tag", key = "#tag.id")
    @Override
    public TagCommonModel updateTag(TagCommonModel tag) {
        Long tagId = tag.getId();
        TagCommonModel tagDB = getTagByTagId(tagId);

        Long tagUserId = tag.getUserId();
        if (!tagUserId.equals(tagDB.getUserId())) {
            throw new InvalidParameterException(
                String.format("无法修改，id 为 %s 的标签不是 userIf 为 %s 的用户创建的", tagId, tagUserId));
        }

        tagDao.updateTag(tagP2CConverter.reverse().convert(tag));
        return getTagByTagId(tag.getId());
    }

    @Cacheable(cacheNames = "tag", key = "#id")
    @Override
    public TagCommonModel getTagByTagId(Long id) {
        return Optional.ofNullable(tagDao.getTagByTagId(id))
            .map(tagP2CConverter::convert)
            .orElseThrow(() -> new ResourceNotFoundException(String.format("id 为 %s 的标签不存在", id)));
    }

    @Cacheable(cacheNames = "tagList", key = "#userId")
    @Override
    public PageInfo<TagCommonModel> getTagsByUserId(Long userId, int pageNum, int pageSize) {
        List<TagPersistenceModel> tagList = tagDao.getTagListByUserId(userId, pageNum, pageSize);

        List<TagCommonModel> list = new ArrayList<>();
        tagP2CConverter.convertAll(tagList)
            .forEach(list::add);

        return new PageInfo<>(list);
    }
}
