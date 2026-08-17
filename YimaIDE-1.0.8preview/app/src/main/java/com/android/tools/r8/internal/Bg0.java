package com.android.tools.r8.internal;

import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Bg0 implements Serializable {
    public InterfaceC1270cr b;
    public volatile Object c = Sj0.a;
    public final Object d = this;

    public Bg0(InterfaceC1270cr interfaceC1270cr) {
        this.b = interfaceC1270cr;
    }

    public final Object a() {
        Object objA;
        Object obj = this.c;
        Sj0 sj0 = Sj0.a;
        if (obj != sj0) {
            return obj;
        }
        synchronized (this.d) {
            objA = this.c;
            if (objA == sj0) {
                InterfaceC1270cr interfaceC1270cr = this.b;
                KB.a(interfaceC1270cr);
                objA = interfaceC1270cr.a();
                this.c = objA;
                this.b = null;
            }
        }
        return objA;
    }

    public final String toString() {
        return this.c != Sj0.a ? String.valueOf(a()) : "Lazy value not initialized yet.";
    }
}
