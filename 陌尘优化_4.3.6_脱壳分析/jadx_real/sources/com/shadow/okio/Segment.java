package com.shadow.okio;

import com.shadow.kotlin.collections.ArraysKt;
import com.shadow.kotlin.io.CloseableKt;
import com.shadow.kotlin.jvm.internal.DefaultConstructorMarker;
import java.util.Arrays;

/* loaded from: /workspace/unpacked/classes2.dex */
public final class Segment {
    public static final Companion Companion = new Companion(null);
    public static final int SHARE_MINIMUM = 1024;
    public static final int SIZE = 8192;
    public final byte[] data;
    public int limit;
    public Segment next;
    public boolean owner;
    public int pos;
    public Segment prev;
    public boolean shared;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public Segment() {
        this.data = new byte[SIZE];
        this.owner = true;
        this.shared = false;
    }

    public final void compact() {
        int i;
        Segment segment = this.prev;
        if (segment == this) {
            throw new IllegalStateException("cannot compact");
        }
        CloseableKt.checkNotNull(segment);
        if (segment.owner) {
            int i2 = this.limit - this.pos;
            Segment segment2 = this.prev;
            CloseableKt.checkNotNull(segment2);
            int i3 = 8192 - segment2.limit;
            Segment segment3 = this.prev;
            CloseableKt.checkNotNull(segment3);
            if (segment3.shared) {
                i = 0;
            } else {
                Segment segment4 = this.prev;
                CloseableKt.checkNotNull(segment4);
                i = segment4.pos;
            }
            if (i2 > i3 + i) {
                return;
            }
            Segment segment5 = this.prev;
            CloseableKt.checkNotNull(segment5);
            writeTo(segment5, i2);
            pop();
            SegmentPool.recycle(this);
        }
    }

    public final Segment pop() {
        Segment segment = this.next;
        if (segment == this) {
            segment = null;
        }
        Segment segment2 = this.prev;
        CloseableKt.checkNotNull(segment2);
        segment2.next = this.next;
        Segment segment3 = this.next;
        CloseableKt.checkNotNull(segment3);
        segment3.prev = this.prev;
        this.next = null;
        this.prev = null;
        return segment;
    }

    public final Segment push(Segment segment) {
        CloseableKt.checkNotNullParameter(segment, "segment");
        segment.prev = this;
        segment.next = this.next;
        Segment segment2 = this.next;
        CloseableKt.checkNotNull(segment2);
        segment2.prev = segment;
        this.next = segment;
        return segment;
    }

    public final Segment sharedCopy() {
        this.shared = true;
        return new Segment(this.data, this.pos, this.limit, true, false);
    }

    public final Segment split(int i) {
        Segment segmentTake;
        if (i <= 0 || i > this.limit - this.pos) {
            throw new IllegalArgumentException("byteCount out of range");
        }
        if (i >= 1024) {
            segmentTake = sharedCopy();
        } else {
            segmentTake = SegmentPool.take();
            byte[] bArr = this.data;
            byte[] bArr2 = segmentTake.data;
            int i2 = this.pos;
            ArraysKt.b(bArr, 0, bArr2, i2, i2 + i);
        }
        segmentTake.limit = segmentTake.pos + i;
        this.pos += i;
        Segment segment = this.prev;
        CloseableKt.checkNotNull(segment);
        segment.push(segmentTake);
        return segmentTake;
    }

    public final Segment unsharedCopy() {
        byte[] bArr = this.data;
        byte[] bArrCopyOf = Arrays.copyOf(bArr, bArr.length);
        CloseableKt.checkNotNullExpressionValue(bArrCopyOf, "copyOf(...)");
        return new Segment(bArrCopyOf, this.pos, this.limit, false, true);
    }

    public final void writeTo(Segment segment, int i) {
        CloseableKt.checkNotNullParameter(segment, "sink");
        if (!segment.owner) {
            throw new IllegalStateException("only owner can write");
        }
        int i2 = segment.limit;
        int i3 = i2 + i;
        if (i3 > 8192) {
            if (segment.shared) {
                throw new IllegalArgumentException();
            }
            int i4 = segment.pos;
            if (i3 - i4 > 8192) {
                throw new IllegalArgumentException();
            }
            byte[] bArr = segment.data;
            ArraysKt.b(bArr, 0, bArr, i4, i2);
            segment.limit -= segment.pos;
            segment.pos = 0;
        }
        byte[] bArr2 = this.data;
        byte[] bArr3 = segment.data;
        int i5 = segment.limit;
        int i6 = this.pos;
        ArraysKt.b(bArr2, i5, bArr3, i6, i6 + i);
        segment.limit += i;
        this.pos += i;
    }

    public Segment(byte[] bArr, int i, int i2, boolean z, boolean z2) {
        CloseableKt.checkNotNullParameter(bArr, "data");
        this.data = bArr;
        this.pos = i;
        this.limit = i2;
        this.shared = z;
        this.owner = z2;
    }
}
