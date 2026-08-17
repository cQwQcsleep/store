package com.google.common.cache;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
final class LongAdder extends Striped64 implements Serializable, LongAddable {
    private static final long serialVersionUID = 7249069246863182397L;

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        ((Striped64) this).busy = 0;
        ((Striped64) this).cells = null;
        ((Striped64) this).base = objectInputStream.readLong();
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeLong(sum());
    }

    public void add(long j) {
        int length;
        Striped64.Cell cell;
        Striped64.Cell[] cellArr = ((Striped64) this).cells;
        if (cellArr == null) {
            long j2 = ((Striped64) this).base;
            if (casBase(j2, j2 + j)) {
                return;
            }
        }
        int[] iArr = (int[]) Striped64.threadHashCode.get();
        boolean zCas = true;
        if (iArr != null && cellArr != null && (length = cellArr.length) >= 1 && (cell = cellArr[(length - 1) & iArr[0]]) != null) {
            long j3 = cell.value;
            zCas = cell.cas(j3, j3 + j);
            if (zCas) {
                return;
            }
        }
        retryUpdate(j, iArr, zCas);
    }

    public void decrement() {
        add(-1L);
    }

    public double doubleValue() {
        return sum();
    }

    public float floatValue() {
        return sum();
    }

    public final long fn(long j, long j2) {
        return j + j2;
    }

    public void increment() {
        add(1L);
    }

    public int intValue() {
        return (int) sum();
    }

    public long longValue() {
        return sum();
    }

    public void reset() {
        internalReset(0L);
    }

    public long sum() {
        long j = ((Striped64) this).base;
        Striped64.Cell[] cellArr = ((Striped64) this).cells;
        if (cellArr != null) {
            for (Striped64.Cell cell : cellArr) {
                if (cell != null) {
                    j += cell.value;
                }
            }
        }
        return j;
    }

    public long sumThenReset() {
        long j = ((Striped64) this).base;
        Striped64.Cell[] cellArr = ((Striped64) this).cells;
        ((Striped64) this).base = 0L;
        if (cellArr != null) {
            for (Striped64.Cell cell : cellArr) {
                if (cell != null) {
                    j += cell.value;
                    cell.value = 0L;
                }
            }
        }
        return j;
    }

    public String toString() {
        return Long.toString(sum());
    }
}
