package kotlin.jvm.internal;

import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import kotlin.Metadata;
import kotlin.collections.BooleanIterator;
import kotlin.collections.ByteIterator;
import kotlin.collections.CharIterator;
import kotlin.collections.DoubleIterator;
import kotlin.collections.FloatIterator;
import kotlin.collections.IntIterator;
import kotlin.collections.LongIterator;
import kotlin.collections.ShortIterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u0000F\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0019\n\u0002\u0018\u0002\n\u0002\u0010\u0017\n\u0002\u0018\u0002\n\u0002\u0010\u0015\n\u0002\u0018\u0002\n\u0002\u0010\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0013\n\u0002\u0018\u0002\n\u0002\u0010\u0018\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0086\u0080\u0004\u001a\u0012\u0010\u0000\u001a\u00020\u00042\u0006\u0010\u0002\u001a\u00020\u0005H\u0086\u0080\u0004\u001a\u0012\u0010\u0000\u001a\u00020\u00062\u0006\u0010\u0002\u001a\u00020\u0007H\u0086\u0080\u0004\u001a\u0012\u0010\u0000\u001a\u00020\b2\u0006\u0010\u0002\u001a\u00020\tH\u0086\u0080\u0004\u001a\u0012\u0010\u0000\u001a\u00020\n2\u0006\u0010\u0002\u001a\u00020\u000bH\u0086\u0080\u0004\u001a\u0012\u0010\u0000\u001a\u00020\f2\u0006\u0010\u0002\u001a\u00020\rH\u0086\u0080\u0004\u001a\u0012\u0010\u0000\u001a\u00020\u000e2\u0006\u0010\u0002\u001a\u00020\u000fH\u0086\u0080\u0004\u001a\u0012\u0010\u0000\u001a\u00020\u00102\u0006\u0010\u0002\u001a\u00020\u0011H\u0086\u0080\u0004¨\u0006\u0012"}, d2 = {"iterator", "Lkotlin/collections/ByteIterator;", "array", "", "Lkotlin/collections/CharIterator;", "", "Lkotlin/collections/ShortIterator;", "", "Lkotlin/collections/IntIterator;", "", "Lkotlin/collections/LongIterator;", "", "Lkotlin/collections/FloatIterator;", "", "Lkotlin/collections/DoubleIterator;", "", "Lkotlin/collections/BooleanIterator;", "", "kotlin-stdlib"}, k = 2, mv = {2, 4, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
public final class ArrayIteratorsKt {
    public static final ByteIterator iterator(byte[] bArr) {
        bArr.getClass();
        return new ArrayByteIterator(bArr);
    }

    public static final CharIterator iterator(char[] cArr) {
        cArr.getClass();
        return new ArrayCharIterator(cArr);
    }

    public static final ShortIterator iterator(short[] sArr) {
        sArr.getClass();
        return new ArrayShortIterator(sArr);
    }

    public static final IntIterator iterator(int[] iArr) {
        iArr.getClass();
        return new ArrayIntIterator(iArr);
    }

    public static final LongIterator iterator(long[] jArr) {
        jArr.getClass();
        return new ArrayLongIterator(jArr);
    }

    public static final FloatIterator iterator(float[] fArr) {
        fArr.getClass();
        return new ArrayFloatIterator(fArr);
    }

    public static final DoubleIterator iterator(double[] dArr) {
        dArr.getClass();
        return new ArrayDoubleIterator(dArr);
    }

    public static final BooleanIterator iterator(boolean[] zArr) {
        zArr.getClass();
        return new ArrayBooleanIterator(zArr);
    }
}
