package j$.util.stream;

import java.util.Collections;
import java.util.EnumSet;

/* renamed from: j$.util.stream.k, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
public abstract class AbstractC0145k {
    public static final /* synthetic */ int a = 0;

    static {
        EnumC0135i enumC0135i = EnumC0135i.CONCURRENT;
        EnumC0135i enumC0135i2 = EnumC0135i.UNORDERED;
        EnumC0135i enumC0135i3 = EnumC0135i.IDENTITY_FINISH;
        Collections.unmodifiableSet(EnumSet.of(enumC0135i, enumC0135i2, enumC0135i3));
        Collections.unmodifiableSet(EnumSet.of(enumC0135i, enumC0135i2));
        Collections.unmodifiableSet(EnumSet.of(enumC0135i3));
        Collections.unmodifiableSet(EnumSet.of(enumC0135i2, enumC0135i3));
        Collections.emptySet();
        Collections.unmodifiableSet(EnumSet.of(enumC0135i2));
    }

    static void a(double[] dArr, double d) {
        double d2 = d - dArr[1];
        double d3 = dArr[0];
        double d4 = d3 + d2;
        dArr[1] = (d4 - d3) - d2;
        dArr[0] = d4;
    }
}
