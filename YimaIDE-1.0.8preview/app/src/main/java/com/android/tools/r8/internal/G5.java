package com.android.tools.r8.internal;

import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class G5 extends AbstractC2205no {
    public static final /* synthetic */ boolean a = true;

    @Override // com.android.tools.r8.internal.AbstractC2205no
    public final boolean a(Object obj, Object obj2) {
        PW pw = (PW) obj;
        PW pw2 = (PW) obj2;
        if (!a && pw.r != pw2.r) {
            x1f.a();
            return false;
        }
        for (int i = 0; i < pw.c0().size(); i++) {
            if (((C2543rl0) pw.s.get(i)) != ((C2543rl0) pw2.s.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC2205no
    public final int a(Object obj) {
        Iterator<C2543rl0> it = ((PW) obj).c0().iterator();
        int i = 0;
        while (it.hasNext()) {
            i = (i * 13) + it.next().b;
        }
        return i;
    }
}
