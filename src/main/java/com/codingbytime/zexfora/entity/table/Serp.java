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
public class Serp extends BaseObject{

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "website_id")
    private Website website;

    private String keyword;

    private Long rank;

    private boolean isCrawled;
}
