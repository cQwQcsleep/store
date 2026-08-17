package com.android.tools.r8.internal;

import com.android.tools.r8.internal.C0875Uh;
import defpackage.rr9;
import java.util.Iterator;
import java.util.function.Function;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Rh, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0797Rh {
    public static final /* synthetic */ boolean c = true;
    public final int a;
    public final C2986wz b = new C2986wz(16);

    public C0797Rh(int i) {
        if (c || i >= 0) {
            this.a = i;
        } else {
            x1f.a();
            throw null;
        }
    }

    public final void a(int i, int i2) {
        if (!c && i < 0) {
            x1f.a();
            return;
        }
        C0875Uh c0875Uh = (C0875Uh) this.b.computeIfAbsent(Integer.valueOf(i), new Function() { // from class: lkc
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return new C0875Uh(((Integer) obj).intValue());
            }
        });
        c0875Uh.getClass();
        if (!C0875Uh.d && i2 < 0) {
            x1f.a();
        } else {
            c0875Uh.c++;
            c0875Uh.b += i2;
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("params:");
        sb.append(this.a);
        sb.append('\n');
        Iterator it = C1674he.a(this.b.keySet(), new rr9()).iterator();
        while (it.hasNext()) {
            sb.append(this.b.get(((Integer) it.next()).intValue()));
            sb.append('\n');
        }
        return sb.toString();
    }
}
