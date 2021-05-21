package com.mydomain.accounting.service;

import com.github.pagehelper.PageInfo;
import com.mydomain.accounting.model.common.TagCommonModel;

public interface TagService {
    TagCommonModel createTag(String description, Long userId);
    TagCommonModel updateTag(TagCommonModel tag);
    TagCommonModel getTagByTagId(Long id);
    PageInfo<TagCommonModel> getTagsByUserId(Long userId, int pageNum, int pageSize);
}
