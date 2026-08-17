package com.android.tools.r8.internal;

import com.android.tools.r8.internal.C2363pg0;

/* JADX INFO: renamed from: com.android.tools.r8.internal.pg0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2363pg0 implements InterfaceC2105mg0 {
    public static final InterfaceC2105mg0 d = new InterfaceC2105mg0() { // from class: t0i
        @Override // java.util.function.Supplier
        public final Object get() {
            return C2363pg0.a();
        }
    };
    public volatile InterfaceC2105mg0 b;
    public Object c;

    public C2363pg0(InterfaceC2105mg0 interfaceC2105mg0) {
        interfaceC2105mg0.getClass();
        this.b = interfaceC2105mg0;
    }

    public static /* synthetic */ Void a() {
        throw new IllegalStateException();
    }

    @Override // java.util.function.Supplier
    public final Object get() {
        InterfaceC2105mg0 interfaceC2105mg0 = this.b;
        InterfaceC2105mg0 interfaceC2105mg1 = d;
        if (interfaceC2105mg0 != interfaceC2105mg1) {
            synchronized (this) {
                try {
                    if (this.b != interfaceC2105mg1) {
                        Object obj = this.b.get();
                        this.c = obj;
                        this.b = interfaceC2105mg1;
                        return obj;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return this.c;
    }

    public final String toString() {
        Object obj = this.b;
        StringBuilder sb = new StringBuilder("Suppliers.memoize(");
        if (obj == d) {
            obj = "<supplier that returned " + this.c + ">";
        }
        sb.append(obj);
        sb.append(")");
        return sb.toString();
    }
}
