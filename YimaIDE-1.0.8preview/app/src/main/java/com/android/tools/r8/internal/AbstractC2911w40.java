package com.android.tools.r8.internal;

import java.lang.reflect.AccessibleObject;

/* JADX INFO: renamed from: com.android.tools.r8.internal.w40, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC2911w40 {
    public static final AbstractC2911w40 a;

    static {
        AbstractC2911w40 c2740u40;
        if (UC.a >= 9) {
            try {
                c2740u40 = new C2740u40(AccessibleObject.class.getDeclaredMethod("canAccess", Object.class));
            } catch (NoSuchMethodException unused) {
                c2740u40 = null;
            }
        } else {
            c2740u40 = null;
        }
        if (c2740u40 == null) {
            c2740u40 = new C2825v40();
        }
        a = c2740u40;
    }

    public abstract boolean a(Object obj, AccessibleObject accessibleObject);
}
