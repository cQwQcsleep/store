package com.android.tools.r8.internal;

import com.android.tools.r8.DataResource;
import com.android.tools.r8.internal.AbstractC2243oF;
import com.android.tools.r8.internal.C2415qF;
import com.android.tools.r8.internal.C2585sF;
import com.android.tools.r8.internal.JE;
import defpackage.f5c;
import defpackage.g5c;
import defpackage.w36;
import java.util.Objects;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class RF extends AbstractC2526rd {
    public final boolean c;
    public final boolean d;
    public final InterfaceC1221cG e;
    public String f;
    public C2601sW g;

    public RF(boolean z, boolean z2, InterfaceC1221cG interfaceC1221cG) {
        super(null);
        this.c = z;
        this.d = z2;
        this.e = interfaceC1221cG;
    }

    public static K2 a(String str, boolean z, boolean z2, boolean z3, final Consumer consumer, C2516rW c2516rW, String str2, Consumer consumer2) {
        if (z) {
            return null;
        }
        if (z3 && "Lcom/android/tools/r8/keepanno/annotations/ExtractedKeepAnnotations;".equals(str)) {
            Objects.requireNonNull(consumer);
            return new HF(c2516rW, new f5c(consumer));
        }
        if (z2) {
            str.getClass();
            switch (str) {
                case "Lcom/android/tools/r8/keepanno/annotations/UsesReflection;":
                case "Lcom/android/tools/r8/keepanno/annotations/KeepForApi;":
                case "Lcom/android/tools/r8/keepanno/annotations/UsedByReflection;":
                case "Lcom/android/tools/r8/keepanno/annotations/UsedByNative;":
                case "Lcom/android/tools/r8/keepanno/annotations/KeepEdge;":
                case "Lcom/android/tools/r8/keepanno/annotations/CheckOptimizedOut;":
                case "Lcom/android/tools/r8/keepanno/annotations/CheckRemoved;":
                    if (str.equals("Lcom/android/tools/r8/keepanno/annotations/KeepEdge;")) {
                        Objects.requireNonNull(consumer);
                        return new UF(c2516rW, new g5c(consumer), consumer2);
                    }
                    if (str.equals("Lcom/android/tools/r8/keepanno/annotations/UsesReflection;")) {
                        C1476fH.a();
                        ME me = new ME(C1476fH.a(str2), C2416qG.c, C2345pV.b);
                        Objects.requireNonNull(consumer);
                        return new C1561gG(c2516rW, new g5c(consumer), consumer2, me);
                    }
                    if (str.equals("Lcom/android/tools/r8/keepanno/annotations/KeepForApi;")) {
                        Objects.requireNonNull(consumer);
                        return new KF(c2516rW, new g5c(consumer), consumer2, str2);
                    }
                    if (str.equals("Lcom/android/tools/r8/keepanno/annotations/UsedByReflection;") || str.equals("Lcom/android/tools/r8/keepanno/annotations/UsedByNative;")) {
                        Objects.requireNonNull(consumer);
                        return new C1305dG(c2516rW, new g5c(consumer), consumer2, str2);
                    }
                    if (str.equals("Lcom/android/tools/r8/keepanno/annotations/CheckRemoved;")) {
                        Objects.requireNonNull(consumer);
                        return new BF(c2516rW, new InterfaceC1221cG() { // from class: h5c
                            @Override // com.android.tools.r8.internal.InterfaceC1221cG
                            public final void accept(Object obj) {
                                consumer.accept((JE) obj);
                            }
                        }, consumer2, str2, 1);
                    }
                    if (!str.equals("Lcom/android/tools/r8/keepanno/annotations/CheckOptimizedOut;")) {
                        return null;
                    }
                    Objects.requireNonNull(consumer);
                    return new BF(c2516rW, new InterfaceC1221cG() { // from class: h5c
                        @Override // com.android.tools.r8.internal.InterfaceC1221cG
                        public final void accept(Object obj) {
                            consumer.accept((JE) obj);
                        }
                    }, consumer2, str2, 2);
            }
        }
        return null;
    }

    @Override // com.android.tools.r8.internal.AbstractC2526rd
    public final void a(int i, int i2, String str, String str2, String str3, String[] strArr) {
        super.a(i, i2, str, str2, str3, strArr);
        String strReplace = str.replace(DataResource.SEPARATOR, '.');
        this.f = strReplace;
        this.g = new C2601sW(strReplace);
    }

    @Override // com.android.tools.r8.internal.AbstractC2526rd
    public final J2 a(String str, boolean z) {
        boolean z2 = this.c;
        boolean z3 = this.d;
        InterfaceC1221cG interfaceC1221cG = this.e;
        Objects.requireNonNull(interfaceC1221cG);
        w36 w36Var = new w36(interfaceC1221cG);
        C2601sW c2601sW = this.g;
        c2601sW.getClass();
        return a(str, z, z2, z3, w36Var, new C2516rW(c2601sW, str), this.f, new Consumer() { // from class: e5c
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a((C2585sF) obj);
            }
        });
    }

    public final void a(C2585sF c2585sF) {
        String strC = AbstractC1732iG.c(this.f);
        c2585sF.getClass();
        c2585sF.a = new C2670tF(strC);
    }

    @Override // com.android.tools.r8.internal.AbstractC2526rd
    public final XO a(int i, String str, String str2, String str3, String[] strArr) {
        if (!this.c) {
            return null;
        }
        C2601sW c2601sW = this.g;
        final InterfaceC1221cG interfaceC1221cG = this.e;
        Objects.requireNonNull(interfaceC1221cG);
        return new TF(c2601sW, new InterfaceC1221cG() { // from class: i5c
            @Override // com.android.tools.r8.internal.InterfaceC1221cG
            public final void accept(Object obj) {
                interfaceC1221cG.accept((AbstractC2243oF) obj);
            }
        }, this.f, str, str2);
    }

    @Override // com.android.tools.r8.internal.AbstractC2526rd
    public final AbstractC0779Qp a(int i, String str, String str2, String str3, Object obj) {
        if (!this.c) {
            return null;
        }
        C2601sW c2601sW = this.g;
        final InterfaceC1221cG interfaceC1221cG = this.e;
        Objects.requireNonNull(interfaceC1221cG);
        return new SF(c2601sW, new InterfaceC1221cG() { // from class: d5c
            @Override // com.android.tools.r8.internal.InterfaceC1221cG
            public final void accept(Object obj2) {
                interfaceC1221cG.accept((C2415qF) obj2);
            }
        }, this.f, str, str2);
    }
}
