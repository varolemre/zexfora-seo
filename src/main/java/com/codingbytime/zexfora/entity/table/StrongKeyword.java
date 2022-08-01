package com.codingbytime.zexfora.entity.table;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class StrongKeyword extends BaseObject {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "page_id")
    private Page page;

    private String strongKeyword;
}
