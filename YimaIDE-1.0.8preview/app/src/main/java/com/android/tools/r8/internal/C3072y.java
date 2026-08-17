package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.y, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3072y extends AbstractC2559s {
    @Override // com.android.tools.r8.internal.AbstractC2559s
    public final boolean a(C c, B b, B b2) {
        synchronized (c) {
            try {
                if (c.d != b) {
                    return false;
                }
                c.d = b2;
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC2559s
    public final B b(C c) {
        B b;
        B b2 = B.c;
        synchronized (c) {
            try {
                b = c.d;
                if (b != b2) {
                    c.d = b2;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return b;
    }

    @Override // com.android.tools.r8.internal.AbstractC2559s
    public final void a(B b, B b2) {
        b.b = b2;
    }

    @Override // com.android.tools.r8.internal.AbstractC2559s
    public final void a(B b, Thread thread) {
        b.a = thread;
    }

    @Override // com.android.tools.r8.internal.AbstractC2559s
    public final C2901w a(C c) {
        C2901w c2901w;
        C2901w c2901w2 = C2901w.b;
        synchronized (c) {
            try {
                c2901w = c.c;
                if (c2901w != c2901w2) {
                    c.c = c2901w2;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return c2901w;
    }

    @Override // com.android.tools.r8.internal.AbstractC2559s
    public final boolean a(C c, Object obj, Object obj2) {
        synchronized (c) {
            try {
                if (c.b != obj) {
                    return false;
                }
                c.b = obj2;
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
