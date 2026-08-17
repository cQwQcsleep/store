package kotlin;

import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import kotlin.jvm.internal.DoubleCompanionObject;
import kotlin.jvm.internal.FloatCompanionObject;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u00004\n\u0000\n\u0002\u0010\u000b\n\u0002\u0010\u0006\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u000b\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0087\u0088\u0004b\u0002\b\u0003\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u0004H\u0087\u0088\u0004b\u0002\b\u0003\u001a\u0012\u0010\u0005\u001a\u00020\u0001*\u00020\u0002H\u0087\u0088\u0004b\u0002\b\u0003\u001a\u0012\u0010\u0005\u001a\u00020\u0001*\u00020\u0004H\u0087\u0088\u0004b\u0002\b\u0003\u001a\u0012\u0010\u0006\u001a\u00020\u0001*\u00020\u0002H\u0087\u0088\u0004b\u0002\b\u0003\u001a\u0012\u0010\u0006\u001a\u00020\u0001*\u00020\u0004H\u0087\u0088\u0004b\u0002\b\u0003\u001a \u0010\u0007\u001a\u00020\b*\u00020\u0002H\u0087\u0088\u0004b\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000bb\u0002\b\u0003\u001a \u0010\f\u001a\u00020\b*\u00020\u0002H\u0087\u0088\u0004b\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000bb\u0002\b\u0003\u001a(\u0010\r\u001a\u00020\u0002*\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\bH\u0087\u0088\u0004b\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000bb\u0002\b\u0003\u001a \u0010\u0007\u001a\u00020\u0010*\u00020\u0004H\u0087\u0088\u0004b\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000bb\u0002\b\u0003\u001a \u0010\f\u001a\u00020\u0010*\u00020\u0004H\u0087\u0088\u0004b\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000bb\u0002\b\u0003\u001a(\u0010\r\u001a\u00020\u0004*\u00020\u00112\u0006\u0010\u000f\u001a\u00020\u0010H\u0087\u0088\u0004b\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000bb\u0002\b\u0003\u001a \u0010\u0012\u001a\u00020\u0010*\u00020\u0010H\u0087\u0088\u0004b\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u0013b\u0002\b\u0003\u001a \u0010\u0014\u001a\u00020\u0010*\u00020\u0010H\u0087\u0088\u0004b\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u0013b\u0002\b\u0003\u001a \u0010\u0015\u001a\u00020\u0010*\u00020\u0010H\u0087\u0088\u0004b\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u0013b\u0002\b\u0003\u001a \u0010\u0016\u001a\u00020\u0010*\u00020\u0010H\u0087\u0088\u0004b\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u0013b\u0002\b\u0003\u001a \u0010\u0017\u001a\u00020\u0010*\u00020\u0010H\u0087\u0088\u0004b\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u0013b\u0002\b\u0003\u001a(\u0010\u0018\u001a\u00020\u0010*\u00020\u00102\u0006\u0010\u0019\u001a\u00020\u0010H\u0087\u0088\u0004b\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u001ab\u0002\b\u0003\u001a(\u0010\u001b\u001a\u00020\u0010*\u00020\u00102\u0006\u0010\u0019\u001a\u00020\u0010H\u0087\u0088\u0004b\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u001ab\u0002\b\u0003\u001a \u0010\u0012\u001a\u00020\u0010*\u00020\bH\u0087\u0088\u0004b\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u0013b\u0002\b\u0003\u001a \u0010\u0014\u001a\u00020\u0010*\u00020\bH\u0087\u0088\u0004b\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u0013b\u0002\b\u0003\u001a \u0010\u0015\u001a\u00020\u0010*\u00020\bH\u0087\u0088\u0004b\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u0013b\u0002\b\u0003\u001a \u0010\u0016\u001a\u00020\b*\u00020\bH\u0087\u0088\u0004b\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u0013b\u0002\b\u0003\u001a \u0010\u0017\u001a\u00020\b*\u00020\bH\u0087\u0088\u0004b\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u0013b\u0002\b\u0003\u001a(\u0010\u0018\u001a\u00020\b*\u00020\b2\u0006\u0010\u0019\u001a\u00020\u0010H\u0087\u0088\u0004b\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u001ab\u0002\b\u0003\u001a(\u0010\u001b\u001a\u00020\b*\u00020\b2\u0006\u0010\u0019\u001a\u00020\u0010H\u0087\u0088\u0004b\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u001ab\u0002\b\u0003¨\u0006\u001c"}, d2 = {"isNaN", "", "", "Lkotlin/internal/InlineOnly;", "", "isInfinite", "isFinite", "toBits", "", "Lkotlin/SinceKotlin;", "version", "1.2", "toRawBits", "fromBits", "Lkotlin/Double$Companion;", "bits", "", "Lkotlin/Float$Companion;", "countOneBits", "1.4", "countLeadingZeroBits", "countTrailingZeroBits", "takeHighestOneBit", "takeLowestOneBit", "rotateLeft", "bitCount", "1.6", "rotateRight", "kotlin-stdlib"}, k = 5, mv = {2, 4, 0}, xi = EditorColorScheme.TEXT_INLAY_HINT_BACKGROUND, xs = "kotlin/NumbersKt")
class NumbersKt__NumbersJVMKt extends NumbersKt__FloorDivModKt {
    private static final int countLeadingZeroBits(int i) {
        return Integer.numberOfLeadingZeros(i);
    }

    private static final int countOneBits(int i) {
        return Integer.bitCount(i);
    }

    private static final int countTrailingZeroBits(int i) {
        return Integer.numberOfTrailingZeros(i);
    }

    private static final double fromBits(DoubleCompanionObject doubleCompanionObject, long j) {
        doubleCompanionObject.getClass();
        return Double.longBitsToDouble(j);
    }

    private static final boolean isFinite(double d) {
        return Math.abs(d) <= Double.MAX_VALUE;
    }

    private static final boolean isInfinite(double d) {
        return Double.isInfinite(d);
    }

    private static final boolean isNaN(double d) {
        return Double.isNaN(d);
    }

    private static final int rotateLeft(int i, int i2) {
        return Integer.rotateLeft(i, i2);
    }

    private static final int rotateRight(int i, int i2) {
        return Integer.rotateRight(i, i2);
    }

    private static final int takeHighestOneBit(int i) {
        return Integer.highestOneBit(i);
    }

    private static final int takeLowestOneBit(int i) {
        return Integer.lowestOneBit(i);
    }

    private static final long toBits(double d) {
        return Double.doubleToLongBits(d);
    }

    private static final long toRawBits(double d) {
        return Double.doubleToRawLongBits(d);
    }

    private static final int countLeadingZeroBits(long j) {
        return Long.numberOfLeadingZeros(j);
    }

    private static final int countOneBits(long j) {
        return Long.bitCount(j);
    }

    private static final int countTrailingZeroBits(long j) {
        return Long.numberOfTrailingZeros(j);
    }

    private static final boolean isInfinite(float f) {
        return Float.isInfinite(f);
    }

    private static final boolean isNaN(float f) {
        return Float.isNaN(f);
    }

    private static final long rotateLeft(long j, int i) {
        return Long.rotateLeft(j, i);
    }

    private static final long rotateRight(long j, int i) {
        return Long.rotateRight(j, i);
    }

    private static final long takeHighestOneBit(long j) {
        return Long.highestOneBit(j);
    }

    private static final long takeLowestOneBit(long j) {
        return Long.lowestOneBit(j);
    }

    private static final int toBits(float f) {
        return Float.floatToIntBits(f);
    }

    private static final int toRawBits(float f) {
        return Float.floatToRawIntBits(f);
    }

    private static final float fromBits(FloatCompanionObject floatCompanionObject, int i) {
        floatCompanionObject.getClass();
        return Float.intBitsToFloat(i);
    }

    private static final boolean isFinite(float f) {
        return Math.abs(f) <= Float.MAX_VALUE;
    }
}
