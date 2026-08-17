package com.android.tools.r8.internal;

import com.android.tools.r8.internal.C2191ng0;
import java.io.Serializable;
import java.util.function.Supplier;

/* JADX INFO: renamed from: com.android.tools.r8.internal.ng0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C2191ng0 {
    public static /* synthetic */ Object a(InterfaceC2706th0 interfaceC2706th0) {
        try {
            return interfaceC2706th0.get();
        } catch (Throwable th) {
            rc6.a(th);
            return null;
        }
    }

    public static <T, E extends Throwable> Supplier<T> b(final InterfaceC2706th0<T, E> interfaceC2706th0) {
        InterfaceC2105mg0 interfaceC2105mg0 = new InterfaceC2105mg0() { // from class: vvh
            @Override // java.util.function.Supplier
            public final Object get() {
                return C2191ng0.a(interfaceC2706th0);
            }
        };
        return interfaceC2105mg0 instanceof Serializable ? new C2277og0(interfaceC2105mg0) : new C2363pg0(interfaceC2105mg0);
    }
}
