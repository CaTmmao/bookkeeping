package com.mydomain.accounting.model.common;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/* 需要实现序列化是因为缓存在 redis　中时需要对对象序列化, redis 中存储的都是序列化后的内容，
 以及从 redis 中获取的数据会被 redis 进行反序列化恢复为我们需要的对象类型*/
public class RecordCommonModel implements Serializable {
    Long id;
    Long userId;
    BigDecimal amount;
    String note;
    String category;
    String status;
    List<TagCommonModel> tagList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<TagCommonModel> getTagList() {
        return tagList;
    }

    public void setTagList(List<TagCommonModel> tagList) {
        this.tagList = tagList;
    }
}
