package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0215h;
import com.android.tools.r8.graph.C0333y;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Fd, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC0482Fd<T extends C0215h> {
    public static final /* synthetic */ boolean d = true;
    public final C0333y a;
    public final com.android.tools.r8.graph.B1 b;
    public final C2752uB c;

    public AbstractC0482Fd(C0333y c0333y) {
        this.a = c0333y;
        this.b = c0333y.a();
        this.c = c0333y.M();
    }

    public void a(C0705Nt c0705Nt, boolean z, String str) {
        boolean zB;
        String str2 = "Invalid code " + str + " " + b();
        C0333y c0333y = this.a;
        try {
            if (z) {
                zB = c0705Nt.b((C0333y<?>) c0333y);
            } else {
                c0705Nt.b(c0333y, false);
                zB = true;
            }
            if (d || zB) {
                return;
            }
            x01.a(str2);
        } catch (AssertionError e) {
            throw new AssertionError(str2, e);
        }
    }

    public abstract boolean a(C0705Nt c0705Nt, IO io2);

    public InterfaceC0560Id b(C0705Nt c0705Nt) {
        throw new Kk0("Should Override or use overload");
    }

    public abstract String b();

    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final InterfaceC0560Id a(C0705Nt c0705Nt, IO io2, C0483Fe c0483Fe) {
        if (!a(c0705Nt, io2)) {
            return c();
        }
        boolean z = d;
        if (!z) {
            a(c0705Nt, !(this instanceof C2367pi0), "before");
        }
        InterfaceC0560Id interfaceC0560IdB = b(c0705Nt, io2, c0483Fe);
        if (!z && !interfaceC0560IdB.a().a()) {
            a(c0705Nt, !(this instanceof C2367pi0), "after");
        }
        return interfaceC0560IdB;
    }

    public InterfaceC0560Id b(C0705Nt c0705Nt, IO io2, C0483Fe c0483Fe) {
        return b(c0705Nt);
    }

    public InterfaceC0560Id c() {
        return InterfaceC0560Id.a;
    }

    public final C0333y a() {
        return this.a;
    }

    public final InterfaceC0560Id a(final C0705Nt c0705Nt, final IO io2, final C0483Fe c0483Fe, Ch0 ch0) {
        return (InterfaceC0560Id) ch0.a(b(), new InterfaceC2706th0() { // from class: fo4
            @Override // com.android.tools.r8.internal.InterfaceC2706th0
            public final Object get() {
                return this.a.a(c0705Nt, io2, c0483Fe);
            }
        });
    }

    public final /* synthetic */ InterfaceC0560Id a(C0705Nt c0705Nt) {
        return a(c0705Nt, null, null);
    }

    public final InterfaceC0560Id a(final C0705Nt c0705Nt, Ch0 ch0) {
        return (InterfaceC0560Id) ch0.a(b(), new InterfaceC2706th0() { // from class: eo4
            @Override // com.android.tools.r8.internal.InterfaceC2706th0
            public final Object get() {
                return this.a.a(c0705Nt);
            }
        });
    }

    public final boolean a(com.android.tools.r8.graph.B5 b5) {
        if (this.c.Z0) {
            return true;
        }
        return b5.a().f(this.a);
    }
}
