package kotlin.experimental;

import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0005\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\n\n\u0000\u001a(\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\u008c\u0004b\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005b\u0002\b\u0006\u001a(\u0010\u0007\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\u008c\u0004b\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005b\u0002\b\u0006\u001a(\u0010\b\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\u008c\u0004b\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005b\u0002\b\u0006\u001a \u0010\t\u001a\u00020\u0001*\u00020\u0001H\u0087\u0088\u0004b\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005b\u0002\b\u0006\u001a(\u0010\u0000\u001a\u00020\n*\u00020\n2\u0006\u0010\u0002\u001a\u00020\nH\u0087\u008c\u0004b\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005b\u0002\b\u0006\u001a(\u0010\u0007\u001a\u00020\n*\u00020\n2\u0006\u0010\u0002\u001a\u00020\nH\u0087\u008c\u0004b\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005b\u0002\b\u0006\u001a(\u0010\b\u001a\u00020\n*\u00020\n2\u0006\u0010\u0002\u001a\u00020\nH\u0087\u008c\u0004b\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005b\u0002\b\u0006\u001a \u0010\t\u001a\u00020\n*\u00020\nH\u0087\u0088\u0004b\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005b\u0002\b\u0006¨\u0006\u000b"}, d2 = {"and", "", "other", "Lkotlin/SinceKotlin;", "version", "1.1", "Lkotlin/internal/InlineOnly;", "or", "xor", "inv", "", "kotlin-stdlib"}, k = 2, mv = {2, 4, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
public final class BitwiseOperationsKt {
    private static final byte and(byte b, byte b2) {
        return (byte) (b & b2);
    }

    private static final byte inv(byte b) {
        return (byte) (~b);
    }

    private static final byte or(byte b, byte b2) {
        return (byte) (b | b2);
    }

    private static final byte xor(byte b, byte b2) {
        return (byte) (b ^ b2);
    }

    private static final short and(short s, short s2) {
        return (short) (s & s2);
    }

    private static final short inv(short s) {
        return (short) (~s);
    }

    private static final short or(short s, short s2) {
        return (short) (s | s2);
    }

    private static final short xor(short s, short s2) {
        return (short) (s ^ s2);
    }
}
