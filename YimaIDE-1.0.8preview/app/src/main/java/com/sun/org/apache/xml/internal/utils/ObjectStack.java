package com.sun.org.apache.xml.internal.utils;

import java.util.EmptyStackException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ObjectStack extends ObjectVector {
    public ObjectStack() {
    }

    @Override // com.sun.org.apache.xml.internal.utils.ObjectVector
    public Object clone() throws CloneNotSupportedException {
        return (ObjectStack) super.clone();
    }

    public boolean empty() {
        return this.m_firstFree == 0;
    }

    public Object peek(int i) {
        try {
            return this.m_map[this.m_firstFree - (i + 1)];
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new EmptyStackException();
        }
    }

    public Object pop() {
        Object[] objArr = this.m_map;
        int i = this.m_firstFree - 1;
        this.m_firstFree = i;
        Object obj = objArr[i];
        objArr[i] = null;
        return obj;
    }

    public Object push(Object obj) {
        int i = this.m_firstFree;
        int i2 = i + 1;
        int i3 = this.m_mapSize;
        if (i2 >= i3) {
            int i4 = i3 + this.m_blocksize;
            this.m_mapSize = i4;
            Object[] objArr = new Object[i4];
            System.arraycopy(this.m_map, 0, objArr, 0, i + 1);
            this.m_map = objArr;
        }
        Object[] objArr2 = this.m_map;
        int i5 = this.m_firstFree;
        objArr2[i5] = obj;
        this.m_firstFree = i5 + 1;
        return obj;
    }

    public void quickPop(int i) {
        this.m_firstFree -= i;
    }

    public int search(Object obj) {
        int iLastIndexOf = lastIndexOf(obj);
        if (iLastIndexOf >= 0) {
            return size() - iLastIndexOf;
        }
        return -1;
    }

    public void setTop(Object obj) {
        try {
            this.m_map[this.m_firstFree - 1] = obj;
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new EmptyStackException();
        }
    }

    public ObjectStack(int i) {
        super(i);
    }

    public ObjectStack(ObjectStack objectStack) {
        super(objectStack);
    }

    public Object peek() {
        try {
            return this.m_map[this.m_firstFree - 1];
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new EmptyStackException();
        }
    }
}
