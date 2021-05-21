package com.mydomain.accounting.controller;

import com.github.pagehelper.PageInfo;
import com.mydomain.accounting.converter.commonToService.TagC2SConverter;
import com.mydomain.accounting.exception.InvalidParameterException;
import com.mydomain.accounting.model.common.TagCommonModel;
import com.mydomain.accounting.model.service.TagServiceModel;
import com.mydomain.accounting.service.TagService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1.0/tags")
public class TagController {
    private static final String ENABLE_STATUS = "ENABLE";
    private static final String DISABLE_STATUS = "DISABLE";
    private final TagService tagService;
    private final TagC2SConverter tagC2SConverter;

    public TagController(TagService tagService,
                         TagC2SConverter tagC2SConverter) {
        this.tagService = tagService;
        this.tagC2SConverter = tagC2SConverter;
    }

    /**
     * 通过标签id获取标签信息
     *
     * @param id 标签id
     * @return 标签信息
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity<TagServiceModel> getTagByTagId(@PathVariable Long id) {
        if (id == null || id <= 0L) {
            throw new InvalidParameterException(String.format("未传 id 变量，并且 id 的值需要大于 0,当前 id 值为 %s", id));
        }

        TagCommonModel resource = tagService.getTagByTagId(id);
        return ResponseEntity.ok(tagC2SConverter.convert(resource));
    }

    /**
     * 获取当前用户所有标签
     *
     * @param userId   用户 id
     * @param pageNum  获取第几页
     * @param pageSize 每页页数
     * @return 标签列表
     */
    @GetMapping
    public PageInfo<TagCommonModel> getTagsByUserId(@RequestParam("userId") Long userId,
                                                    @RequestParam("pageNum") int pageNum,
                                                    @RequestParam("pageSize") int pageSize) {
        if (userId == null || pageNum == 0 || pageSize == 0) {
            throw new InvalidParameterException("需要传入 userId, pageNum, pageSize 参数");
        }

        return tagService.getTagsByUserId(userId, pageNum, pageSize);
    }

    /**
     * 创建标签
     *
     * @param tag 标签相关信息
     * @return 新创建的标签信息
     */
    @PostMapping
    public ResponseEntity<TagServiceModel> createTag(@RequestBody TagServiceModel tag) {
        String description = tag.getDescription();
        Long userId = tag.getUserId();

        if (description == null || description.isEmpty() || userId == null) {
            throw new InvalidParameterException("必须传入 description, userId");
        }

        TagCommonModel resource = tagService.createTag(description, userId);
        return ResponseEntity.ok(tagC2SConverter.convert(resource));
    }

    /**
     * 更新标签信息
     *
     * @param tag 标签信息
     * @return 更新后的标签信息
     */
    @PutMapping
    public ResponseEntity<TagServiceModel> updateTag(@RequestBody TagServiceModel tag) {
        Long id = tag.getId();
        String status = tag.getStatus();
        Long userId = tag.getUserId();

        if (userId == null || id == null || id <= 0L || userId <= 0L) {
            throw new InvalidParameterException("需要至少传入 id， userId 参数。并且 id 与 userId 的值应大于 0");
        }

        if (status != null && !ENABLE_STATUS.equals(status) && !DISABLE_STATUS.equals(status)) {
            throw new InvalidParameterException("status 值只能是 'DISABLE' 或 'ENABLE'");
        }

        TagCommonModel resource = tagService.updateTag(tagC2SConverter.reverse().convert(tag));
        return ResponseEntity.ok(tagC2SConverter.convert(resource));
    }
}
