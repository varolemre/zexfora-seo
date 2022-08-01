package com.codingbytime.zexfora.entity.table;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Keyword extends BaseObject{

    private String keyword;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "website_id")
    private Website website;
}
