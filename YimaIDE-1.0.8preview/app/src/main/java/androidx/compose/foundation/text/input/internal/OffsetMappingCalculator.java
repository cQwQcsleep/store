package androidx.compose.foundation.text.input.internal;

import androidx.compose.foundation.internal.InlineClassHelperKt;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\t\b\u0001\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\bJ\u0015\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\b¢\u0006\u0004\b\u0011\u0010\u0012J\u0015\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\b¢\u0006\u0004\b\u0014\u0010\u0012J\u001f\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\u0017H\u0002¢\u0006\u0004\b\u0018\u0010\u0019J7\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\b2\u0006\u0010\u001c\u001a\u00020\b2\u0006\u0010\u001d\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\u0017H\u0002¢\u0006\u0004\b\u001e\u0010\u001fR\u0010\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Landroidx/compose/foundation/text/input/internal/OffsetMappingCalculator;", "", "<init>", "()V", "ops", "Landroidx/compose/foundation/text/input/internal/OpArray;", "[I", "opsSize", "", "recordEditOperation", "", "sourceStart", "sourceEnd", "newLength", "mapFromSource", "Landroidx/compose/ui/text/TextRange;", "offset", "mapFromSource--jx7JFs", "(I)J", "mapFromDest", "mapFromDest--jx7JFs", "map", "fromSource", "", "map-fzxv0v0", "(IZ)J", "mapStep", "opOffset", "untransformedLen", "transformedLen", "mapStep-C6u-MEY", "(IIIIZ)J", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class OffsetMappingCalculator {
    public static final int $stable = 8;
    private int[] ops = OpArray.constructor-impl(10);
    private int opsSize;

    /* JADX INFO: renamed from: map-fzxv0v0, reason: not valid java name */
    private final long m1562mapfzxv0v0(int offset, boolean fromSource) {
        int i;
        int iMin;
        int[] iArr = this.ops;
        int i2 = this.opsSize;
        if (i2 >= 0) {
            if (fromSource) {
                iMin = offset;
                for (int i3 = 0; i3 < i2; i3++) {
                    int i4 = i3 * 3;
                    int i5 = iArr[i4];
                    int i6 = iArr[i4 + 1];
                    int i7 = iArr[i4 + 2];
                    long jM1563mapStepC6uMEY = m1563mapStepC6uMEY(iMin, i5, i6, i7, fromSource);
                    long jM1563mapStepC6uMEY2 = m1563mapStepC6uMEY(offset, i5, i6, i7, fromSource);
                    iMin = Math.min(TextRange.getStart-impl(jM1563mapStepC6uMEY), TextRange.getStart-impl(jM1563mapStepC6uMEY2));
                    offset = Math.max(TextRange.getEnd-impl(jM1563mapStepC6uMEY), TextRange.getEnd-impl(jM1563mapStepC6uMEY2));
                }
            } else {
                int i8 = i2 - 1;
                iMin = offset;
                while (-1 < i8) {
                    int i9 = i8 * 3;
                    int i10 = iArr[i9];
                    int i11 = iArr[i9 + 1];
                    int i12 = iArr[i9 + 2];
                    OffsetMappingCalculator offsetMappingCalculator = this;
                    boolean z = fromSource;
                    long jM1563mapStepC6uMEY3 = offsetMappingCalculator.m1563mapStepC6uMEY(iMin, i10, i11, i12, z);
                    long jM1563mapStepC6uMEY4 = offsetMappingCalculator.m1563mapStepC6uMEY(offset, i10, i11, i12, z);
                    iMin = Math.min(TextRange.getStart-impl(jM1563mapStepC6uMEY3), TextRange.getStart-impl(jM1563mapStepC6uMEY4));
                    offset = Math.max(TextRange.getEnd-impl(jM1563mapStepC6uMEY3), TextRange.getEnd-impl(jM1563mapStepC6uMEY4));
                    i8--;
                    this = offsetMappingCalculator;
                    fromSource = z;
                }
            }
            i = offset;
            offset = iMin;
        } else {
            i = offset;
        }
        return TextRangeKt.TextRange(offset, i);
    }

    /* JADX INFO: renamed from: mapStep-C6u-MEY, reason: not valid java name */
    private final long m1563mapStepC6uMEY(int offset, int opOffset, int untransformedLen, int transformedLen, boolean fromSource) {
        int i = fromSource ? untransformedLen : transformedLen;
        if (fromSource) {
            untransformedLen = transformedLen;
        }
        if (offset < opOffset) {
            return TextRangeKt.TextRange(offset);
        }
        if (offset == opOffset) {
            return i == 0 ? TextRangeKt.TextRange(opOffset, untransformedLen + opOffset) : TextRangeKt.TextRange(opOffset);
        }
        if (offset < opOffset + i) {
            return untransformedLen == 0 ? TextRangeKt.TextRange(opOffset) : TextRangeKt.TextRange(opOffset, untransformedLen + opOffset);
        }
        return TextRangeKt.TextRange((offset - i) + untransformedLen);
    }

    /* JADX INFO: renamed from: mapFromDest--jx7JFs, reason: not valid java name */
    public final long m1564mapFromDestjx7JFs(int offset) {
        return m1562mapfzxv0v0(offset, false);
    }

    /* JADX INFO: renamed from: mapFromSource--jx7JFs, reason: not valid java name */
    public final long m1565mapFromSourcejx7JFs(int offset) {
        return m1562mapfzxv0v0(offset, true);
    }

    public final void recordEditOperation(int sourceStart, int sourceEnd, int newLength) {
        if (!(newLength >= 0)) {
            InlineClassHelperKt.throwIllegalArgumentException("Expected newLen to be ≥ 0, was " + newLength);
        }
        int iMin = Math.min(sourceStart, sourceEnd);
        int iMax = Math.max(iMin, sourceEnd) - iMin;
        if (iMax >= 2 || iMax != newLength) {
            int i = this.opsSize + 1;
            if (i > OpArray.getSize-impl(this.ops)) {
                this.ops = OpArray.copyOf-pSmdads(this.ops, Math.max(i * 2, OpArray.getSize-impl(this.ops) * 2));
            }
            OpArray.set-impl(this.ops, this.opsSize, iMin, iMax, newLength);
            this.opsSize = i;
        }
    }
}
