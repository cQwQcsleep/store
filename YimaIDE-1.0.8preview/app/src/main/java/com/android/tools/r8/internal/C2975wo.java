package com.android.tools.r8.internal;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: renamed from: com.android.tools.r8.internal.wo, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2975wo implements Ai0, Cloneable {
    public static final C2975wo d = new C2975wo();
    public final List b;
    public final List c;

    public C2975wo() {
        List list = Collections.EMPTY_LIST;
        this.b = list;
        this.c = list;
    }

    @Override // com.android.tools.r8.internal.Ai0
    public final AbstractC3220zi0 a(C0471Es c0471Es, Fj0 fj0) {
        boolean z;
        boolean zA = a(fj0.a);
        boolean z2 = false;
        if (zA) {
            z = true;
        } else {
            Iterator it = this.b.iterator();
            if (it.hasNext()) {
                it.next().getClass();
                throw new ClassCastException();
            }
            z = false;
        }
        if (zA) {
            z2 = true;
        } else {
            Iterator it2 = this.c.iterator();
            if (it2.hasNext()) {
                it2.next().getClass();
                throw new ClassCastException();
            }
        }
        if (z || z2) {
            return new C2889vo(this, z2, z, c0471Es, fj0);
        }
        return null;
    }

    public final Object clone() {
        try {
            return (C2975wo) super.clone();
        } catch (CloneNotSupportedException e) {
            x01.a(e);
            return null;
        }
    }

    public static boolean a(Class cls) {
        if (Enum.class.isAssignableFrom(cls) || (cls.getModifiers() & 8) != 0) {
            return false;
        }
        return cls.isAnonymousClass() || cls.isLocalClass();
    }
}
