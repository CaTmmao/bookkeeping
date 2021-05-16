package com.mydomain.accounting.model.common;

public final class TagCommonModelBuilder {
    Long id;
    String description;
    String status;
    Long userId;

    public TagCommonModelBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public TagCommonModelBuilder description(String description) {
        this.description = description;
        return this;
    }

    public TagCommonModelBuilder status(String status) {
        this.status = status;
        return this;
    }

    public TagCommonModelBuilder userId(Long userId) {
        this.userId = userId;
        return this;
    }

    public TagCommonModel build() {
        TagCommonModel tagCommonModel = new TagCommonModel();
        tagCommonModel.setId(id);
        tagCommonModel.setDescription(description);
        tagCommonModel.setStatus(status);
        tagCommonModel.setUserId(userId);
        return tagCommonModel;
    }
}
