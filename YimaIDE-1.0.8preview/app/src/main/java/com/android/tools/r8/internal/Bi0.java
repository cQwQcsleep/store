package com.android.tools.r8.internal;

import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Bi0 extends AbstractC3220zi0 {
    public final C0471Es a;
    public final AbstractC3220zi0 b;
    public final Type c;

    public Bi0(C0471Es c0471Es, AbstractC3220zi0 abstractC3220zi0, Type type) {
        this.a = c0471Es;
        this.b = abstractC3220zi0;
        this.c = type;
    }

    @Override // com.android.tools.r8.internal.AbstractC3220zi0
    public final void a(C2754uD c2754uD, Object obj) {
        AbstractC3220zi0 abstractC3220zi0A = this.b;
        Type type = this.c;
        if (obj != null && ((type instanceof Class) || (type instanceof TypeVariable))) {
            type = obj.getClass();
        }
        if (type != this.c) {
            abstractC3220zi0A = this.a.a(new Fj0(type));
            if (abstractC3220zi0A instanceof E40) {
                AbstractC3220zi0 abstractC3220zi0 = this.b;
                while (abstractC3220zi0 instanceof C0445Ds) {
                    AbstractC3220zi0 abstractC3220zi1 = ((C0445Ds) abstractC3220zi0).a;
                    if (abstractC3220zi1 == null) {
                        k2d.a("Adapter for type with cyclic dependency has been used before dependency has been resolved");
                        return;
                    } else if (abstractC3220zi1 == abstractC3220zi0) {
                        break;
                    } else {
                        abstractC3220zi0 = abstractC3220zi1;
                    }
                }
                if (!(abstractC3220zi0 instanceof E40)) {
                    abstractC3220zi0A = this.b;
                }
            }
        }
        abstractC3220zi0A.a(c2754uD, obj);
    }
}
