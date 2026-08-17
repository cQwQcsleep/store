package io.github.rosemoe.sora.util;

import android.util.SparseIntArray;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class BinaryHeap {
    public final Lock lock = new ReentrantLock();
    private int idAllocator = 1;
    private final SparseIntArray idToPosition = new SparseIntArray();
    private int nodeCount = 0;
    private long[] nodes = new long[129];

    private static int data(long j) {
        return IntPair.getSecond(j);
    }

    private void heapifyDown(int i) {
        int i2 = i * 2;
        while (true) {
            int i3 = this.nodeCount;
            if (i2 > i3) {
                return;
            }
            long[] jArr = this.nodes;
            long j = jArr[i];
            int i4 = i2 + 1;
            if (i4 <= i3 && data(jArr[i4]) > data(this.nodes[i2])) {
                i2 = i4;
            }
            long j2 = this.nodes[i2];
            if (data(j) >= data(j2)) {
                return;
            }
            this.idToPosition.put(id(j2), i);
            this.idToPosition.put(id(j), i2);
            long[] jArr2 = this.nodes;
            jArr2[i2] = j;
            jArr2[i] = j2;
            int i5 = i2;
            i2 *= 2;
            i = i5;
        }
    }

    private void heapifyUp(int i) {
        while (true) {
            int i2 = i;
            i /= 2;
            if (i < 1) {
                return;
            }
            long[] jArr = this.nodes;
            long j = jArr[i2];
            long j2 = jArr[i];
            if (data(j) <= data(j2)) {
                return;
            }
            this.idToPosition.put(id(j), i);
            this.idToPosition.put(id(j2), i2);
            long[] jArr2 = this.nodes;
            jArr2[i2] = j2;
            jArr2[i] = j;
        }
    }

    private static int id(long j) {
        return IntPair.getFirst(j);
    }

    public void clear() {
        this.nodeCount = 0;
        this.idToPosition.clear();
        this.idAllocator = -1;
    }

    public void ensureCapacity(int i) {
        int i2 = i + 1;
        long[] jArr = this.nodes;
        if (jArr.length < i2) {
            if ((jArr.length << 1) >= i2) {
                this.nodes = new long[jArr.length << 1];
            } else {
                this.nodes = new long[i2];
            }
            System.arraycopy(jArr, 0, this.nodes, 0, this.nodeCount + 1);
        }
    }

    public int getNodeCount() {
        return this.nodeCount;
    }

    public int push(int i) {
        ensureCapacity(this.nodeCount + 1);
        int i2 = this.idAllocator;
        if (i2 == Integer.MAX_VALUE) {
            k2d.a("unable to allocate more id");
            return 0;
        }
        this.idAllocator = i2 + 1;
        int i3 = this.nodeCount + 1;
        this.nodeCount = i3;
        this.nodes[i3] = IntPair.pack(i2, i);
        this.idToPosition.put(i2, this.nodeCount);
        heapifyUp(this.nodeCount);
        return i2;
    }

    public void remove(int i) {
        int i2 = this.idToPosition.get(i, 0);
        if (i2 == 0) {
            w01.a("trying to remove with an invalid id");
            return;
        }
        this.idToPosition.delete(i);
        long[] jArr = this.nodes;
        int i3 = this.nodeCount;
        jArr[i2] = jArr[i3];
        this.nodeCount = i3 - 1;
        jArr[i3] = 0;
        if (i2 == i3) {
            return;
        }
        this.idToPosition.put(id(jArr[i2]), i2);
        heapifyUp(i2);
        heapifyDown(i2);
    }

    public int top() {
        if (this.nodeCount == 0) {
            return 0;
        }
        return data(this.nodes[1]);
    }

    public void update(int i, int i2) {
        int i3 = this.idToPosition.get(i, 0);
        if (i3 == 0) {
            w01.a("trying to update with an invalid id");
            return;
        }
        int iData = data(this.nodes[i3]);
        long[] jArr = this.nodes;
        jArr[i3] = IntPair.pack(id(jArr[i3]), i2);
        if (iData < i2) {
            heapifyUp(i3);
        } else if (iData > i2) {
            heapifyDown(i3);
        }
    }
}
