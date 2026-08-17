package com.android.tools.r8.synthesis;

import defpackage.sla;
import java.util.ArrayList;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Q {
    public static final /* synthetic */ boolean c = true;
    public int a = 1;
    public ArrayList b = new ArrayList();

    public final S.b a(String str) {
        if (!c && str.isEmpty()) {
            x1f.a();
            return null;
        }
        int i = this.a;
        this.a = i + 1;
        return a(new U(i, str, false));
    }

    public final S.b b(String str) {
        int i = this.a;
        this.a = i + 1;
        return a(new V(i, str, false));
    }

    public final S.b c(String str) {
        int i = this.a;
        this.a = i + 1;
        return a(new V(i, str, true));
    }

    public final S.b a(S.b bVar) {
        this.b.add(bVar);
        if (this.b.size() == bVar.c()) {
            return bVar;
        }
        sla.a("Invalid synthetic kind id: ", bVar.c());
        return null;
    }
}
