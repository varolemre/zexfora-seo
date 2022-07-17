package com.codingbytime.zexfora.entity.table;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Keyword {

    @Id
    private Long id;

    private String keyword;

    @ManyToMany(mappedBy = "keywordList")
    private Set<Domain> domainSet = new HashSet<>();
}
