package com.android.tools.r8.internal;

import java.io.Serializable;

/* JADX INFO: renamed from: com.android.tools.r8.internal.og0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2277og0 implements InterfaceC2105mg0, Serializable {
    public final InterfaceC2105mg0 b;
    public volatile transient boolean c;
    public transient Object d;

    public C2277og0(InterfaceC2105mg0 interfaceC2105mg0) {
        interfaceC2105mg0.getClass();
        this.b = interfaceC2105mg0;
    }

    @Override // java.util.function.Supplier
    public final Object get() {
        if (!this.c) {
            synchronized (this) {
                try {
                    if (!this.c) {
                        Object obj = this.b.get();
                        this.d = obj;
                        this.c = true;
                        return obj;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return this.d;
    }

    public final String toString() {
        Object obj;
        StringBuilder sb = new StringBuilder("Suppliers.memoize(");
        if (this.c) {
            obj = "<supplier that returned " + this.d + ">";
        } else {
            obj = this.b;
        }
        sb.append(obj);
        sb.append(")");
        return sb.toString();
    }
}
