package com.codingbytime.zexfora.entity.table;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SeoReport extends BaseObject {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "website_id", referencedColumnName = "id")
    private Website website;

    @Column(name = "H1")
    private boolean h1tag;
    private boolean h2tag;
    private boolean mediumCss;
    private boolean mediumJs;
    private boolean metaDescription;

    @Column(name = "title")
    @Size(max = 155)
    private String title;

    private boolean crawlability;
    private boolean localLanguage;
    private boolean urlSchema;
    private boolean charset;
    private boolean doctype;
    private boolean favicon;
    private boolean robotstxt;
    private Integer externalLinks;
    private boolean mobileOptimization;
    private boolean isSsl;
    private boolean gzip;
    private boolean blackList;
    private int indexCount;
    private boolean siteMap;
    private boolean imageAlt;
}
