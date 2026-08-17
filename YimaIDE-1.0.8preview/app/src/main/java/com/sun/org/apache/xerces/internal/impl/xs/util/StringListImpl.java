package com.sun.org.apache.xerces.internal.impl.xs.util;

import com.sun.org.apache.xerces.internal.xs.StringList;
import java.lang.reflect.Array;
import java.util.AbstractList;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class StringListImpl extends AbstractList<String> implements StringList {
    public static final StringListImpl EMPTY_LIST = new StringListImpl(new String[0], 0);
    private final String[] fArray;
    private final int fLength;
    private final List<String> fVector;

    public StringListImpl(List<String> list) {
        this.fVector = list;
        this.fLength = list == null ? 0 : list.size();
        this.fArray = null;
    }

    private void toArray0(Object[] objArr) {
        int i = this.fLength;
        if (i > 0) {
            System.arraycopy(this.fArray, 0, objArr, 0, i);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xs.StringList
    public boolean contains(String str) {
        List<String> list = this.fVector;
        if (list != null) {
            return list.contains(str);
        }
        if (str == null) {
            for (int i = 0; i < this.fLength; i++) {
                if (this.fArray[i] == null) {
                    return true;
                }
            }
        } else {
            for (int i2 = 0; i2 < this.fLength; i2++) {
                if (str.equals(this.fArray[i2])) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // java.util.AbstractList, java.util.List
    public String get(int i) {
        if (i < 0 || i >= this.fLength) {
            b1e.a("Index: ", i);
            return null;
        }
        List<String> list = this.fVector;
        return list != null ? list.get(i) : this.fArray[i];
    }

    @Override // com.sun.org.apache.xerces.internal.xs.StringList
    public int getLength() {
        return this.fLength;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.StringList
    public String item(int i) {
        if (i < 0 || i >= this.fLength) {
            return null;
        }
        List<String> list = this.fVector;
        return list != null ? list.get(i) : this.fArray[i];
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return getLength();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public Object[] toArray(Object[] objArr) {
        List<String> list = this.fVector;
        if (list != null) {
            return list.toArray(objArr);
        }
        if (objArr.length < this.fLength) {
            objArr = (Object[]) Array.newInstance(objArr.getClass().getComponentType(), this.fLength);
        }
        toArray0(objArr);
        int length = objArr.length;
        int i = this.fLength;
        if (length > i) {
            objArr[i] = null;
        }
        return objArr;
    }

    public StringListImpl(String[] strArr, int i) {
        this.fArray = strArr;
        this.fLength = i;
        this.fVector = null;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public Object[] toArray() {
        List<String> list = this.fVector;
        if (list != null) {
            return list.toArray();
        }
        Object[] objArr = new Object[this.fLength];
        toArray0(objArr);
        return objArr;
    }
}
