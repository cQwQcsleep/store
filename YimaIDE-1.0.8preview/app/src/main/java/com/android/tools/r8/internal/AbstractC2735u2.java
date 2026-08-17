package com.android.tools.r8.internal;

import com.android.tools.r8.DiagnosticsHandler;
import com.android.tools.r8.utils.ExceptionDiagnostic;
import com.android.tools.r8.utils.StringDiagnostic;
import java.io.IOException;
import java.io.InputStream;
import java.util.function.BiPredicate;

/* JADX INFO: renamed from: com.android.tools.r8.internal.u2, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC2735u2 {
    public static final /* synthetic */ boolean b = true;
    public int a = -1;

    public static AbstractC2735u2 a(C2752uB c2752uB, DiagnosticsHandler diagnosticsHandler) {
        if (AbstractC2735u2.class.getClassLoader().getResource("resources/new_api_database.ser") == null) {
            diagnosticsHandler.warning(new StringDiagnostic("Could not find the api database at resources/new_api_database.ser"));
            return new C2564s2();
        }
        c2752uB.a().getClass();
        try {
            InputStream resourceAsStream = AbstractC2735u2.class.getClassLoader().getResourceAsStream("resources/new_api_database.ser");
            try {
                if (resourceAsStream != null) {
                    C2478r2 c2478r2 = new C2478r2(K7.a(resourceAsStream));
                    resourceAsStream.close();
                    return c2478r2;
                }
                diagnosticsHandler.warning(new StringDiagnostic("Could not open the api database at resources/new_api_database.ser"));
                C2564s2 c2564s2 = new C2564s2();
                if (resourceAsStream == null) {
                    return c2564s2;
                }
                resourceAsStream.close();
                return c2564s2;
            } catch (Throwable th) {
                if (resourceAsStream != null) {
                    try {
                        resourceAsStream.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        } catch (IOException e) {
            diagnosticsHandler.warning(new ExceptionDiagnostic(e));
            return new C2564s2();
        }
    }

    public static int c() {
        return 17;
    }

    public abstract int a(int i, int i2, byte[] bArr, BiPredicate biPredicate);

    public abstract boolean a(byte[] bArr, int i, int i2);

    public abstract byte b(byte[] bArr, int i, int i2);

    public final int b(com.android.tools.r8.graph.H2 h2) {
        int iA = a(h2);
        if (this.a == -1) {
            this.a = d();
        }
        C2649t2 c2649t2C = c((iA * 6) + b(this.a));
        c2649t2C.getClass();
        if (c2649t2C == C2649t2.c) {
            return -1;
        }
        int i = c2649t2C.a;
        int i2 = c2649t2C.b;
        if (i < 0) {
            if (!b && i >= 0) {
                x1f.a();
                return 0;
            }
            boolean z = W7.a;
            int i3 = Integer.MAX_VALUE & i;
            if (a(i3, h2.f)) {
                return i3;
            }
            return -1;
        }
        if (!b && i2 <= 0) {
            x1f.a();
            return 0;
        }
        if (this.a == -1) {
            this.a = d();
        }
        int i4 = this.a;
        return a(a(i4) + ((1 << b()) * 6) + i, i2, h2.f, new BiPredicate() { // from class: kei
            @Override // java.util.function.BiPredicate
            public final boolean test(Object obj, Object obj2) {
                return this.b.a(((Integer) obj).intValue(), (byte[]) obj2);
            }
        });
    }

    public abstract C2649t2 c(int i);

    public abstract int d();

    public static boolean a(String str) {
        return "resources/new_api_database.ser".equals(str);
    }

    public static int a() {
        return 4;
    }

    public static int a(com.android.tools.r8.graph.F2 f2) {
        int iB = 1 << (b() - 1);
        return (f2.hashCode() % iB) + iB;
    }

    public static int a(com.android.tools.r8.graph.H2 h2) {
        int iC = 1 << (c() - 1);
        return (h2.hashCode() % iC) + iC;
    }

    public static int a(int i) {
        return b(i) + ((1 << c()) * 6);
    }

    public final boolean a(int i, byte[] bArr) {
        C2649t2 c2649t2C = c((i * 6) + a());
        c2649t2C.getClass();
        if (c2649t2C == C2649t2.c || bArr.length != c2649t2C.b) {
            return false;
        }
        if (this.a == -1) {
            this.a = d();
        }
        int i2 = this.a;
        return a(bArr, a(i2) + ((1 << b()) * 6) + c2649t2C.a, c2649t2C.b);
    }

    public static int b() {
        return 18;
    }

    public static int b(int i) {
        return a() + (i * 6);
    }
}
