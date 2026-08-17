package com.android.tools.r8.kotlin;

import com.android.tools.r8.graph.B1;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.internal.AH;
import com.android.tools.r8.internal.C3015xH;
import com.android.tools.r8.internal.C3099yH;
import com.android.tools.r8.internal.FH;
import com.android.tools.r8.internal.IH;
import com.android.tools.r8.internal.T40;
import com.android.tools.r8.kotlin.AbstractC3295m;
import com.android.tools.r8.shaking.InterfaceC3369b0;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.kotlin.m, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC3295m implements InterfaceC3369b0 {
    public static final T40 a = T40.i;

    public static AbstractC3295m a(AH ah, B1 b1) {
        if (ah instanceof IH) {
            IH ih = (IH) ah;
            String str = ih.a;
            return new C3292j(u0.a(str, b1, str), ih.b);
        }
        if (ah instanceof FH) {
            FH fh = (FH) ah;
            String str2 = fh.a;
            return new C3293k(u0.a(str2, b1, str2), fh.b);
        }
        if (!(ah instanceof C3099yH)) {
            return ah instanceof AH.a ? C3291i.a((AH.a) ah, b1) : new C3294l(ah);
        }
        C3015xH c3015xH = ((C3099yH) ah).a;
        return new C3290h(new C3296n(u0.a(c3015xH.b(), b1, c3015xH.b()), a(c3015xH.a(), b1)));
    }

    public abstract boolean b(Consumer consumer, C0333y c0333y);

    public static Map a(Map map, final B1 b1) {
        if (map.isEmpty()) {
            return a;
        }
        final LinkedHashMap linkedHashMap = new LinkedHashMap();
        map.forEach(new BiConsumer() { // from class: ikh
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                linkedHashMap.put((String) obj, AbstractC3295m.a((AH) obj2, b1));
            }
        });
        return linkedHashMap;
    }
}
