package com.android.tools.r8.jetbrains.kotlinx.metadata.jvm;

import com.android.tools.r8.internal.KB;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class j implements Comparable {
    public static final j e = new j(1, 9, 0);
    public static final j f = new j(2, 0, 0);
    public final int b;
    public final int c;
    public final int d;

    public j(int i, int i2, int i3) {
        this.b = i;
        this.c = i2;
        this.d = i3;
        if (i < 0) {
            w01.a("Major version should be not less than 0");
            throw null;
        }
        if (i2 < 0) {
            w01.a("Minor version should be not less than 0");
            throw null;
        }
        if (i3 >= 0) {
            return;
        }
        w01.a("Patch version should be not less than 0");
        throw null;
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public final int compareTo(j jVar) {
        KB.c(jVar, "other");
        int iA = KB.a(this.b, jVar.b);
        if (iA != 0) {
            return iA;
        }
        int iA2 = KB.a(this.c, jVar.c);
        return iA2 != 0 ? iA2 : KB.a(this.d, jVar.d);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!j.class.equals(obj != null ? obj.getClass() : null)) {
            return false;
        }
        KB.a(obj, "null cannot be cast to non-null type kotlinx.metadata.jvm.JvmMetadataVersion");
        j jVar = (j) obj;
        return this.b == jVar.b && this.c == jVar.c && this.d == jVar.d;
    }

    public final int hashCode() {
        return (((this.b * 31) + this.c) * 31) + this.d;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.b);
        sb.append('.');
        sb.append(this.c);
        sb.append('.');
        sb.append(this.d);
        return sb.toString();
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public j(int[] iArr) {
        this(iArr[0], iArr[1], iArr[2]);
        KB.c(iArr, "intArray");
    }
}
