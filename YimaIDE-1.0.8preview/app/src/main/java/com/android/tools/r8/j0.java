package com.android.tools.r8;

import com.android.tools.r8.internal.C2742u50;
import com.android.tools.r8.internal.InterfaceC2105mg0;
import com.android.tools.r8.internal.Ta0;
import com.android.tools.r8.internal.Wf0;
import com.android.tools.r8.shaking.C3367a3;
import com.android.tools.r8.shaking.T2;
import com.android.tools.r8.shaking.Z2;
import com.android.tools.r8.utils.ExceptionDiagnostic;
import com.android.tools.r8.utils.StringDiagnostic;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class j0 implements DataResourceProvider.Visitor {
    public static final /* synthetic */ boolean f = true;
    public final Supplier a;
    public final C2742u50 b;
    public final ArrayList c = new ArrayList();
    public final ArrayList d = new ArrayList();
    public Ta0 e;

    public j0(C2742u50 c2742u50, InterfaceC2105mg0 interfaceC2105mg0) {
        this.a = interfaceC2105mg0;
        this.b = c2742u50;
    }

    public final boolean a(DataEntryResource dataEntryResource) {
        Ta0 ta0A;
        if (!dataEntryResource.getName().startsWith("META-INF/com.android.tools/r8")) {
            return false;
        }
        String strSubstring = dataEntryResource.getName().substring(29);
        if (strSubstring.startsWith("/")) {
            return true;
        }
        if (!strSubstring.startsWith("-from-") && !strSubstring.startsWith("-upto-")) {
            return false;
        }
        Ta0 ta0A2 = Ta0.f;
        if (strSubstring.startsWith("-from-")) {
            String strSubstring2 = strSubstring.substring(6);
            char[] cArr = Wf0.a;
            int iIndexOf = strSubstring2.indexOf(45);
            int iIndexOf2 = strSubstring2.indexOf(47);
            if (iIndexOf == -1) {
                iIndexOf = iIndexOf2;
            } else if (iIndexOf2 != -1) {
                iIndexOf = Math.min(iIndexOf, iIndexOf2);
            }
            if (iIndexOf == -1) {
                return false;
            }
            try {
                ta0A2 = Ta0.a(strSubstring2.substring(0, iIndexOf));
                strSubstring = strSubstring2.substring(iIndexOf);
            } catch (IllegalArgumentException unused) {
                return false;
            }
        }
        if (strSubstring.startsWith("-upto-")) {
            String strSubstring3 = strSubstring.substring(6);
            int iIndexOf3 = strSubstring3.indexOf(47);
            if (iIndexOf3 == -1) {
                return false;
            }
            try {
                ta0A = Ta0.a(strSubstring3.substring(0, iIndexOf3));
            } catch (IllegalArgumentException unused2) {
                return false;
            }
        } else {
            ta0A = null;
        }
        if (this.e == null) {
            this.e = (Ta0) this.a.get();
        }
        return this.e.b(ta0A2) && (ta0A == null || ta0A.a(this.e));
    }

    @Override // com.android.tools.r8.DataResourceProvider.Visitor
    public final void visit(DataEntryResource dataEntryResource) {
        if (!dataEntryResource.getName().startsWith("META-INF/proguard") ? false : dataEntryResource.getName().substring(17).startsWith("/")) {
            if (!f && a(dataEntryResource)) {
                x1f.a();
                return;
            }
            final ArrayList arrayList = this.c;
            Objects.requireNonNull(arrayList);
            a(dataEntryResource, new Consumer() { // from class: lah
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    arrayList.add((Z2) obj);
                }
            });
            return;
        }
        if (a(dataEntryResource)) {
            if (!f) {
                if (dataEntryResource.getName().startsWith("META-INF/proguard") ? dataEntryResource.getName().substring(17).startsWith("/") : false) {
                    x1f.a();
                    return;
                }
            }
            final ArrayList arrayList2 = this.d;
            Objects.requireNonNull(arrayList2);
            a(dataEntryResource, new Consumer() { // from class: lah
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    arrayList2.add((Z2) obj);
                }
            });
        }
    }

    @Override // com.android.tools.r8.DataResourceProvider.Visitor
    public final void visit(DataDirectoryResource dataDirectoryResource) {
    }

    public final void a(DataEntryResource dataEntryResource, Consumer consumer) {
        try {
            InputStream byteStream = dataEntryResource.getByteStream();
            try {
                consumer.accept(new C3367a3(dataEntryResource.getOrigin(), byteStream));
                byteStream.close();
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
            this.b.error(new StringDiagnostic("Failed to open input: " + e.getMessage(), dataEntryResource.getOrigin()));
        } catch (Exception e2) {
            this.b.error(new ExceptionDiagnostic(e2, dataEntryResource.getOrigin()));
        }
    }

    public final void a(T2 t2) {
        for (Z2 z2 : !this.d.isEmpty() ? this.d : this.c) {
            try {
                t2.a(z2);
            } catch (Exception e) {
                this.b.error(new ExceptionDiagnostic(e, z2.getOrigin()));
            }
        }
    }
}
