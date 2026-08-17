package com.android.tools.r8.internal;

import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Do, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC0441Do {
    public static final InterfaceC1648hI a(Collection collection, C1734iI c1734iI) {
        KB.c(collection, "<this>");
        KB.c(c1734iI, "type");
        Iterator it = collection.iterator();
        InterfaceC1648hI interfaceC1648hI = null;
        while (it.hasNext()) {
            InterfaceC1648hI interfaceC1648hI2 = (InterfaceC1648hI) it.next();
            if (KB.a(interfaceC1648hI2.getType(), c1734iI)) {
                if (interfaceC1648hI != null) {
                    qu7.a("Multiple extensions handle the same extension type: ", c1734iI);
                    return null;
                }
                interfaceC1648hI = interfaceC1648hI2;
            }
        }
        if (interfaceC1648hI != null) {
            return interfaceC1648hI;
        }
        qu7.a("No extensions handle the extension type: ", c1734iI);
        return null;
    }
}
