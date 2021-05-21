package com.mydomain.accounting.converter.commonToService;

import com.google.common.base.Converter;
import com.mydomain.accounting.model.common.TagCommonModel;
import com.mydomain.accounting.model.common.TagCommonModelBuilder;
import com.mydomain.accounting.model.service.TagServiceModel;
import com.mydomain.accounting.model.service.TagServiceModelBuilder;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.stereotype.Service;

@Service
public class TagC2SConverter extends Converter<TagCommonModel, TagServiceModel> {
    @Override
    protected TagServiceModel doForward(TagCommonModel tag) {
        return new TagServiceModelBuilder()
            .id(tag.getId())
            .description(tag.getDescription())
            .status(tag.getStatus())
            .userId(tag.getUserId())
            .build();
    }

    @Override
    protected TagCommonModel doBackward(TagServiceModel tag) {
        return new TagCommonModelBuilder()
            .id(tag.getId())
            .description(tag.getDescription())
            .status(tag.getStatus())
            .userId(tag.getUserId())
            .build();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(@Nullable Object object) {
        return super.equals(object);
    }
}
