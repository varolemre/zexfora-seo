package com.codingbytime.zexfora.entity.table;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Page extends BaseObject {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "website_id", referencedColumnName = "id")
    private Website website;

    @OneToMany(mappedBy = "page")
    private Set<StrongKeyword> strongKeywords = new HashSet<>();
}
