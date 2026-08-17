package com.android.tools.r8.internal;

import com.android.tools.r8.internal.InterfaceC1959kz;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Qo, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC0778Qo {
    public static InterfaceC2045lz a(InterfaceC2045lz interfaceC2045lz, InterfaceC2665tA interfaceC2665tA) {
        BU it = interfaceC2045lz.c().iterator();
        final C2986wz c2986wz = null;
        while (it.hasNext()) {
            InterfaceC1959kz interfaceC1959kz = (InterfaceC1959kz) it.next();
            int iA = interfaceC1959kz.a();
            Object value = interfaceC1959kz.getValue();
            Object objA = interfaceC2665tA.a(iA, value);
            if (c2986wz == null) {
                if (objA != value) {
                    c2986wz = new C2986wz(interfaceC2045lz.size());
                    C1674he.a(interfaceC2045lz.c(), new Consumer() { // from class: b1c
                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            InterfaceC1959kz interfaceC1959kz2 = (InterfaceC1959kz) obj;
                            c2986wz.a(interfaceC1959kz2.a(), interfaceC1959kz2.getValue());
                        }
                    }, interfaceC1959kz);
                }
            }
            if (objA != null) {
                c2986wz.a(iA, objA);
            } else {
                it.remove();
            }
        }
        if (c2986wz != null) {
            return c2986wz;
        }
        return null;
    }
}
