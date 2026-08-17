package com.android.tools.r8.jetbrains.kotlinx.metadata.jvm;

import com.android.tools.r8.internal.InterfaceC1145bO;
import com.android.tools.r8.internal.KB;
import java.util.Arrays;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final /* synthetic */ class h implements InterfaceC1145bO {
    public final /* synthetic */ int[] a;
    public final /* synthetic */ String[] b;
    public final /* synthetic */ String[] c;
    public final /* synthetic */ int d;
    public final /* synthetic */ String e;
    public final /* synthetic */ int f;
    public final /* synthetic */ int[] g;
    public final /* synthetic */ String h;

    public h(int[] iArr, String[] strArr, String[] strArr2, int i, String str, int i2, int[] iArr2, String str2) {
        this.a = iArr;
        this.b = strArr;
        this.c = strArr2;
        this.d = i;
        this.e = str;
        this.f = i2;
        this.g = iArr2;
        this.h = str2;
    }

    @Override // java.lang.annotation.Annotation
    public final /* synthetic */ Class annotationType() {
        return InterfaceC1145bO.class;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1145bO
    public final /* synthetic */ int[] bv() {
        return this.a;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1145bO
    public final /* synthetic */ String[] d1() {
        return this.b;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1145bO
    public final /* synthetic */ String[] d2() {
        return this.c;
    }

    @Override // java.lang.annotation.Annotation
    public final boolean equals(Object obj) {
        if (!(obj instanceof InterfaceC1145bO)) {
            return false;
        }
        InterfaceC1145bO interfaceC1145bO = (InterfaceC1145bO) obj;
        return Arrays.equals(this.a, interfaceC1145bO.bv()) && Arrays.equals(this.b, interfaceC1145bO.d1()) && Arrays.equals(this.c, interfaceC1145bO.d2()) && this.d == interfaceC1145bO.xi() && KB.a((Object) this.e, (Object) interfaceC1145bO.xs()) && this.f == interfaceC1145bO.k() && Arrays.equals(this.g, interfaceC1145bO.mv()) && KB.a((Object) this.h, (Object) interfaceC1145bO.pn());
    }

    @Override // java.lang.annotation.Annotation
    public final int hashCode() {
        return (Arrays.hashCode(this.a) ^ (-2059602595)) + (Arrays.hashCode(this.b) ^ (-774644295)) + (Arrays.hashCode(this.c) ^ (-774644168)) + (Integer.hashCode(this.d) ^ (-2147447359)) + (this.e.hashCode() ^ 1642302527) + (Integer.hashCode(this.f) ^ 418090604) + (Arrays.hashCode(this.g) ^ (-1975239209)) + (this.h.hashCode() ^ (-551720817));
    }

    @Override // com.android.tools.r8.internal.InterfaceC1145bO
    public final /* synthetic */ int k() {
        return this.f;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1145bO
    public final /* synthetic */ int[] mv() {
        return this.g;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1145bO
    public final /* synthetic */ String pn() {
        return this.h;
    }

    @Override // java.lang.annotation.Annotation
    public final String toString() {
        return "@kotlin.Metadata(bytecodeVersion=" + Arrays.toString(this.a) + ", data1=" + Arrays.toString(this.b) + ", data2=" + Arrays.toString(this.c) + ", extraInt=" + this.d + ", extraString=" + this.e + ", kind=" + this.f + ", metadataVersion=" + Arrays.toString(this.g) + ", packageName=" + this.h + ')';
    }

    @Override // com.android.tools.r8.internal.InterfaceC1145bO
    public final /* synthetic */ int xi() {
        return this.d;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1145bO
    public final /* synthetic */ String xs() {
        return this.e;
    }
}
