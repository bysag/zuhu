package com.zuhu.system.pojo;

import java.io.Serializable;

public class PoJo implements Serializable {

    private static final long serialVersionUID = 1830074364836380436L;

    private int limit;

    private int offset;

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

}
