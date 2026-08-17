package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Dr, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC0444Dr extends AbstractC2063m8 implements InterfaceC0418Cr, InterfaceC2156nE, InterfaceC2721tr {
    public final int h;
    public final int i;

    public AbstractC0444Dr() {
        super(Ua0.class, "iterator", "iterator()Ljava/util/Iterator;", false);
        this.h = 1;
        this.i = 0;
    }

    @Override // com.android.tools.r8.internal.InterfaceC0418Cr
    public final int b() {
        return this.h;
    }

    @Override // com.android.tools.r8.internal.AbstractC2063m8
    public final InterfaceC2156nE c() {
        AbstractC2654t40.a.getClass();
        return this;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        boolean z = obj instanceof AbstractC0444Dr;
        if (z) {
            AbstractC0444Dr abstractC0444Dr = (AbstractC0444Dr) obj;
            return this.e.equals(abstractC0444Dr.e) && this.f.equals(abstractC0444Dr.f) && this.i == abstractC0444Dr.i && this.h == abstractC0444Dr.h && KB.a(this.c, abstractC0444Dr.c) && KB.a(e(), abstractC0444Dr.e());
        }
        if (!z) {
            return false;
        }
        InterfaceC2156nE interfaceC2156nEC = this.b;
        if (interfaceC2156nEC == null) {
            interfaceC2156nEC = c();
            this.b = interfaceC2156nEC;
        }
        return obj.equals(interfaceC2156nEC);
    }

    public final int hashCode() {
        return this.f.hashCode() + ((this.e.hashCode() + (e() == null ? 0 : e().hashCode() * 31)) * 31);
    }

    public final String toString() {
        InterfaceC2156nE interfaceC2156nEC = this.b;
        if (interfaceC2156nEC == null) {
            interfaceC2156nEC = c();
            this.b = interfaceC2156nEC;
        }
        if (interfaceC2156nEC != this) {
            return interfaceC2156nEC.toString();
        }
        if ("<init>".equals(this.e)) {
            return "constructor (Kotlin reflection is not available)";
        }
        return "function " + this.e + " (Kotlin reflection is not available)";
    }
}
