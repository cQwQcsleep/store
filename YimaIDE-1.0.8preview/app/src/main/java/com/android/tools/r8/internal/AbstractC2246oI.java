package com.android.tools.r8.internal;

import com.reandroid.arsc.chunk.TypeBlock;

/* JADX INFO: renamed from: com.android.tools.r8.internal.oI, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC2246oI {
    public final AbstractC2246oI a;

    public AbstractC2246oI(int i) {
        this.a = null;
    }

    public DI a(int i, String str, int i2, KI ki) {
        KB.c(str, TypeBlock.NAME_name);
        KB.c(ki, "variance");
        AbstractC2246oI abstractC2246oI = this.a;
        if (abstractC2246oI != null) {
            return abstractC2246oI.a(i, str, i2, ki);
        }
        return null;
    }

    public HI b(int i) {
        AbstractC2246oI abstractC2246oI = this.a;
        if (abstractC2246oI != null) {
            return abstractC2246oI.b(i);
        }
        return null;
    }

    public HI c(int i) {
        AbstractC2246oI abstractC2246oI = this.a;
        if (abstractC2246oI != null) {
            return abstractC2246oI.c(i);
        }
        return null;
    }

    public AbstractC2246oI() {
        this(0);
    }

    public final void b() {
        AbstractC2246oI abstractC2246oI = this.a;
        if (abstractC2246oI != null) {
            abstractC2246oI.b();
        }
    }

    public MI c() {
        AbstractC2246oI abstractC2246oI = this.a;
        if (abstractC2246oI != null) {
            return abstractC2246oI.c();
        }
        return null;
    }

    public HI a(int i) {
        AbstractC2246oI abstractC2246oI = this.a;
        if (abstractC2246oI != null) {
            return abstractC2246oI.a(i);
        }
        return null;
    }

    public II a(int i, String str) {
        KB.c(str, TypeBlock.NAME_name);
        AbstractC2246oI abstractC2246oI = this.a;
        if (abstractC2246oI != null) {
            return abstractC2246oI.a(i, str);
        }
        return null;
    }

    public C1053aI a() {
        AbstractC2246oI abstractC2246oI = this.a;
        if (abstractC2246oI != null) {
            return abstractC2246oI.a();
        }
        return null;
    }

    public InterfaceC2160nI a(C1734iI c1734iI) {
        KB.c(c1734iI, "type");
        AbstractC2246oI abstractC2246oI = this.a;
        if (abstractC2246oI != null) {
            return abstractC2246oI.a(c1734iI);
        }
        return null;
    }
}
