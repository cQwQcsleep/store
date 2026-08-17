package com.android.tools.r8.internal;

import defpackage.g3c;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

/* JADX INFO: renamed from: com.android.tools.r8.internal.u40, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2740u40 extends AbstractC2911w40 {
    public final /* synthetic */ Method b;

    public C2740u40(Method method) {
        this.b = method;
    }

    @Override // com.android.tools.r8.internal.AbstractC2911w40
    public final boolean a(Object obj, AccessibleObject accessibleObject) {
        try {
            return ((Boolean) this.b.invoke(accessibleObject, obj)).booleanValue();
        } catch (Exception e) {
            g3c.a("Failed invoking canAccess", e);
            return false;
        }
    }
}
