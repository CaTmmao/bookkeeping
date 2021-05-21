package com.mydomain.accounting.converter.persistenceToCommon;

import java.util.List;

import com.google.common.base.Converter;
import com.google.common.collect.ImmutableList;
import com.mydomain.accounting.model.common.RecordCommonModel;
import com.mydomain.accounting.model.common.RecordCommonModelBuilder;
import com.mydomain.accounting.model.common.TagCommonModel;
import com.mydomain.accounting.model.persistence.RecordPersistenceModel;
import com.mydomain.accounting.model.persistence.RecordPersistenceModelBuilder;
import com.mydomain.accounting.model.persistence.TagPersistenceModel;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.stereotype.Service;

@Service
public class RecordP2CConverter extends Converter<RecordPersistenceModel, RecordCommonModel> {
    private static final String INCOME = "INCOME";
    private static final String OUTCOME = "OUTCOME";
    private static final String DISABLE_STATUS = "DISABLE";
    private static final String ENABLE_STATUS = "ENABLE";
    private final TagP2CConverter tagP2CConverter = new TagP2CConverter();

    @Override
    protected RecordCommonModel doForward(RecordPersistenceModel record) {
        List<TagCommonModel> tagList =
            ImmutableList.copyOf(tagP2CConverter.convertAll(record.getTagList()));

        return RecordCommonModelBuilder.builder()
            .userId(record.getUserId())
            .id(record.getId())
            .note(record.getNote())
            .category(record.getCategory() == 1 ? INCOME : OUTCOME)
            .status(record.getStatus() == 1 ? ENABLE_STATUS : DISABLE_STATUS)
            .amount(record.getAmount())
            .tagList(tagList)
            .build();
    }

    @Override
    protected RecordPersistenceModel doBackward(RecordCommonModel record) {
        List<TagPersistenceModel> tagList = ImmutableList.copyOf(
            tagP2CConverter.reverse().convertAll(record.getTagList()));

        return RecordPersistenceModelBuilder.builder()
            .id(record.getId())
            .amount(record.getAmount())
            .category(INCOME.equals(record.getCategory()) ? 1 : 0)
            .note(record.getNote())
            .status(ENABLE_STATUS.equals(record.getStatus()) ? 1 : 0)
            .tagList(tagList)
            .userId(record.getUserId())
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
