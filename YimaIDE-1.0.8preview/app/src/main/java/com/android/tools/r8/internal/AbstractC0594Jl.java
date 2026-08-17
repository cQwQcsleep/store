package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0322w2;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Jl, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC0594Jl {
    public static final /* synthetic */ boolean a = true;

    public static C0568Il a(C0322w2 c0322w2, boolean z, com.android.tools.r8.graph.W0.a aVar) {
        int iA = -1;
        AbstractC2004lX abstractC2004lX = null;
        if (aVar == null) {
            return new C0568Il(-1, null);
        }
        C1975l7 c1975l7 = new C1975l7();
        C0542Hl c0542Hl = new C0542Hl(aVar.e, c0322w2, z, c1975l7);
        com.android.tools.r8.graph.O0[] o0Arr = aVar.g;
        for (com.android.tools.r8.graph.O0 o0 : o0Arr) {
            o0.a(c0542Hl);
            if (c0542Hl.b() > 0) {
                break;
            }
            if (o0 instanceof com.android.tools.r8.graph.O0.b) {
                iA = c0542Hl.a();
                abstractC2004lX = (AbstractC2004lX) c1975l7.a();
            }
        }
        return new C0568Il(iA, abstractC2004lX);
    }

    public static boolean a(com.android.tools.r8.graph.O0... o0Arr) {
        for (int length = o0Arr.length - 1; length >= 0; length--) {
            com.android.tools.r8.graph.O0 o0 = o0Arr[length];
            o0.getClass();
            if (o0 instanceof com.android.tools.r8.graph.O0.b) {
                return true;
            }
            if (!a) {
                com.android.tools.r8.graph.O0 o1 = o0Arr[length];
                o1.getClass();
                if (o1 instanceof com.android.tools.r8.graph.S0) {
                    x1f.a();
                    return false;
                }
            }
        }
        return true;
    }
}
