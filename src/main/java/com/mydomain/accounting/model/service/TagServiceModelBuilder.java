package com.mydomain.accounting.model.service;

public final class TagServiceModelBuilder {
    Long id;
    String status;
    Long userId;
    String description;

    public TagServiceModelBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public TagServiceModelBuilder status(String status) {
        this.status = status;
        return this;
    }

    public TagServiceModelBuilder userId(Long userId) {
        this.userId = userId;
        return this;
    }

    public TagServiceModelBuilder description(String description) {
        this.description = description;
        return this;
    }

    public TagServiceModel build() {
        TagServiceModel tagServiceModel = new TagServiceModel();
        tagServiceModel.setId(id);
        tagServiceModel.setStatus(status);
        tagServiceModel.setUserId(userId);
        tagServiceModel.setDescription(description);
        return tagServiceModel;
    }
}
