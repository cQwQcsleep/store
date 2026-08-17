package com.android.tools.r8.references;

import defpackage.x0g;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class PrimitiveReference implements TypeReference {
    static final a a = new a();
    static final b b = new b();
    static final c c = new c();
    static final d d = new d();
    static final e e = new e();
    static final f f = new f();
    static final g g = new g();
    static final h h = new h();
    static final /* synthetic */ boolean i = true;

    public static PrimitiveReference a(String str) {
        if (!i && str.length() != 1) {
            x1f.a();
            return null;
        }
        char cCharAt = str.charAt(0);
        if (cCharAt == 'F') {
            return f;
        }
        if (cCharAt == 'S') {
            return d;
        }
        if (cCharAt == 'Z') {
            return a;
        }
        if (cCharAt == 'I') {
            return e;
        }
        if (cCharAt == 'J') {
            return g;
        }
        switch (cCharAt) {
            case 'B':
                return b;
            case 'C':
                return c;
            case 'D':
                return h;
            default:
                x0g.a("Invalid primitive descriptor: ".concat(str));
                return null;
        }
    }

    @Override // com.android.tools.r8.references.TypeReference
    public PrimitiveReference asPrimitive() {
        return this;
    }

    public boolean equals(Object obj) {
        return this == obj;
    }

    @Override // com.android.tools.r8.references.TypeReference
    public abstract String getDescriptor();

    public int hashCode() {
        return System.identityHashCode(this);
    }

    @Override // com.android.tools.r8.references.TypeReference
    public boolean isPrimitive() {
        return true;
    }

    private PrimitiveReference() {
    }
}
