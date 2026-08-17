package com.android.tools.r8.internal;

import defpackage.ylf;
import java.util.Iterator;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Wh, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC0927Wh implements InterfaceC2260oW {
    public boolean a(Object obj, String str) {
        Iterator it = b().iterator();
        while (it.hasNext()) {
            if (((InterfaceC2260oW) it.next()).a(str, new ylf(this), obj)) {
                return true;
            }
        }
        return false;
    }

    public abstract AbstractC0551Hu b();

    @Override // com.android.tools.r8.internal.InterfaceC2260oW
    public boolean a() {
        Iterator it = b().iterator();
        while (it.hasNext()) {
            if (((InterfaceC2260oW) it.next()).a()) {
                return true;
            }
        }
        return false;
    }

    @Override // com.android.tools.r8.internal.InterfaceC2260oW
    public final boolean a(String str, Consumer consumer, Object obj) {
        return a(obj, str);
    }

    @Override // com.android.tools.r8.internal.InterfaceC2260oW
    public final boolean a(String str, String str2, String str3, Consumer consumer) {
        return a(str, str2, str3);
    }

    @Override // com.android.tools.r8.internal.InterfaceC2260oW
    public final J2 a(Consumer consumer, String str) {
        return a(str);
    }

    @Override // com.android.tools.r8.internal.InterfaceC2260oW
    public final J2 a(String str, Consumer consumer, String str2) {
        return a(str, str2);
    }

    public final void a(Object obj) {
    }

    public final boolean a(String str, String str2, String str3) {
        Iterator it = b().iterator();
        while (it.hasNext()) {
            if (((InterfaceC2260oW) it.next()).a(str, str2, str3, new ylf(this))) {
                return true;
            }
        }
        return false;
    }

    public J2 a(String str) {
        Iterator it = b().iterator();
        while (it.hasNext()) {
            J2 j2A = ((InterfaceC2260oW) it.next()).a(new ylf(this), str);
            if (j2A != null) {
                return j2A;
            }
        }
        return null;
    }

    public final J2 a(String str, String str2) {
        Iterator it = b().iterator();
        while (it.hasNext()) {
            J2 j2A = ((InterfaceC2260oW) it.next()).a(str, (Consumer) new ylf(this), str2);
            if (j2A != null) {
                return j2A;
            }
        }
        return null;
    }
}
