package com.mydomain.accounting.converter.commonToService;

import java.util.LinkedList;
import java.util.List;

import com.google.common.base.Converter;
import com.google.common.collect.ImmutableList;
import com.mydomain.accounting.model.common.RecordCommonModel;
import com.mydomain.accounting.model.common.RecordCommonModelBuilder;
import com.mydomain.accounting.model.common.TagCommonModel;
import com.mydomain.accounting.model.service.RecordServiceModel;
import com.mydomain.accounting.model.service.RecordServiceModelBuilder;
import com.mydomain.accounting.model.service.TagServiceModel;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.stereotype.Service;

@Service
public class RecordC2SConverter extends Converter<RecordCommonModel, RecordServiceModel> {
    private final TagC2SConverter tagC2SConverter = new TagC2SConverter();

    @Override
    protected RecordServiceModel doForward(RecordCommonModel record) {
        List<TagServiceModel> tagList =
            ImmutableList.copyOf(tagC2SConverter.convertAll(record.getTagList()));

        return RecordServiceModelBuilder.builder()
            .id(record.getId())
            .amount(record.getAmount())
            .category(record.getCategory())
            .note(record.getNote())
            .tagList(tagList)
            .userId(record.getUserId())
            .build();
    }

    @Override
    protected RecordCommonModel doBackward(RecordServiceModel record) {
        List<TagCommonModel> tagList = new LinkedList<>();
        if (record.getTagList() != null) {
            tagList = ImmutableList
                .copyOf(tagC2SConverter.reverse().convertAll(record.getTagList()));
        }


        return RecordCommonModelBuilder.builder()
            .id(record.getId())
            .amount(record.getAmount())
            .category(record.getCategory())
            .note(record.getNote())
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
