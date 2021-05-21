package com.mydomain.accounting.controller;

import com.mydomain.accounting.converter.commonToService.RecordC2SConverter;
import com.mydomain.accounting.exception.InvalidParameterException;
import com.mydomain.accounting.model.common.RecordCommonModel;
import com.mydomain.accounting.model.service.RecordServiceModel;
import com.mydomain.accounting.service.RecordService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1.0/records")
public class RecordController {
    private final RecordC2SConverter recordC2SConverter;
    private final RecordService recordService;

    public RecordController(RecordC2SConverter recordC2SConverter,
                            RecordService recordService) {
        this.recordC2SConverter = recordC2SConverter;
        this.recordService = recordService;
    }

    /**
     * 检查 record 对象是否有 null 值的变量
     *
     * @param record record 对象
     * @return 如有值为 null 的变量返回 false
     */
    public boolean checkRecordParamIfAnyNull(RecordServiceModel record) {
        return record.getUserId() == null
            || record.getAmount() == null
            || record.getCategory() == null
            || record.getNote() == null
            || record.getTagList() == null
            || record.getTagList().isEmpty();
    }

    @PostMapping
    public RecordServiceModel createRecord(@RequestBody RecordServiceModel record) {
        if (checkRecordParamIfAnyNull(record)) {
            throw new InvalidParameterException("参数不全,需要传递 userId, amount, category, note, tagList");
        }

        RecordCommonModel recordCommon = recordC2SConverter.reverse().convert(record);
        RecordCommonModel resource = recordService.createRecord(recordCommon);
        return recordC2SConverter.convert(resource);
    }

    @GetMapping(path = "/{id}")
    public RecordServiceModel getRecordByRecordId(@PathVariable Long id) {
        RecordCommonModel resource = recordService.getRecordByRecordId(id);
        return recordC2SConverter.convert(resource);
    }

    @PutMapping
    public RecordServiceModel updateRecord(@RequestBody RecordServiceModel record) {
        Long userId = record.getUserId();
        Long id = record.getId();
        if (userId == null || userId <= 0L || id == null || id <= 0L) {
            throw new InvalidParameterException(
                String.format("参数至少传入 id，userId，并且值需要大于 0,当前 id 值为 %s, userId 值为 %s", id, userId));
        }

        RecordCommonModel resource = recordService.updateRecord(
            recordC2SConverter.reverse().convert(record));

        return recordC2SConverter.convert(resource);
    }
}
