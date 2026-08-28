package com.shadow.okio.internal;

import com.shadow.kotlin.collections.ArraysKt;
import com.shadow.kotlin.io.CloseableKt;
import com.shadow.okio.Buffer;
import com.shadow.okio.ByteString;
import com.shadow.okio.C0058SegmentedByteString;
import com.shadow.okio.Segment;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;

/* renamed from: com.shadow.okio.internal.-SegmentedByteString, reason: invalid class name */
/* loaded from: /workspace/unpacked/classes2.dex */
public final class SegmentedByteString {
    public static final int binarySearch(int[] iArr, int i, int i2, int i3) {
        CloseableKt.checkNotNullParameter(iArr, "<this>");
        int i4 = i3 - 1;
        while (i2 <= i4) {
            int i5 = (i2 + i4) >>> 1;
            int i6 = iArr[i5];
            if (i6 < i) {
                i2 = i5 + 1;
            } else {
                if (i6 <= i) {
                    return i5;
                }
                i4 = i5 - 1;
            }
        }
        return (-i2) - 1;
    }

    public static final void commonCopyInto(C0058SegmentedByteString c0058SegmentedByteString, int i, byte[] bArr, int i2, int i3) {
        CloseableKt.checkNotNullParameter(c0058SegmentedByteString, "<this>");
        CloseableKt.checkNotNullParameter(bArr, "target");
        long j = i3;
        com.shadow.okio.SegmentedByteString.checkOffsetAndCount(c0058SegmentedByteString.size(), i, j);
        com.shadow.okio.SegmentedByteString.checkOffsetAndCount(bArr.length, i2, j);
        int i4 = i3 + i;
        int iSegment = segment(c0058SegmentedByteString, i);
        while (i < i4) {
            int i5 = iSegment == 0 ? 0 : c0058SegmentedByteString.getDirectory$okio()[iSegment - 1];
            int i6 = c0058SegmentedByteString.getDirectory$okio()[iSegment] - i5;
            int i7 = c0058SegmentedByteString.getDirectory$okio()[c0058SegmentedByteString.getSegments$okio().length + iSegment];
            int iMin = Math.min(i4, i6 + i5) - i;
            int i8 = (i - i5) + i7;
            ArraysKt.b(c0058SegmentedByteString.getSegments$okio()[iSegment], i2, bArr, i8, i8 + iMin);
            i2 += iMin;
            i += iMin;
            iSegment++;
        }
    }

    public static final boolean commonEquals(C0058SegmentedByteString c0058SegmentedByteString, Object obj) {
        CloseableKt.checkNotNullParameter(c0058SegmentedByteString, "<this>");
        if (obj == c0058SegmentedByteString) {
            return true;
        }
        if (obj instanceof ByteString) {
            ByteString byteString = (ByteString) obj;
            if (byteString.size() == c0058SegmentedByteString.size() && c0058SegmentedByteString.rangeEquals(0, byteString, 0, c0058SegmentedByteString.size())) {
                return true;
            }
        }
        return false;
    }

    public static final int commonGetSize(C0058SegmentedByteString c0058SegmentedByteString) {
        CloseableKt.checkNotNullParameter(c0058SegmentedByteString, "<this>");
        return c0058SegmentedByteString.getDirectory$okio()[c0058SegmentedByteString.getSegments$okio().length - 1];
    }

    public static final int commonHashCode(C0058SegmentedByteString c0058SegmentedByteString) {
        CloseableKt.checkNotNullParameter(c0058SegmentedByteString, "<this>");
        int hashCode$okio = c0058SegmentedByteString.getHashCode$okio();
        if (hashCode$okio != 0) {
            return hashCode$okio;
        }
        int length = c0058SegmentedByteString.getSegments$okio().length;
        int i = 0;
        int i2 = 0;
        int i3 = 1;
        while (i < length) {
            int i4 = c0058SegmentedByteString.getDirectory$okio()[length + i];
            int i5 = c0058SegmentedByteString.getDirectory$okio()[i];
            byte[] bArr = c0058SegmentedByteString.getSegments$okio()[i];
            int i6 = (i5 - i2) + i4;
            while (i4 < i6) {
                i3 = (i3 * 31) + bArr[i4];
                i4++;
            }
            i++;
            i2 = i5;
        }
        c0058SegmentedByteString.setHashCode$okio(i3);
        return i3;
    }

    public static final byte commonInternalGet(C0058SegmentedByteString c0058SegmentedByteString, int i) {
        CloseableKt.checkNotNullParameter(c0058SegmentedByteString, "<this>");
        com.shadow.okio.SegmentedByteString.checkOffsetAndCount(c0058SegmentedByteString.getDirectory$okio()[c0058SegmentedByteString.getSegments$okio().length - 1], i, 1L);
        int iSegment = segment(c0058SegmentedByteString, i);
        return c0058SegmentedByteString.getSegments$okio()[iSegment][(i - (iSegment == 0 ? 0 : c0058SegmentedByteString.getDirectory$okio()[iSegment - 1])) + c0058SegmentedByteString.getDirectory$okio()[c0058SegmentedByteString.getSegments$okio().length + iSegment]];
    }

    public static final boolean commonRangeEquals(C0058SegmentedByteString c0058SegmentedByteString, int i, ByteString byteString, int i2, int i3) {
        CloseableKt.checkNotNullParameter(c0058SegmentedByteString, "<this>");
        CloseableKt.checkNotNullParameter(byteString, "other");
        if (i < 0 || i > c0058SegmentedByteString.size() - i3) {
            return false;
        }
        int i4 = i3 + i;
        int iSegment = segment(c0058SegmentedByteString, i);
        while (i < i4) {
            int i5 = iSegment == 0 ? 0 : c0058SegmentedByteString.getDirectory$okio()[iSegment - 1];
            int i6 = c0058SegmentedByteString.getDirectory$okio()[iSegment] - i5;
            int i7 = c0058SegmentedByteString.getDirectory$okio()[c0058SegmentedByteString.getSegments$okio().length + iSegment];
            int iMin = Math.min(i4, i6 + i5) - i;
            if (!byteString.rangeEquals(i2, c0058SegmentedByteString.getSegments$okio()[iSegment], (i - i5) + i7, iMin)) {
                return false;
            }
            i2 += iMin;
            i += iMin;
            iSegment++;
        }
        return true;
    }

    public static final ByteString commonSubstring(C0058SegmentedByteString c0058SegmentedByteString, int i, int i2) {
        CloseableKt.checkNotNullParameter(c0058SegmentedByteString, "<this>");
        int iResolveDefaultParameter = com.shadow.okio.SegmentedByteString.resolveDefaultParameter(c0058SegmentedByteString, i2);
        if (i < 0) {
            throw new IllegalArgumentException(("beginIndex=" + i + " < 0").toString());
        }
        if (iResolveDefaultParameter > c0058SegmentedByteString.size()) {
            throw new IllegalArgumentException(("endIndex=" + iResolveDefaultParameter + " > length(" + c0058SegmentedByteString.size() + ')').toString());
        }
        int i3 = iResolveDefaultParameter - i;
        if (i3 < 0) {
            throw new IllegalArgumentException(("endIndex=" + iResolveDefaultParameter + " < beginIndex=" + i).toString());
        }
        if (i == 0 && iResolveDefaultParameter == c0058SegmentedByteString.size()) {
            return c0058SegmentedByteString;
        }
        if (i == iResolveDefaultParameter) {
            return ByteString.EMPTY;
        }
        int iSegment = segment(c0058SegmentedByteString, i);
        int iSegment2 = segment(c0058SegmentedByteString, iResolveDefaultParameter - 1);
        byte[][] bArr = (byte[][]) ArraysKt.e(c0058SegmentedByteString.getSegments$okio(), iSegment, iSegment2 + 1);
        int[] iArr = new int[bArr.length * 2];
        if (iSegment <= iSegment2) {
            int i4 = iSegment;
            int i5 = 0;
            while (true) {
                iArr[i5] = Math.min(c0058SegmentedByteString.getDirectory$okio()[i4] - i, i3);
                int i6 = i5 + 1;
                iArr[i5 + bArr.length] = c0058SegmentedByteString.getDirectory$okio()[c0058SegmentedByteString.getSegments$okio().length + i4];
                if (i4 == iSegment2) {
                    break;
                }
                i4++;
                i5 = i6;
            }
        }
        int i7 = iSegment != 0 ? c0058SegmentedByteString.getDirectory$okio()[iSegment - 1] : 0;
        int length = bArr.length;
        iArr[length] = (i - i7) + iArr[length];
        return new C0058SegmentedByteString(bArr, iArr);
    }

    public static final byte[] commonToByteArray(C0058SegmentedByteString c0058SegmentedByteString) {
        CloseableKt.checkNotNullParameter(c0058SegmentedByteString, "<this>");
        byte[] bArr = new byte[c0058SegmentedByteString.size()];
        int length = c0058SegmentedByteString.getSegments$okio().length;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (i < length) {
            int i4 = c0058SegmentedByteString.getDirectory$okio()[length + i];
            int i5 = c0058SegmentedByteString.getDirectory$okio()[i];
            int i6 = i5 - i2;
            ArraysKt.b(c0058SegmentedByteString.getSegments$okio()[i], i3, bArr, i4, i4 + i6);
            i3 += i6;
            i++;
            i2 = i5;
        }
        return bArr;
    }

    public static final void commonWrite(C0058SegmentedByteString c0058SegmentedByteString, Buffer buffer, int i, int i2) {
        CloseableKt.checkNotNullParameter(c0058SegmentedByteString, "<this>");
        CloseableKt.checkNotNullParameter(buffer, "buffer");
        int i3 = i + i2;
        int iSegment = segment(c0058SegmentedByteString, i);
        while (i < i3) {
            int i4 = iSegment == 0 ? 0 : c0058SegmentedByteString.getDirectory$okio()[iSegment - 1];
            int i5 = c0058SegmentedByteString.getDirectory$okio()[iSegment] - i4;
            int i6 = c0058SegmentedByteString.getDirectory$okio()[c0058SegmentedByteString.getSegments$okio().length + iSegment];
            int iMin = Math.min(i3, i5 + i4) - i;
            int i7 = (i - i4) + i6;
            Segment segment = new Segment(c0058SegmentedByteString.getSegments$okio()[iSegment], i7, i7 + iMin, true, false);
            Segment segment2 = buffer.head;
            if (segment2 == null) {
                segment.prev = segment;
                segment.next = segment;
                buffer.head = segment;
            } else {
                CloseableKt.checkNotNull(segment2);
                Segment segment3 = segment2.prev;
                CloseableKt.checkNotNull(segment3);
                segment3.push(segment);
            }
            i += iMin;
            iSegment++;
        }
        buffer.setSize$okio(buffer.size() + i2);
    }

    public static final void forEachSegment(C0058SegmentedByteString c0058SegmentedByteString, Function3<? super byte[], ? super Integer, ? super Integer, Unit> function3) {
        CloseableKt.checkNotNullParameter(c0058SegmentedByteString, "<this>");
        CloseableKt.checkNotNullParameter(function3, "action");
        int length = c0058SegmentedByteString.getSegments$okio().length;
        for (int i = 0; i < length; i++) {
            int i2 = c0058SegmentedByteString.getDirectory$okio()[length + i];
            int i3 = c0058SegmentedByteString.getDirectory$okio()[i];
            byte[] bArr = c0058SegmentedByteString.getSegments$okio()[i];
            function3.invoke$1();
        }
    }

    public static final int segment(C0058SegmentedByteString c0058SegmentedByteString, int i) {
        CloseableKt.checkNotNullParameter(c0058SegmentedByteString, "<this>");
        int iBinarySearch = binarySearch(c0058SegmentedByteString.getDirectory$okio(), i + 1, 0, c0058SegmentedByteString.getSegments$okio().length);
        return iBinarySearch >= 0 ? iBinarySearch : ~iBinarySearch;
    }

    private static final void forEachSegment(C0058SegmentedByteString c0058SegmentedByteString, int i, int i2, Function3<? super byte[], ? super Integer, ? super Integer, Unit> function3) {
        int iSegment = segment(c0058SegmentedByteString, i);
        while (i < i2) {
            int i3 = iSegment == 0 ? 0 : c0058SegmentedByteString.getDirectory$okio()[iSegment - 1];
            int i4 = c0058SegmentedByteString.getDirectory$okio()[iSegment] - i3;
            int i5 = c0058SegmentedByteString.getDirectory$okio()[c0058SegmentedByteString.getSegments$okio().length + iSegment];
            int iMin = Math.min(i2, i3 + i4) - i;
            byte[] bArr = c0058SegmentedByteString.getSegments$okio()[iSegment];
            function3.invoke$1();
            i += iMin;
            iSegment++;
        }
    }

    public static final boolean commonRangeEquals(C0058SegmentedByteString c0058SegmentedByteString, int i, byte[] bArr, int i2, int i3) {
        CloseableKt.checkNotNullParameter(c0058SegmentedByteString, "<this>");
        CloseableKt.checkNotNullParameter(bArr, "other");
        if (i < 0 || i > c0058SegmentedByteString.size() - i3 || i2 < 0 || i2 > bArr.length - i3) {
            return false;
        }
        int i4 = i3 + i;
        int iSegment = segment(c0058SegmentedByteString, i);
        while (i < i4) {
            int i5 = iSegment == 0 ? 0 : c0058SegmentedByteString.getDirectory$okio()[iSegment - 1];
            int i6 = c0058SegmentedByteString.getDirectory$okio()[iSegment] - i5;
            int i7 = c0058SegmentedByteString.getDirectory$okio()[c0058SegmentedByteString.getSegments$okio().length + iSegment];
            int iMin = Math.min(i4, i6 + i5) - i;
            if (!com.shadow.okio.SegmentedByteString.arrayRangeEquals(c0058SegmentedByteString.getSegments$okio()[iSegment], (i - i5) + i7, bArr, i2, iMin)) {
                return false;
            }
            i2 += iMin;
            i += iMin;
            iSegment++;
        }
        return true;
    }
}
