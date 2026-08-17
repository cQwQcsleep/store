package kotlin;

import io.github.rosemoe.sora.langs.textmate.folding.IndentRange;
import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0010\b\n\u0002\u0010\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\n\n\u0000\u001a \u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0087\u0088\u0004b\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005b\u0002\b\u0006\u001a \u0010\u0007\u001a\u00020\u0001*\u00020\u0002H\u0087\u0088\u0004b\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005b\u0002\b\u0006\u001a \u0010\b\u001a\u00020\u0001*\u00020\u0002H\u0087\u0088\u0004b\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005b\u0002\b\u0006\u001a \u0010\t\u001a\u00020\u0002*\u00020\u0002H\u0087\u0088\u0004b\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005b\u0002\b\u0006\u001a \u0010\n\u001a\u00020\u0002*\u00020\u0002H\u0087\u0088\u0004b\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005b\u0002\b\u0006\u001a$\u0010\u000b\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\f\u001a\u00020\u0001H\u0087\u0080\u0004b\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\r\u001a$\u0010\u000e\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\f\u001a\u00020\u0001H\u0087\u0080\u0004b\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\r\u001a \u0010\u0000\u001a\u00020\u0001*\u00020\u000fH\u0087\u0088\u0004b\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005b\u0002\b\u0006\u001a \u0010\u0007\u001a\u00020\u0001*\u00020\u000fH\u0087\u0088\u0004b\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005b\u0002\b\u0006\u001a \u0010\b\u001a\u00020\u0001*\u00020\u000fH\u0087\u0088\u0004b\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005b\u0002\b\u0006\u001a \u0010\t\u001a\u00020\u000f*\u00020\u000fH\u0087\u0088\u0004b\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005b\u0002\b\u0006\u001a \u0010\n\u001a\u00020\u000f*\u00020\u000fH\u0087\u0088\u0004b\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005b\u0002\b\u0006\u001a$\u0010\u000b\u001a\u00020\u000f*\u00020\u000f2\u0006\u0010\f\u001a\u00020\u0001H\u0087\u0080\u0004b\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\r\u001a$\u0010\u000e\u001a\u00020\u000f*\u00020\u000f2\u0006\u0010\f\u001a\u00020\u0001H\u0087\u0080\u0004b\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\r¨\u0006\u0010"}, d2 = {"countOneBits", "", "", "Lkotlin/SinceKotlin;", "version", "1.4", "Lkotlin/internal/InlineOnly;", "countLeadingZeroBits", "countTrailingZeroBits", "takeHighestOneBit", "takeLowestOneBit", "rotateLeft", "bitCount", "1.6", "rotateRight", "", "kotlin-stdlib"}, k = 5, mv = {2, 4, 0}, xi = EditorColorScheme.TEXT_INLAY_HINT_BACKGROUND, xs = "kotlin/NumbersKt")
class NumbersKt__NumbersKt extends NumbersKt__NumbersJVMKt {
    private static final int countLeadingZeroBits(short s) {
        return Integer.numberOfLeadingZeros(s & UShort.MAX_VALUE) - 16;
    }

    private static final int countOneBits(short s) {
        return Integer.bitCount(s & UShort.MAX_VALUE);
    }

    private static final int countTrailingZeroBits(short s) {
        return Integer.numberOfTrailingZeros(s | 65536);
    }

    public static final short rotateLeft(short s, int i) {
        int i2 = i & 15;
        return (short) (((s & IndentRange.MAX_FOLDING_REGIONS) >>> (16 - i2)) | (s << i2));
    }

    public static final short rotateRight(short s, int i) {
        int i2 = i & 15;
        return (short) (((s & IndentRange.MAX_FOLDING_REGIONS) >>> i2) | (s << (16 - i2)));
    }

    private static final short takeHighestOneBit(short s) {
        return (short) Integer.highestOneBit(s & UShort.MAX_VALUE);
    }

    private static final byte takeLowestOneBit(byte b) {
        return (byte) Integer.lowestOneBit(b);
    }

    private static final short takeLowestOneBit(short s) {
        return (short) Integer.lowestOneBit(s);
    }

    private static final int countTrailingZeroBits(byte b) {
        return Integer.numberOfTrailingZeros(b | UByte.MIN_VALUE);
    }

    private static final int countOneBits(byte b) {
        return Integer.bitCount(b & UByte.MAX_VALUE);
    }

    private static final byte takeHighestOneBit(byte b) {
        return (byte) Integer.highestOneBit(b & UByte.MAX_VALUE);
    }

    private static final int countLeadingZeroBits(byte b) {
        return Integer.numberOfLeadingZeros(b & UByte.MAX_VALUE) - 24;
    }

    public static final byte rotateLeft(byte b, int i) {
        int i2 = i & 7;
        return (byte) (((b & 255) >>> (8 - i2)) | (b << i2));
    }

    public static final byte rotateRight(byte b, int i) {
        int i2 = i & 7;
        return (byte) (((b & 255) >>> i2) | (b << (8 - i2)));
    }
}
