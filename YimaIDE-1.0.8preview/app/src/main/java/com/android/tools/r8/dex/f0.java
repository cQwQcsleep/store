package com.android.tools.r8.dex;

import com.android.tools.r8.DataEntryResource;
import com.android.tools.r8.ResourceException;
import com.android.tools.r8.graph.B1;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.internal.AbstractC3148ys;
import com.android.tools.r8.internal.C2752uB;
import com.android.tools.r8.internal.K7;
import com.android.tools.r8.internal.Wf0;
import com.android.tools.r8.naming.AbstractC3345r0;
import com.android.tools.r8.shaking.E3;
import com.android.tools.r8.shaking.R2;
import com.android.tools.r8.utils.ExceptionDiagnostic;
import com.android.tools.r8.utils.StringDiagnostic;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class f0 {
    public static final /* synthetic */ boolean f = true;
    public final C0333y a;
    public final B1 b;
    public final AbstractC3148ys c;
    public final AbstractC3345r0 d;
    public final C2752uB e;

    public f0(C0333y c0333y) {
        this.a = c0333y;
        this.b = c0333y.a();
        this.c = c0333y.A();
        this.d = c0333y.w();
        this.e = c0333y.M();
    }

    public static boolean c(DataEntryResource dataEntryResource) {
        return dataEntryResource.getName().startsWith("META-INF/services/");
    }

    public final byte[] a(DataEntryResource dataEntryResource) {
        try {
            InputStream byteStream = dataEntryResource.getByteStream();
            try {
                b0 b0Var = new b0(this, new String(K7.a(byteStream), Charset.defaultCharset()));
                if (!b0Var.d()) {
                    byteStream.close();
                    return null;
                }
                byte[] bytes = b0Var.c().getBytes(Charset.defaultCharset());
                byteStream.close();
                return bytes;
            } catch (Throwable th) {
                if (byteStream != null) {
                    try {
                        byteStream.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        } catch (ResourceException e) {
            this.e.i.error(new StringDiagnostic("Failed to open input: " + e.getMessage(), dataEntryResource.getOrigin()));
            return null;
        } catch (Exception e2) {
            this.e.i.error(new ExceptionDiagnostic(e2, dataEntryResource.getOrigin()));
            return null;
        }
    }

    public final DataEntryResource b(DataEntryResource dataEntryResource) {
        String name;
        if (a(dataEntryResource, this.e, new Function() { // from class: kug
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((R2) obj).c();
            }
        })) {
            e0 d0Var = dataEntryResource.getName().startsWith("META-INF/services/") ? new d0(this, dataEntryResource.getName()) : new Z(this, dataEntryResource.getName());
            name = d0Var.d() ? d0Var.c() : dataEntryResource.getName();
        } else {
            name = dataEntryResource.getName();
        }
        if (!f && name == null) {
            x1f.a();
            return null;
        }
        byte[] bArrA = a(dataEntryResource, this.e, new Function() { // from class: lug
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((R2) obj).b();
            }
        }) ? a(dataEntryResource) : null;
        if (bArrA != null) {
            return DataEntryResource.fromBytes(bArrA, name, dataEntryResource.getOrigin());
        }
        return !name.equals(dataEntryResource.getName()) ? dataEntryResource.withName(name) : dataEntryResource;
    }

    public static boolean a(DataEntryResource dataEntryResource, C2752uB c2752uB, Function function) {
        R2 r2H = c2752uB.H();
        if (r2H == null) {
            if (!f && !c2752uB.u1.J0) {
                x1f.a();
            }
            return false;
        }
        E3 e3 = (E3) function.apply(r2H);
        return e3.a && !Wf0.i(dataEntryResource.getName()).endsWith(".class") && e3.a(dataEntryResource.getName());
    }
}
