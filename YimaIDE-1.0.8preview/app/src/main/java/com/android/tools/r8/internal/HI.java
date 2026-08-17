package com.android.tools.r8.internal;

import com.reandroid.arsc.chunk.TypeBlock;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class HI {
    public final HI a;

    public HI(int i) {
        this.a = null;
    }

    public HI a(int i, KI ki) {
        KB.c(ki, "variance");
        HI hi = this.a;
        if (hi != null) {
            return hi.a(i, ki);
        }
        return null;
    }

    public void b(String str) {
        KB.c(str, TypeBlock.NAME_name);
        HI hi = this.a;
        if (hi != null) {
            hi.b(str);
        }
    }

    public void c(int i) {
        HI hi = this.a;
        if (hi != null) {
            hi.c(i);
        }
    }

    public HI() {
        this(0);
    }

    public void b() {
        HI hi = this.a;
        if (hi != null) {
            hi.b();
        }
    }

    public HI b(int i) {
        HI hi = this.a;
        if (hi != null) {
            return hi.b(i);
        }
        return null;
    }

    public void a(String str) {
        KB.c(str, TypeBlock.NAME_name);
        HI hi = this.a;
        if (hi != null) {
            hi.a(str);
        }
    }

    public HI a(int i) {
        HI hi = this.a;
        if (hi != null) {
            return hi.a(i);
        }
        return null;
    }

    public HI a(int i, String str) {
        HI hi = this.a;
        if (hi != null) {
            return hi.a(i, str);
        }
        return null;
    }

    public CI a(C1734iI c1734iI) {
        KB.c(c1734iI, "type");
        HI hi = this.a;
        if (hi != null) {
            return hi.a(c1734iI);
        }
        return null;
    }

    public final void a() {
        HI hi = this.a;
        if (hi != null) {
            hi.a();
        }
    }
}
