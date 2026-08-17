package com.android.tools.r8.internal;

import defpackage.f3i;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.qW, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2431qW extends K2 {
    public final List c;
    public final Runnable d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C2431qW(C2516rW c2516rW, InterfaceC2260oW interfaceC2260oW, Runnable runnable) {
        super(c2516rW);
        List listSingletonList = Collections.singletonList(interfaceC2260oW);
        this.c = listSingletonList;
        this.d = runnable;
    }

    @Override // com.android.tools.r8.internal.K2, com.android.tools.r8.internal.J2
    public final void a(Object obj, String str) {
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            if (((InterfaceC2260oW) it.next()).a(str, new f3i(this), obj)) {
                return;
            }
        }
        super.a(obj, str);
        throw null;
    }

    public C2431qW(C2516rW c2516rW, AbstractC0551Hu abstractC0551Hu, Runnable runnable) {
        super(c2516rW);
        this.c = abstractC0551Hu;
        this.d = runnable;
    }

    public final void a(Object obj) {
    }

    @Override // com.android.tools.r8.internal.K2, com.android.tools.r8.internal.J2
    public final J2 a(String str) {
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            J2 j2A = ((InterfaceC2260oW) it.next()).a(new f3i(this), str);
            if (j2A != null) {
                return j2A;
            }
        }
        super.a(str);
        throw null;
    }

    @Override // com.android.tools.r8.internal.K2, com.android.tools.r8.internal.J2
    public final void a(String str, String str2, String str3) {
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            if (((InterfaceC2260oW) it.next()).a(str, str2, str3, new f3i(this))) {
                return;
            }
        }
        super.a(str, str2, str3);
        throw null;
    }

    @Override // com.android.tools.r8.internal.K2, com.android.tools.r8.internal.J2
    public final J2 a(String str, String str2) {
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            J2 j2A = ((InterfaceC2260oW) it.next()).a(str, (Consumer) new f3i(this), str2);
            if (j2A != null) {
                return j2A;
            }
        }
        super.a(str, str2);
        throw null;
    }

    @Override // com.android.tools.r8.internal.J2
    public final void a() {
        this.d.run();
        super.a();
    }
}
