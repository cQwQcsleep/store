package com.intellij.openapi.application.impl;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;

/* JADX INFO: loaded from: /workspace/dex_all/classes6.dex */
final class BulkArrayQueue<T> {
    private int head;
    private Object[] myQueue = new Object[1024];
    private int tail;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = i != 1 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
        Object[] objArr = new Object[i != 1 ? 3 : 2];
        if (i == 1) {
            objArr[0] = "com/intellij/openapi/application/impl/BulkArrayQueue";
        } else if (i == 2) {
            objArr[0] = "elements";
        } else if (i != 3) {
            objArr[0] = "info";
        } else {
            objArr[0] = "shouldRemove";
        }
        if (i != 1) {
            objArr[1] = "com/intellij/openapi/application/impl/BulkArrayQueue";
        } else {
            objArr[1] = "getAndNullize";
        }
        if (i != 1) {
            if (i == 2) {
                objArr[2] = "bulkEnqueueFirst";
            } else if (i != 3) {
                objArr[2] = "enqueue";
            } else {
                objArr[2] = "removeAll";
            }
        }
        String str2 = String.format(str, objArr);
        if (i == 1) {
            throw new IllegalStateException(str2);
        }
    }

    private T getAndNullize(int i) {
        Object[] objArr = this.myQueue;
        T t = (T) objArr[i];
        objArr[i] = null;
        if (t == null) {
            $$$reportNull$$$0(1);
        }
        return t;
    }

    private void growAndUnwrap(int i) {
        int i2;
        if (i < 0) {
            qf1.a("illegal argument: ", i);
            return;
        }
        Object[] objArr = this.myQueue;
        int length = objArr.length;
        int i3 = (length < 100000 ? length * 2 : (length >> 1) + length) + i;
        if (i3 <= length + i) {
            throw new OutOfMemoryError("reserveAtStart: " + i + "; oldCapacity: " + length);
        }
        Object[] objArr2 = new Object[i3];
        int i4 = this.head;
        int i5 = this.tail;
        if (i4 <= i5) {
            i2 = i5 - i4;
        } else {
            int i6 = length - i4;
            System.arraycopy(objArr, 0, objArr2, i + i6, i5);
            i2 = i6;
        }
        System.arraycopy(this.myQueue, this.head, objArr2, i, i2);
        this.tail = size() + i;
        this.head = i;
        this.myQueue = objArr2;
    }

    public void bulkEnqueueFirst(ObjectArrayList<? extends T> objectArrayList) {
        if (objectArrayList == null) {
            $$$reportNull$$$0(2);
        }
        int size = objectArrayList.size();
        int length = this.myQueue.length;
        if (size > (length - size()) - 1) {
            growAndUnwrap(size);
        }
        int i = this.head;
        int i2 = this.tail;
        if (i > i2) {
            i = (i - i2) - 1;
        }
        int iMin = Math.min(size, i);
        objectArrayList.getElements(0, this.myQueue, this.head - iMin, iMin);
        this.head -= iMin;
        if (iMin != size) {
            int i3 = size - iMin;
            int i4 = length - i3;
            objectArrayList.getElements(iMin, this.myQueue, i4, i3);
            this.head = i4;
        }
    }

    public boolean isEmpty() {
        return this.head == this.tail;
    }

    public T pollFirst() {
        if (isEmpty()) {
            return null;
        }
        int i = this.head;
        int length = (i + 1) % this.myQueue.length;
        T andNullize = getAndNullize(i);
        this.head = length;
        return andNullize;
    }

    public int size() {
        int i = this.head;
        int i2 = this.tail;
        return i <= i2 ? i2 - i : (i2 + this.myQueue.length) - i;
    }
}
