package com.android.tools.r8.internal;

import com.android.tools.r8.internal.AbstractC1646hG;
import com.android.tools.r8.internal.AbstractC2243oF;
import com.android.tools.r8.internal.C2415qF;
import com.android.tools.r8.internal.C2585sF;
import defpackage.lx5;
import java.util.ArrayList;
import java.util.Objects;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.hG, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC1646hG {
    public static ArrayList a(byte[] bArr, boolean z, boolean z2) {
        C1586gd c1586gd = new C1586gd(bArr);
        ArrayList arrayList = new ArrayList();
        c1586gd.a(new RF(z, z2, new lx5(arrayList)), new H4[0], 1);
        return arrayList;
    }

    public static K2 b(String str, boolean z, boolean z2, boolean z3, final String str2, final String str3, final String str4, C2516rW c2516rW, final Consumer consumer) {
        Objects.requireNonNull(consumer);
        return TF.a(str, z, z2, new Consumer() { // from class: s2h
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                consumer.accept((AbstractC2243oF) obj);
            }
        }, c2516rW, str2, str3, str4, new Consumer() { // from class: t2h
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                AbstractC1646hG.b(str2, str3, str4, (C2585sF) obj);
            }
        });
    }

    public static void b(String str, String str2, String str3, C2585sF c2585sF) {
        String strB = AbstractC1732iG.b(str);
        c2585sF.getClass();
        c2585sF.a = new C3013xF(strB, str2, str3);
    }

    public static K2 a(String str, boolean z, boolean z2, boolean z3, final String str2, C2516rW c2516rW, Consumer consumer) {
        return RF.a(str, z, z2, z3, consumer, c2516rW, str2, new Consumer() { // from class: u2h
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                AbstractC1646hG.a(str2, (C2585sF) obj);
            }
        });
    }

    public static void a(String str, C2585sF c2585sF) {
        String strB = AbstractC1732iG.b(str);
        c2585sF.getClass();
        c2585sF.a = new C2670tF(strB);
    }

    public static K2 a(String str, boolean z, boolean z2, boolean z3, final String str2, final String str3, final String str4, C2516rW c2516rW, final Consumer consumer) {
        Objects.requireNonNull(consumer);
        return SF.a(str, z, z2, new Consumer() { // from class: v2h
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                consumer.accept((C2415qF) obj);
            }
        }, c2516rW, str2, str3, str4, new Consumer() { // from class: w2h
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                AbstractC1646hG.a(str2, str3, str4, (C2585sF) obj);
            }
        });
    }

    public static void a(String str, String str2, String str3, C2585sF c2585sF) {
        String strB = AbstractC1732iG.b(str);
        c2585sF.getClass();
        c2585sF.a = new C2927wF(strB, str2, str3);
    }
}
