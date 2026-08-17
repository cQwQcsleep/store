package com.android.tools.r8.dex.code;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class V {
    public static final /* synthetic */ boolean a = true;

    public static void a(AbstractC0138z1 abstractC0138z1, AbstractC0138z1 abstractC0138z2) {
        boolean z = a;
        if (!z && abstractC0138z1.getClass() != abstractC0138z2.getClass()) {
            x1f.a();
            return;
        }
        if (!z && abstractC0138z1.k() != abstractC0138z2.k()) {
            x1f.a();
        } else {
            if (z || abstractC0138z1.toString().equals(abstractC0138z2.toString())) {
                return;
            }
            x1f.a();
        }
    }
}
