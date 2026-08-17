package com.android.tools.r8.internal;

import defpackage.g3c;
import defpackage.h3c;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Mg, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0666Mg implements AU {
    public final /* synthetic */ Constructor a;

    public C0666Mg(Constructor constructor) {
        this.a = constructor;
    }

    @Override // com.android.tools.r8.internal.AU
    public final Object a() {
        try {
            return this.a.newInstance(null);
        } catch (IllegalAccessException e) {
            AbstractC3082y40 abstractC3082y40 = B40.a;
            g3c.a("Unexpected IllegalAccessException occurred (Gson 2.10.1). Certain ReflectionAccessFilter features require Java >= 9 to work correctly. If you are not using ReflectionAccessFilter, report this to the Gson maintainers.", e);
            return null;
        } catch (InstantiationException e2) {
            h3c.a("Failed to invoke constructor '", B40.a(this.a), "' with no args", e2);
            return null;
        } catch (InvocationTargetException e3) {
            g3c.a("Failed to invoke constructor '" + B40.a(this.a) + "' with no args", e3.getCause());
            return null;
        }
    }
}
