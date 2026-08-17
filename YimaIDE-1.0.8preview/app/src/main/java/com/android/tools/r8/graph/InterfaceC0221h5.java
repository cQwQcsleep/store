package com.android.tools.r8.graph;

import com.android.tools.r8.graph.InterfaceC0221h5;
import com.android.tools.r8.naming.AbstractC3345r0;
import com.android.tools.r8.utils.structural.AbstractC3519a;

/* JADX INFO: renamed from: com.android.tools.r8.graph.h5, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface InterfaceC0221h5 extends com.android.tools.r8.utils.structural.x {
    default int a(InterfaceC0221h5 interfaceC0221h5, AbstractC3345r0 abstractC3345r0) {
        InterfaceC0221h5 interfaceC0221h6 = (InterfaceC0221h5) R();
        com.android.tools.r8.utils.structural.v vVar = new com.android.tools.r8.utils.structural.v() { // from class: h2h
            @Override // com.android.tools.r8.utils.structural.v
            public final int a(Object obj, Object obj2, AbstractC3519a abstractC3519a) {
                return ((InterfaceC0221h5) obj).a((InterfaceC0221h5) obj2, abstractC3519a);
            }
        };
        if (interfaceC0221h6 == interfaceC0221h5) {
            return 0;
        }
        return vVar.a(interfaceC0221h6, interfaceC0221h5, new com.android.tools.r8.utils.structural.d(abstractC3345r0));
    }
}
