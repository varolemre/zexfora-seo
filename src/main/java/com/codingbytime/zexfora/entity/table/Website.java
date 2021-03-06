package com.codingbytime.zexfora.entity.table;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Website extends BaseObject {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user")
    private User user;

    @OneToOne(mappedBy = "website")
    private SeoReport seoReport;

    @OneToMany(mappedBy = "website")
    private Set<Serp> serps = new HashSet<>();

    @OneToMany(mappedBy = "website")
    private Set<Page> pages = new HashSet<>();

    @OneToMany(mappedBy = "website")
    private Set<CrawledKeywords> crawledKeywords = new HashSet<>();

    @OneToMany(mappedBy = "website")
    private Set<Keyword> keywords = new HashSet<>();

}
