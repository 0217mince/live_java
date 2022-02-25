package com.ke.live.utils;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 小凡
 */
public class QueryResult<T> implements Serializable {
    private static final long serialVersionUID = 6195741402452890231L;
    private long totalCount;
    private long start;
    private int limit;
    private List<T> records;
    private Map<String, Object> properties;

    public QueryResult() {
    }

    public QueryResult(int start, int limit) {
        this.start = (long)start;
        this.limit = limit;
    }

    public QueryResult(long totalCount, int start, int limit, List<T> records) {
        this.totalCount = totalCount;
        this.start = (long)start;
        this.limit = limit;
        this.records = records;
    }

    public void setTotal(long totalCount) {
        this.totalCount = totalCount;
    }

    public long getTotal() {
        return this.totalCount;
    }

    public long getStart() {
        return this.start;
    }

    public long getLimit() {
        return (long)this.limit;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public boolean hasNext() {
        return this.start + (long)this.limit < this.totalCount;
    }

    public void setItems(List<T> records) {
        this.records = records;
    }

    public List<T> getItems() {
        return this.records == null ? Collections.emptyList() : this.records;
    }

    public void setProperty(String nm, Object v) {
        if (this.properties == null) {
            this.properties = new HashMap();
        }

        this.properties.put(nm, v);
    }

    public Object getProperty(String nm) {
        return this.properties == null ? null : this.properties.get(nm);
    }

    public Map<String, Object> getProperties() {
        return this.properties;
    }
}
