package com.android.tools.r8.internal;

import java.io.IOException;
import java.lang.reflect.Field;
import java.security.AccessController;
import java.util.HashMap;

/* JADX INFO: renamed from: com.android.tools.r8.internal.mj0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2111mj0 extends AbstractC3220zi0 {
    public final HashMap a = new HashMap();
    public final HashMap b = new HashMap();
    public final HashMap c = new HashMap();

    public C2111mj0(Class cls) {
        try {
            for (Field field : (Field[]) AccessController.doPrivileged(new C2026lj0(cls))) {
                Enum r5 = (Enum) field.get(null);
                String strName = r5.name();
                String string = r5.toString();
                InterfaceC1669hb0 interfaceC1669hb0 = (InterfaceC1669hb0) field.getAnnotation(InterfaceC1669hb0.class);
                if (interfaceC1669hb0 != null) {
                    strName = interfaceC1669hb0.value();
                    for (String str : interfaceC1669hb0.alternate()) {
                        this.a.put(str, r5);
                    }
                }
                this.a.put(strName, r5);
                this.b.put(string, r5);
                this.c.put(r5, strName);
            }
        } catch (IllegalAccessException e) {
            x01.a(e);
            throw null;
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC3220zi0
    public final void a(C2754uD c2754uD, Object obj) throws IOException {
        Enum r2 = (Enum) obj;
        c2754uD.d(r2 == null ? null : (String) this.c.get(r2));
    }
}
