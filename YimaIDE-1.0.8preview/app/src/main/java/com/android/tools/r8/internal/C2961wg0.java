package com.android.tools.r8.internal;

import com.android.tools.r8.dex.code.AbstractC0138z1;
import java.util.HashMap;

/* JADX INFO: renamed from: com.android.tools.r8.internal.wg0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C2961wg0 {
    public static final /* synthetic */ boolean c = true;
    public final HashMap a = new HashMap();
    public final HashMap b = new HashMap();

    public void a(com.android.tools.r8.dex.code.b4 b4Var) {
        int iQ = b4Var.q();
        C2875vg0 c2875vg0 = (C2875vg0) this.b.get(Integer.valueOf(iQ));
        if (c2875vg0 == null) {
            this.a.put(Integer.valueOf(iQ), b4Var);
            return;
        }
        int[] iArrJ = b4Var.J();
        int[] iArr = new int[iArrJ.length];
        for (int i = 0; i < iArrJ.length; i++) {
            iArr[i] = c2875vg0.a + iArrJ[i];
        }
        c2875vg0.b = iArr;
        c2875vg0.c = b4Var.I();
    }

    public int[] b(int i) {
        return ((C2875vg0) this.b.get(Integer.valueOf(i))).c;
    }

    public void a(AbstractC0138z1 abstractC0138z1) {
        int iQ = abstractC0138z1.q();
        int iS = abstractC0138z1.s() + iQ;
        this.b.put(Integer.valueOf(iS), new C2875vg0(iQ));
        if (this.a.containsKey(Integer.valueOf(iS))) {
            a((com.android.tools.r8.dex.code.b4) this.a.remove(Integer.valueOf(iS)));
        }
    }

    public int[] a(int i) {
        return ((C2875vg0) this.b.get(Integer.valueOf(i))).b;
    }
}
