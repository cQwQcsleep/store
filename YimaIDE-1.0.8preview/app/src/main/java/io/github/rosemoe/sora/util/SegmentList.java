package io.github.rosemoe.sora.util;

import defpackage.jb9;
import defpackage.we3;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class SegmentList<T> extends AbstractList<T> {
    public static final int DEFAULT_SEGMENT_CAPACITY = 8192;
    private int length;
    private FindResult<T> result;
    private final int segmentCapacity;
    private final List<Segment<T>> segments;

    public interface ConsumerCompat<T> {
        void accept(T t);
    }

    public SegmentList(int i) {
        this.result = new FindResult<>();
        if (i < 4) {
            w01.a("block size should be at least 4");
            throw null;
        }
        this.segmentCapacity = i;
        this.segments = new ArrayList();
    }

    private void adjustElements(int i, Segment<T> segment) {
        if (i > 0) {
            Segment<T> segment2 = this.segments.get(i - 1);
            if (!segment2.isMutable() || segment2.size() > (this.segmentCapacity * 4) / 5) {
                return;
            }
            int size = segment.size();
            int i2 = this.segmentCapacity;
            if (size > (i2 * 4) / 5) {
                List<T> listSubList = segment.subList(0, ((i2 * 4) / 5) - segment2.size());
                segment2.addAll(listSubList);
                listSubList.clear();
            }
        }
    }

    private void checkAccessIndex(int i) {
        if (i < 0 || i >= this.length) {
            kac.a("index ", i, " out of bounds. length = ", this.length);
        }
    }

    private void checkInsertIndex(int i) {
        if (i < 0 || i > this.length) {
            kac.a("index ", i, " out of bounds. length = ", this.length);
        }
    }

    private Segment<T> ensureMutable(int i) {
        Segment<T> segment = this.segments.get(i);
        Segment<T> mutable = segment.toMutable();
        if (segment == mutable) {
            return segment;
        }
        this.segments.set(i, mutable);
        segment.release();
        return mutable;
    }

    private FindResult<T> getSegment(int i) {
        int i2;
        if (this.segments.isEmpty()) {
            this.segments.add(new Segment<>(this.segmentCapacity));
        }
        List<Segment<T>> list = this.segments;
        Segment<T> segment = list.get(list.size() - 1);
        int size = size() - segment.size();
        int size2 = this.segments.size() - 1;
        int i3 = 0;
        int size3 = 0;
        while (i3 <= size2) {
            Segment<T> segment2 = this.segments.get(i3);
            if ((i >= size3 && i < segment2.size() + size3) || (i2 = i3 + 1) == this.segments.size()) {
                return makeResult(segment2, size3, i3);
            }
            size3 += segment2.size();
            if ((i >= size && i < segment.size() + size) || (size2 == this.segments.size() - 1 && i == this.length)) {
                return makeResult(segment, size, size2);
            }
            if (size2 > 0) {
                segment = this.segments.get(size2 - 1);
                size -= segment.size();
            }
            size2--;
            i3 = i2;
        }
        k2d.a("unreachable");
        return null;
    }

    private FindResult<T> getSegmentMut(int i) {
        FindResult<T> segment = getSegment(i);
        segment.segment = ensureMutable(segment.blockIndex);
        return segment;
    }

    private FindResult<T> makeResult(Segment<T> segment, int i, int i2) {
        FindResult<T> findResult = this.result;
        findResult.blockIndex = i2;
        findResult.segment = segment;
        findResult.offset = i;
        return findResult;
    }

    private void mergeSegment(int i, int i2) {
        if (i > i2) {
            i2 = i;
            i = i2;
        }
        if (i == i2 || i < 0 || i2 >= this.segments.size()) {
            return;
        }
        Segment<T> segment = this.segments.get(i);
        Segment<T> segment2 = this.segments.get(i2);
        if (segment.size() + segment2.size() <= (this.segmentCapacity * 3) / 4) {
            ensureMutable(i);
            this.segments.get(i).addAll(segment2);
            this.segments.remove(i2);
            segment2.release();
        }
    }

    @Override // java.util.AbstractList, java.util.List
    public void add(int i, T t) {
        checkInsertIndex(i);
        FindResult<T> segmentMut = getSegmentMut(i);
        segmentMut.segment.add(i - segmentMut.offset, t);
        this.length++;
        adjustElements(segmentMut.blockIndex, segmentMut.segment);
        int size = segmentMut.segment.size();
        int i2 = this.segmentCapacity;
        if (size >= i2) {
            Segment<T> segment = new Segment<>(this.segmentCapacity);
            Segment<T> segment2 = segmentMut.segment;
            List<T> listSubList = segment2.subList(i2 / 2, segment2.size());
            segment.addAll(listSubList);
            listSubList.clear();
            this.segments.add(segmentMut.blockIndex + 1, segment);
        }
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public void clear() {
        Iterator<Segment<T>> it2 = this.segments.iterator();
        while (it2.hasNext()) {
            it2.next().release();
        }
        this.segments.clear();
        this.length = 0;
    }

    public void forEachCompat(ConsumerCompat<T> consumerCompat) {
        for (int i = 0; i < this.segments.size(); i++) {
            Segment<T> segment = this.segments.get(i);
            for (int i2 = 0; i2 < segment.size(); i2++) {
                consumerCompat.accept(segment.get(i2));
            }
        }
    }

    @Override // java.util.AbstractList, java.util.List
    public T get(int i) {
        checkAccessIndex(i);
        FindResult<T> segment = getSegment(i);
        return segment.segment.get(i - segment.offset);
    }

    @Override // java.util.AbstractList, java.util.List
    public T remove(int i) {
        checkAccessIndex(i);
        FindResult<T> segmentMut = getSegmentMut(i);
        T tRemove = segmentMut.segment.remove(i - segmentMut.offset);
        this.length--;
        mergeSegment(i - 1, i);
        mergeSegment(i, i + 1);
        return tRemove;
    }

    @Override // java.util.AbstractList
    public void removeRange(int i, int i2) {
        if (i > i2) {
            jb9.a("start > end");
            return;
        }
        if (i < 0 || i2 > this.length) {
            we3.a("start = ", i, ", end = ", i2, ", length = ", size());
            return;
        }
        if (i == i2) {
            return;
        }
        FindResult<T> segment = getSegment(i);
        int i3 = segment.offset;
        int i4 = segment.blockIndex;
        Segment<T> segment2 = segment.segment;
        while (true) {
            int i5 = i2 - i3;
            if (i5 <= 0 || i4 >= this.segments.size()) {
                break;
            }
            int size = segment2.size();
            if (i > i3 || i2 < i3 + size) {
                ensureMutable(i4);
                segment2 = this.segments.get(i4);
                segment2.subList(Math.max(i - i3, 0), Math.min(i5, size)).clear();
                i4++;
            } else {
                this.segments.remove(i4);
                segment2.release();
            }
            i3 += size;
            if (i4 < this.segments.size()) {
                segment2 = this.segments.get(i4);
            }
        }
        mergeSegment(i4 - 1, i4);
        this.length -= i2 - i;
    }

    @Override // java.util.AbstractList, java.util.List
    public T set(int i, T t) {
        checkAccessIndex(i);
        FindResult<T> segmentMut = getSegmentMut(i);
        return segmentMut.segment.set(i - segmentMut.offset, t);
    }

    public SegmentList<T> shallowCopy() {
        SegmentList<T> segmentList = new SegmentList<>(this.segmentCapacity);
        segmentList.segments.clear();
        Iterator<Segment<T>> it2 = this.segments.iterator();
        while (it2.hasNext()) {
            it2.next().retain();
        }
        segmentList.segments.addAll(this.segments);
        segmentList.length = this.length;
        return segmentList;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return this.length;
    }

    public static class FindResult<T> {
        int blockIndex;
        int offset;
        Segment<T> segment;

        public FindResult(Segment<T> segment, int i, int i2) {
            this.segment = segment;
            this.offset = i;
            this.blockIndex = i2;
        }

        public FindResult() {
        }
    }

    public static class Segment<T> extends ArrayList<T> implements ShareableData<Segment<T>> {
        private final AtomicInteger refCount;

        public Segment() {
            this.refCount = new AtomicInteger(1);
        }

        public Segment<T> copy() {
            Segment<T> segment = new Segment<>(size());
            segment.addAll(this);
            return segment;
        }

        @Override // io.github.rosemoe.sora.util.ShareableData
        public boolean isMutable() {
            return this.refCount.get() == 1;
        }

        @Override // io.github.rosemoe.sora.util.ShareableData
        public void release() {
            if (this.refCount.decrementAndGet() >= 0) {
                return;
            }
            k2d.a("illegal release invocation");
        }

        @Override // io.github.rosemoe.sora.util.ShareableData
        public void retain() {
            this.refCount.incrementAndGet();
        }

        @Override // io.github.rosemoe.sora.util.ShareableData
        public Segment<T> toMutable() {
            return isMutable() ? this : copy();
        }

        public Segment(int i) {
            super(i);
            this.refCount = new AtomicInteger(1);
        }
    }

    public SegmentList() {
        this(8192);
    }
}
