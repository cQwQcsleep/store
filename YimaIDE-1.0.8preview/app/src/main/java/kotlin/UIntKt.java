package kotlin;

import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u0000<\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0005\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\n\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\u001a)\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0087\u0088\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006b\u0002\b\u0007b\u0002\b\b¢\u0006\u0002\u0010\u0003\u001a)\u0010\u0000\u001a\u00020\u0001*\u00020\tH\u0087\u0088\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006b\u0002\b\u0007b\u0002\b\b¢\u0006\u0002\u0010\n\u001a)\u0010\u0000\u001a\u00020\u0001*\u00020\u000bH\u0087\u0088\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006b\u0002\b\u0007b\u0002\b\b¢\u0006\u0002\u0010\f\u001a)\u0010\u0000\u001a\u00020\u0001*\u00020\rH\u0087\u0088\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006b\u0002\b\u0007b\u0002\b\b¢\u0006\u0002\u0010\u000e\u001a)\u0010\u0000\u001a\u00020\u0001*\u00020\u000fH\u0087\u0088\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006b\u0002\b\u0007b\u0002\b\b¢\u0006\u0002\u0010\u0010\u001a)\u0010\u0000\u001a\u00020\u0001*\u00020\u0011H\u0087\u0088\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006b\u0002\b\u0007b\u0002\b\b¢\u0006\u0002\u0010\u0012¨\u0006\u0013"}, d2 = {"toUInt", "Lkotlin/UInt;", "", "(B)I", "Lkotlin/SinceKotlin;", "version", "1.5", "Lkotlin/internal/InlineOnly;", "Lkotlin/internal/IntrinsicConstEvaluation;", "", "(S)I", "", "(I)I", "", "(J)I", "", "(F)I", "", "(D)I", "kotlin-stdlib"}, k = 2, mv = {2, 4, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
public final class UIntKt {
    private static final int toUInt(long j) {
        return UInt.m133constructorimpl((int) j);
    }

    private static final int toUInt(short s) {
        return UInt.m133constructorimpl(s);
    }

    private static final int toUInt(int i) {
        return UInt.m133constructorimpl(i);
    }

    private static final int toUInt(byte b) {
        return UInt.m133constructorimpl(b);
    }

    private static final int toUInt(float f) {
        return UnsignedKt.doubleToUInt(f);
    }

    private static final int toUInt(double d) {
        return UnsignedKt.doubleToUInt(d);
    }
}
