package org.eclipse.jdt.internal.compiler.util;

import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public final class ObjectVector implements Iterable<Object> {
    static int INITIAL_SIZE = 10;
    Object[] elements;
    int maxSize;
    public int size;

    public ObjectVector(int i) {
        i = i <= 0 ? INITIAL_SIZE : i;
        this.maxSize = i;
        this.size = 0;
        this.elements = new Object[i];
    }

    public void add(Object obj) {
        int i = this.size;
        int i2 = this.maxSize;
        if (i == i2) {
            Object[] objArr = this.elements;
            int i3 = i2 * 2;
            this.maxSize = i3;
            Object[] objArr2 = new Object[i3];
            this.elements = objArr2;
            System.arraycopy(objArr, 0, objArr2, 0, i);
        }
        Object[] objArr3 = this.elements;
        int i4 = this.size;
        this.size = i4 + 1;
        objArr3[i4] = obj;
    }

    public void addAll(ObjectVector objectVector) {
        int i = this.size;
        int i2 = objectVector.size;
        if (i + i2 >= this.maxSize) {
            int i3 = i2 + i;
            this.maxSize = i3;
            Object[] objArr = this.elements;
            Object[] objArr2 = new Object[i3];
            this.elements = objArr2;
            System.arraycopy(objArr, 0, objArr2, 0, i);
        }
        System.arraycopy(objectVector.elements, 0, this.elements, this.size, objectVector.size);
        this.size += objectVector.size;
    }

    public boolean contains(Object obj) {
        int i = this.size;
        do {
            i--;
            if (i < 0) {
                return false;
            }
        } while (!obj.equals(this.elements[i]));
        return true;
    }

    public boolean containsIdentical(Object obj) {
        int i = this.size;
        do {
            i--;
            if (i < 0) {
                return false;
            }
        } while (obj != this.elements[i]);
        return true;
    }

    public void copyInto(Object[] objArr, int i) {
        System.arraycopy(this.elements, 0, objArr, i, this.size);
    }

    public Object elementAt(int i) {
        return this.elements[i];
    }

    public Object find(Object obj) {
        int i = this.size;
        do {
            i--;
            if (i < 0) {
                return null;
            }
        } while (!obj.equals(this.elements[i]));
        return this.elements[i];
    }

    @Override // java.lang.Iterable
    public Iterator<Object> iterator() {
        return new Iterator<Object>() { // from class: org.eclipse.jdt.internal.compiler.util.ObjectVector.1
            int i = 0;

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.i < ObjectVector.this.size;
            }

            @Override // java.util.Iterator
            public Object next() {
                ObjectVector objectVector = ObjectVector.this;
                int i = this.i;
                this.i = i + 1;
                return objectVector.elementAt(i);
            }
        };
    }

    public Object remove(Object obj) {
        int i = this.size;
        while (true) {
            int i2 = i - 1;
            if (i2 < 0) {
                return null;
            }
            if (obj.equals(this.elements[i2])) {
                Object[] objArr = this.elements;
                int i3 = this.size - 1;
                this.size = i3;
                System.arraycopy(objArr, i, objArr, i2, i3 - i2);
                this.elements[this.size] = null;
                return obj;
            }
            i = i2;
        }
    }

    public void removeAll() {
        int i = this.size;
        while (true) {
            i--;
            if (i < 0) {
                this.size = 0;
                return;
            }
            this.elements[i] = null;
        }
    }

    public int size() {
        return this.size;
    }

    public String toString() {
        String str = "";
        for (int i = 0; i < this.size; i++) {
            str = str + this.elements[i].toString() + "\n";
        }
        return str;
    }

    public void copyInto(Object[] objArr) {
        copyInto(objArr, 0);
    }

    public ObjectVector() {
        this(INITIAL_SIZE);
    }

    public void addAll(Object[] objArr) {
        int i = this.size;
        if (objArr.length + i >= this.maxSize) {
            int length = objArr.length + i;
            this.maxSize = length;
            Object[] objArr2 = this.elements;
            Object[] objArr3 = new Object[length];
            this.elements = objArr3;
            System.arraycopy(objArr2, 0, objArr3, 0, i);
        }
        System.arraycopy(objArr, 0, this.elements, this.size, objArr.length);
        this.size += objArr.length;
    }
}
