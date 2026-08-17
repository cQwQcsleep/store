package kotlin.coroutines.jvm.internal;

import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u0000`\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0005\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\f\n\u0000\u001a$\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0081\u0080\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006b\u0002\b\u0007\u001a$\u0010\b\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\nH\u0081\u0080\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006b\u0002\b\u0007\u001a$\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0002\u001a\u00020\rH\u0081\u0080\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006b\u0002\b\u0007\u001a$\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0002\u001a\u00020\u0010H\u0081\u0080\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006b\u0002\b\u0007\u001a$\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u0013H\u0081\u0080\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006b\u0002\b\u0007\u001a$\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0002\u001a\u00020\u0016H\u0081\u0080\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006b\u0002\b\u0007\u001a$\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0002\u001a\u00020\u0019H\u0081\u0080\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006b\u0002\b\u0007\u001a$\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0002\u001a\u00020\u001cH\u0081\u0080\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006b\u0002\b\u0007¨\u0006\u001d"}, d2 = {"boxBoolean", "Ljava/lang/Boolean;", "primitive", "", "Lkotlin/SinceKotlin;", "version", "1.3", "Lkotlin/PublishedApi;", "boxByte", "Ljava/lang/Byte;", "", "boxShort", "Ljava/lang/Short;", "", "boxInt", "Ljava/lang/Integer;", "", "boxLong", "Ljava/lang/Long;", "", "boxFloat", "Ljava/lang/Float;", "", "boxDouble", "Ljava/lang/Double;", "", "boxChar", "Ljava/lang/Character;", "", "kotlin-stdlib"}, k = 2, mv = {2, 4, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
public final class Boxing {
    public static final Boolean boxBoolean(boolean z) {
        return Boolean.valueOf(z);
    }

    public static final Byte boxByte(byte b) {
        return Byte.valueOf(b);
    }

    public static final Character boxChar(char c) {
        return new Character(c);
    }

    public static final Double boxDouble(double d) {
        return new Double(d);
    }

    public static final Float boxFloat(float f) {
        return new Float(f);
    }

    public static final Integer boxInt(int i) {
        return new Integer(i);
    }

    public static final Long boxLong(long j) {
        return new Long(j);
    }

    public static final Short boxShort(short s) {
        return new Short(s);
    }
}
