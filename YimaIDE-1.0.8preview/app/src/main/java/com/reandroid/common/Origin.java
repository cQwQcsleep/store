package com.reandroid.common;

import com.reandroid.utils.StringsUtil;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Origin {
    private final Object name;
    private Origin parent;
    private Object separator = ":";

    public Origin(Origin origin, Object obj) {
        this.parent = origin;
        this.name = obj;
    }

    public static Origin createNew(Object obj) {
        return newRoot().createChild(obj);
    }

    public static Origin newRoot() {
        return new Origin(null, StringsUtil.EMPTY);
    }

    public Origin createChild(Object obj) {
        return new Origin(this, obj);
    }

    public Object getName() {
        return this.name;
    }

    public Origin getParent() {
        return this.parent;
    }

    public Object getSeparator() {
        Object obj = this.separator;
        return obj == null ? StringsUtil.EMPTY : obj;
    }

    public boolean isRoot() {
        return getParent() == null;
    }

    public void setParent(Origin origin) {
        this.parent = origin;
    }

    public void setSeparator(Object obj) {
        this.separator = obj;
    }

    public Origin[] toArray() {
        int i = 0;
        for (Origin parent = this; !parent.isRoot(); parent = parent.getParent()) {
            i++;
        }
        Origin[] originArr = new Origin[i];
        for (int i2 = i - 1; i2 >= 0; i2--) {
            originArr[i2] = this;
            this = this.getParent();
        }
        return originArr;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Origin[] array = toArray();
        int length = array.length;
        for (int i = 0; i < length; i++) {
            Origin origin = array[i];
            if (i != 0) {
                sb.append(origin.getSeparator());
            }
            sb.append(origin.getName());
        }
        return sb.toString();
    }
}
