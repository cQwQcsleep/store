package com.android.tools.r8.internal;

import com.reandroid.arsc.chunk.TypeBlock;

/* JADX INFO: renamed from: com.android.tools.r8.internal.xI, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC3016xI {
    public final AbstractC3016xI a;

    public AbstractC3016xI(int i) {
        this.a = null;
    }

    public DI a(int i, String str, int i2, KI ki) {
        KB.c(str, TypeBlock.NAME_name);
        KB.c(ki, "variance");
        AbstractC3016xI abstractC3016xI = this.a;
        if (abstractC3016xI != null) {
            return abstractC3016xI.a(i, str, i2, ki);
        }
        return null;
    }

    public HI b(int i) {
        AbstractC3016xI abstractC3016xI = this.a;
        if (abstractC3016xI != null) {
            return abstractC3016xI.b(i);
        }
        return null;
    }

    public HI c(int i) {
        AbstractC3016xI abstractC3016xI = this.a;
        if (abstractC3016xI != null) {
            return abstractC3016xI.c(i);
        }
        return null;
    }

    public AbstractC3016xI() {
        this(0);
    }

    public MI b() {
        AbstractC3016xI abstractC3016xI = this.a;
        if (abstractC3016xI != null) {
            return abstractC3016xI.b();
        }
        return null;
    }

    public HI a(int i) {
        AbstractC3016xI abstractC3016xI = this.a;
        if (abstractC3016xI != null) {
            return abstractC3016xI.a(i);
        }
        return null;
    }

    public II a(int i, String str) {
        KB.c(str, TypeBlock.NAME_name);
        AbstractC3016xI abstractC3016xI = this.a;
        if (abstractC3016xI != null) {
            return abstractC3016xI.a(i, str);
        }
        return null;
    }

    public InterfaceC2930wI a(C1734iI c1734iI) {
        KB.c(c1734iI, "type");
        AbstractC3016xI abstractC3016xI = this.a;
        if (abstractC3016xI != null) {
            return abstractC3016xI.a(c1734iI);
        }
        return null;
    }

    public final void a() {
        AbstractC3016xI abstractC3016xI = this.a;
        if (abstractC3016xI != null) {
            abstractC3016xI.a();
        }
    }
}
