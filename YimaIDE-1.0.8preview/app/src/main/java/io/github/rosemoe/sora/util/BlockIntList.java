package io.github.rosemoe.sora.util;

import defpackage.hx0;
import io.github.rosemoe.sora.text.Content;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class BlockIntList {
    private static final int CACHE_COUNT = 8;
    private static final int CACHE_SWITCH = 30;
    private final int blockSize;
    private final List<Cache> caches;
    private Block foundBlock;
    private int foundIndex;
    private Block head;
    private int length;
    public final Lock lock;
    private int max;
    private int modCount;
    private final List<Block> recycled;
    private int updateTime;

    public class Cache {
        public Block block;
        public int indexOfStart;

        private Cache() {
        }
    }

    public BlockIntList(int i) {
        this.lock = new ReentrantLock();
        this.recycled = new ArrayList();
        this.blockSize = i;
        if (i <= 4) {
            w01.a("block size must be bigger than 4");
            throw null;
        }
        this.length = 0;
        this.modCount = 0;
        this.head = new Block();
        this.caches = new ArrayList(10);
    }

    private Cache cache(int i, Block block) {
        Cache cache = new Cache();
        cache.indexOfStart = i;
        cache.block = block;
        return cache;
    }

    private void computeMax() {
        this.max = 0;
        for (Block block = this.head; block != null; block = block.next) {
            this.max = Math.max(this.max, block.max);
        }
    }

    private void findBlock1(int i) {
        Block block = this.head;
        int i2 = 0;
        int size = i;
        int i3 = -1;
        for (int i4 = 0; i4 < this.caches.size(); i4++) {
            Cache cache = this.caches.get(i4);
            int i5 = cache.indexOfStart;
            if (i5 < i && i - i5 < size) {
                size = i - i5;
                block = cache.block;
                i3 = i4;
            }
        }
        if (i3 != -1) {
            Collections.swap(this.caches, 0, i3);
        }
        while (size >= block.size() && block.next != null) {
            size -= block.size();
            block = block.next;
            i2++;
        }
        if (i2 >= 30) {
            this.caches.add(cache(i - size, block));
        }
        if (this.caches.size() > 8) {
            List<Cache> list = this.caches;
            list.remove(list.size() - 1);
        }
        this.foundIndex = size;
        this.foundBlock = block;
    }

    private void invalidateCacheFrom(int i) {
        int i2 = 0;
        while (i2 < this.caches.size()) {
            if (this.caches.get(i2).indexOfStart >= i) {
                this.caches.remove(i2);
                i2--;
            }
            i2++;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Block newBlock() {
        if (this.recycled.isEmpty()) {
            return new Block();
        }
        List<Block> list = this.recycled;
        return list.remove(list.size() - 1);
    }

    public void add(int i, int i2) {
        if (i < 0 || i > size()) {
            hx0.a(i, size());
            return;
        }
        findBlock1(i);
        invalidateCacheFrom(i);
        Block block = this.foundBlock;
        int size = this.foundIndex;
        while (size > block.size() && block.next != null) {
            size -= block.size();
            block = block.next;
        }
        block.add(size, i2);
        this.length++;
        if (block.size() > this.blockSize) {
            block.separate();
        }
        this.modCount++;
    }

    public void clear() {
        this.head = new Block();
        this.length = 0;
        this.caches.clear();
        this.foundBlock = null;
        this.foundIndex = 0;
    }

    public int get(int i) {
        if (i < 0 || i >= size()) {
            hx0.a(i, size());
            return 0;
        }
        findBlock1(i);
        return this.foundBlock.get(this.foundIndex);
    }

    public int getMax() {
        int i = this.modCount;
        if (i != this.updateTime) {
            this.updateTime = i;
        }
        computeMax();
        return this.max;
    }

    public int remove(int i) {
        if (i < 0 || i >= size()) {
            hx0.a(i, size());
            return 0;
        }
        Block block = this.head;
        Block block2 = null;
        int size = i;
        while (size >= block.size()) {
            size -= block.size();
            block2 = block;
            block = block.next;
        }
        int iRemove = block.remove(size);
        invalidateCacheFrom(i - size);
        if (block.size() == 0 && block2 != null) {
            block2.next = block.next;
            this.recycled.add(block);
        } else if (block.size() < this.blockSize / 4 && block2 != null && block2.size() + block.size() < this.blockSize / 2) {
            block2.next = block.next;
            System.arraycopy(block.data, 0, block2.data, block2.size, block.size);
            block2.size += block.size;
        }
        this.modCount++;
        this.length--;
        return iRemove;
    }

    public void removeRange(int i, int i2) {
        if (i2 > this.length || i < 0 || i > i2) {
            qc6.a();
            return;
        }
        Block block = this.head;
        Block block2 = null;
        while (i >= block.size()) {
            i -= block.size();
            i2 -= block.size();
            block2 = block;
            block = block.next;
        }
        int i3 = i2 - i;
        int size = i3;
        while (size > 0) {
            if (i != 0 || size < block.size()) {
                int iMin = Math.min(block.size(), size);
                block.remove(0, iMin);
                size -= iMin;
                block2 = block;
                block = block.next;
                i = 0;
            } else {
                if (block2 != null) {
                    block2.next = block.next;
                    this.recycled.add(block);
                }
                size -= block.size();
                block.size = 0;
                block = block.next;
            }
        }
        this.length -= i3;
    }

    public int set(int i, int i2) {
        if (i < 0 || i >= size()) {
            hx0.a(i, size());
            return 0;
        }
        findBlock1(i);
        int i3 = this.foundBlock.set(this.foundIndex, i2);
        this.modCount++;
        return i3;
    }

    public int size() {
        return this.length;
    }

    public class Block {
        private final int[] data;
        private int max;
        private Block next;
        private int size = 0;

        public Block() {
            this.data = new int[BlockIntList.this.blockSize + 5];
        }

        private void compute() {
            int iMax = 0;
            for (int i = 0; i < this.size; i++) {
                iMax = Math.max(iMax, this.data[i]);
            }
            this.max = iMax;
        }

        public void add(int i, int i2) {
            int[] iArr = this.data;
            System.arraycopy(iArr, i, iArr, i + 1, this.size - i);
            this.data[i] = i2;
            this.size++;
            if (i2 > this.max) {
                this.max = i2;
            }
        }

        public int get(int i) {
            return this.data[i];
        }

        public int remove(int i) {
            int[] iArr = this.data;
            int i2 = iArr[i];
            System.arraycopy(iArr, i + 1, iArr, i, (this.size - i) - 1);
            this.size--;
            if (i2 == this.max) {
                compute();
            }
            return i2;
        }

        public void separate() {
            Block block = this.next;
            Block blockNewBlock = BlockIntList.this.newBlock();
            int i = (BlockIntList.this.blockSize * 3) / 4;
            System.arraycopy(this.data, i, blockNewBlock.data, 0, this.size - i);
            blockNewBlock.size = this.size - i;
            this.size = i;
            this.next = blockNewBlock;
            blockNewBlock.next = block;
            compute();
            blockNewBlock.compute();
        }

        public int set(int i, int i2) {
            int[] iArr = this.data;
            int i3 = iArr[i];
            iArr[i] = i2;
            int i4 = this.max;
            if (i3 != i4) {
                if (i2 > i4) {
                    this.max = i2;
                }
                return i3;
            }
            if (i2 >= i3) {
                this.max = i2;
                return i3;
            }
            compute();
            return i3;
        }

        public int size() {
            return this.size;
        }

        public void remove(int i, int i2) {
            int[] iArr = this.data;
            System.arraycopy(iArr, i2, iArr, i, this.size - i2);
            this.size -= i2 - i;
            compute();
        }
    }

    public BlockIntList() {
        this(Content.DEFAULT_LIST_CAPACITY);
    }

    public void add(int i) {
        add(this.length, i);
    }
}
