package com.android.tools.r8.kotlin;

import com.android.tools.r8.internal.InterfaceC1145bO;
import com.android.tools.r8.internal.Kk0;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class Q implements InterfaceC1145bO {
    public static final String[] h = new String[0];
    public static final int[] i = new int[0];
    public final int a;
    public final int[] b;
    public final String[] c;
    public final String[] d;
    public final int e;
    public final String f;
    public final String g;

    public Q(Integer num, int[] iArr, String[] strArr, String[] strArr2, String str, String str2, Integer num2) {
        this.a = num.intValue();
        this.b = iArr == null ? i : iArr;
        this.c = strArr == null ? h : strArr;
        this.d = strArr2 == null ? h : strArr2;
        this.f = str == null ? XmlPullParser.NO_NAMESPACE : str;
        this.g = str2 == null ? XmlPullParser.NO_NAMESPACE : str2;
        this.e = num2 == null ? 0 : num2.intValue();
    }

    public static Q a(com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.r rVar) {
        com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.h hVarB;
        boolean z = r.a;
        com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.j jVarA = rVar.a();
        if (jVarA.b != 1 || jVarA.c >= 4) {
            hVarB = rVar.b();
        } else {
            rVar.a(L.a);
            hVarB = rVar.b();
            rVar.a(jVarA);
        }
        return new Q(Integer.valueOf(hVarB.f), L.a(rVar.a()), hVarB.b, hVarB.c, hVarB.e, hVarB.h, Integer.valueOf(hVarB.d));
    }

    @Override // java.lang.annotation.Annotation
    public final Class annotationType() {
        throw new Kk0("Should never be called");
    }

    public String[] b() {
        return this.d;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1145bO
    public final int[] bv() {
        throw new Kk0("Field is deprecated and should not be used");
    }

    public int d() {
        return this.a;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1145bO
    public final String[] d1() {
        return this.c;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1145bO
    public final String[] d2() {
        return this.d;
    }

    public String e() {
        return this.g;
    }

    @Override // java.lang.annotation.Annotation
    public final boolean equals(Object obj) {
        throw new Kk0();
    }

    @Override // java.lang.annotation.Annotation
    public final int hashCode() {
        throw new Kk0();
    }

    @Override // com.android.tools.r8.internal.InterfaceC1145bO
    public final int k() {
        return this.a;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1145bO
    public final int[] mv() {
        return this.b;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1145bO
    public final String pn() {
        return this.g;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1145bO
    public final int xi() {
        return this.e;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1145bO
    public final String xs() {
        return this.f;
    }

    public String[] a() {
        return this.c;
    }
}
