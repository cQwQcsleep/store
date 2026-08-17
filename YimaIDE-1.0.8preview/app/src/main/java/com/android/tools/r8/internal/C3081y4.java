package com.android.tools.r8.internal;

import java.util.function.Supplier;

/* JADX INFO: renamed from: com.android.tools.r8.internal.y4, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C3081y4 {
    public static final /* synthetic */ boolean a = true;

    public static boolean a(C2752uB c2752uB, Supplier supplier) {
        if (c2752uB.u1.E0) {
            return ((Boolean) supplier.get()).booleanValue();
        }
        return true;
    }

    public static boolean a(Object obj) {
        if (a || obj != null) {
            return true;
        }
        x1f.a();
        return false;
    }
}
