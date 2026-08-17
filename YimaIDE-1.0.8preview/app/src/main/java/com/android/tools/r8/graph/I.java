package com.android.tools.r8.graph;

import com.android.tools.r8.internal.AbstractC3175z9;
import com.android.tools.r8.internal.C0972Ya;
import com.android.tools.r8.internal.Wf0;
import defpackage.fd6;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class I {
    public static H a(B5 b5, C0972Ya c0972Ya, String str, C0333y c0333y) {
        StringBuilder sb = new StringBuilder("Invalid try catch range for ");
        sb.append(Wf0.a(", ", c0972Ya.c, new fd6()));
        sb.append(": ");
        sb.append(str);
        sb.append(".");
        if (c0333y.o()) {
            sb.append(" In later version of R8, the method may be assumed not reachable.");
        }
        return new H(b5, sb.toString());
    }

    public static H a(B5 b5, int i, AbstractC3175z9 abstractC3175z9, String str, C0333y c0333y) {
        StringBuilder sb = new StringBuilder("Invalid stack map table at instruction index ");
        sb.append(i);
        sb.append(": ");
        sb.append(abstractC3175z9);
        sb.append(", error: ");
        sb.append(str);
        sb.append(".");
        if (c0333y.o()) {
            sb.append(" In later version of R8, the method may be assumed not reachable.");
        }
        return new H(b5, sb.toString());
    }
}
