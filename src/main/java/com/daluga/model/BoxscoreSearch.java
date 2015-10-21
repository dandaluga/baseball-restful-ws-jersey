package com.daluga.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.List;

@XmlRootElement
public class BoxscoreSearch {

    private Date fromDate;
    private Date toDate;
    private List<String> name;

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public List<String> getName() {
        return name;
    }

    public void setName(List<String> name) {
        this.name = name;
    }

    private BoxscoreSearchType searchType;

    public BoxscoreSearchType getSearchType() {
        return searchType;
    }

    public void setSearchType(BoxscoreSearchType searchType) {

        this.searchType = searchType;
    }
}
