package com.mydomain.accounting.converter.persistenceToCommon;

import com.google.common.base.Converter;
import com.mydomain.accounting.model.common.TagCommonModel;
import com.mydomain.accounting.model.common.TagCommonModelBuilder;
import com.mydomain.accounting.model.persistence.TagPersistenceModel;
import com.mydomain.accounting.model.persistence.TagPersistenceModelBuilder;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.stereotype.Service;

@Service
public class TagP2CConverter extends Converter<TagPersistenceModel, TagCommonModel> {
    private static final String DISABLE_STATUS = "DISABLE";

    @Override
    protected TagCommonModel doForward(TagPersistenceModel tagPersistenceModel) {
        return new TagCommonModelBuilder()
            .id(tagPersistenceModel.getId())
            .userId(tagPersistenceModel.getUserId())
            .status(tagPersistenceModel.getStatus() == 1 ? "ENABLE" : "DISABLE")
            .description(tagPersistenceModel.getDescription())
            .build();
    }

    @Override
    protected TagPersistenceModel doBackward(TagCommonModel tagCommonModel) {
        int status;

        if (tagCommonModel.getStatus() == null || DISABLE_STATUS.equals(tagCommonModel.getStatus())) {
            status = 1;
        } else {
            status = 0;
        }

        TagPersistenceModel result = new TagPersistenceModelBuilder()
            .id(tagCommonModel.getId())
            .description(tagCommonModel.getDescription())
            .userId(tagCommonModel.getUserId())
            .status(status)
            .build();

        return result;
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
